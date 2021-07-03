from django.db import models
from django.db.models.constraints import UniqueConstraint
from django.db.models.signals import pre_save, post_save
from django.dispatch import receiver
from django.conf import settings
import logging
logger = logging.getLogger(__name__)

from .utils import obj2json, get_random_line, audio2wav, AmpliSpectrogram

import django.utils.timezone as tz

class Track(models.Model):
    class Meta:
        constraints = [ UniqueConstraint( fields=['name', 'session'], name="unique_name_session" ), ]
    name = models.CharField(max_length=200)
    session = models.ForeignKey('Session', on_delete=models.CASCADE)
    current_version = models.ForeignKey('Version', null=True, on_delete=models.CASCADE)

    def __str__(self):
        return "{}: {}".format( self.session, self.name )

    def to_dict(self):
        return {'versions': obj2json(self.version_set.all())}
        
    @property
    def version_count(self):
        return Version.objects.filter(track=self).count()
    @property
    def current_version(self):
        return Version.objects.filter(track=self).order_by('date_created').last()

    def get_versions(self):
        return Version.objects.filter(track=self)

class Version(models.Model):
    track = models.ForeignKey('Track', on_delete=models.CASCADE)
    audiofile = models.FileField(upload_to='versions')
    amplispectrogram = models.ImageField(null=True,upload_to='amplispec')
    length = models.DurationField(null=True)
    info = models.TextField(blank=True, null=True)
    date_created = models.DateTimeField(auto_now_add=True)
    last_modified = models.DateTimeField(auto_now=True)

    def __str__(self):
        if not self.info:
            return "{} v{}".format(self.track.name, self.id)
        return "{} v{} [{}]".format(self.track.name, self.id, self.info)

    def to_dict(self):
        data = dict()
        data['audiofile'] = self.audiofile.url
        data['amplispectrogram'] = None ### TO BE FIXED
        try:
            data['amplispectrogram'] = self.amplispectrogram.url
        except:
            data['amplispectrogram'] = ""
        try:
            time, millis = str(self.length).split('.')
        except ValueError:
            time = str(self.length)
        hours, mins, secs = time.split(':')
        if int(hours)>0:
            data['length'] = "{}h{}'{}"
        else:
            data['length'] = "{}'{}".format(mins,secs)
        data['comments'] = obj2json(VersionComment.objects.filter(thread=self.versionthread))
        data['timethreads'] = obj2json(self.timethread_set.all())
        return data


    def calc_amplispectrogram(self):
        import os
        filename = "{}.{}".format(self.track.name,self.id)
        out = os.path.join(settings.MEDIA_ROOT, "amplispec", filename+".png")
        logger.debug("create wav file")
        audio2wav(self.audiofile.path)
        logger.debug("create amplispec")
        AmpliSpectrogram(self.audiofile.path+".wav", out).plot()
        logger.debug("delete wav file")
        os.remove(self.audiofile.path+".wav")
        os.system("mogrify -crop 3954x850+273+75 \"{}\"".format(out))# dirty hack because matplotlib draws with padding. apt install imagemagick
        Version.objects.filter( id=self.id ).update( amplispectrogram=os.path.join("amplispec", os.path.basename(out)))

    @property
    def filesize(self):
        return "{} MB".format(round(self.audiofile.size/1024**2,1))

    def update_file_info(self):
        import math
        import mutagen
        from datetime import timedelta
        audio = mutagen.File(self.audiofile)
        (decimal,secs) = math.modf(audio.info.length)
        Version.objects.filter( id=self.id ).update(length=timedelta(seconds=secs, microseconds=decimal*1000000))

@receiver(post_save, sender=Version)
def update_audiofile_info(sender, instance, **kwargs):
    logger.debug("post_save update_audiofile_info()")
    instance.update_file_info()

@receiver(post_save, sender=Version)
def generate_amplispec_for_version(sender, instance, **kwargs):
    logger.debug("post_save generate_amplispec_for_version()")
    instance.calc_amplispectrogram()
    
@receiver(post_save, sender=Version)
def generate_versionthread_for_version(sender, instance, **kwargs):
    vthread = VersionThread.objects.get_or_create(version=instance)

    
class Session(models.Model):
    class Meta:
        constraints = [ UniqueConstraint( fields=['name', 'date' ], name="unique_name_date"),]
        ordering = ['-date']
    name = models.CharField(max_length=200)
    date = models.DateField(null=True)
    description = models.TextField()
    hash_string = models.CharField(max_length=40, unique=True, default="grievous-badger")

    def __str__(self):
        return "{} [{}]".format(self.name, self.hash_string)

    def to_dict(self):
        data = dict()
        data['track_count'] = self.track_count
        data['track_length_sum'] = str(self.track_length_sum)
        data['tracks'] = obj2json( Track.objects.filter(session=self) )
        data['tracks'] = obj2json( Track.objects.filter(session=self) )
        return data

    def generate_unused_hash_string():
        import os
        directory = os.path.join(settings.MEDIA_ROOT, "txt")
        hash_string = ""
        while (hash_string=="" or Session.objects.filter(hash_string=hash_string).count() > 0):
            adjective = get_random_line( os.path.join(directory, "adjectives.txt") )
            noun = get_random_line( os.path.join(directory, "nouns.txt") )
            hash_string = adjective + '-' + noun
        return hash_string
    @property
    def track_count(self):
        return Track.objects.filter(session=self).count()
    @property
    def version_count(self):
        a = 0
        for t in Track.objects.filter(session=self):
            a += Version.objects.filter(track=t).count()
        return a

    @property
    def track_length_sum(self):
        import datetime
        if self.version_count==0:
            return 0.0
        return sum([ t.current_version.length
                     for t in Track.objects.filter(session=self)
        ], datetime.timedelta())

@receiver(pre_save, sender=Session)
def gen_hash_for_sess(sender, instance, **kwargs):
    if instance.hash_string=="" or instance.hash_string is None or instance.hash_string == Session.hash_string.field.default:
        instance.hash_string = Session.generate_unused_hash_string()


class CommentThread(models.Model):
    class Meta:
        abstract = True
    @property
    def comment_count(self):
        return self.timecomment_set.all().count()
    @property
    def track(self):
        return self.version.track
    @property
    def session(self):
        return self.track.session
    
class TimeThread(CommentThread):
    class Meta:
        unique_together = (('version', 'relative_time'),)
        constraints = ( models.CheckConstraint(check=models.Q(relative_time__gte=0.0), name="reltime__gte_0"),
                        models.CheckConstraint(check=models.Q(relative_time__lte=1.0), name="reltime__lte_0"))
        
    version = models.ForeignKey(Version, on_delete=models.CASCADE)
    relative_time = models.FloatField()

    def __str__(self):
        return "Thread: {} [{}]".format(str(self.version), self.relative_time)

    def to_dict(self):
        timecomments = TimeComment.objects.filter(thread=self)
        return {
            'relative_time': self.relative_time,
            'comment_count': timecomments.count(),
            'comments': obj2json(timecomments)
        }

    @property
    def comment_count(self):
        return self.timecomment_set.all().count()
    @property
    def track(self):
        return self.version.track
    @property
    def session(self):
        return self.track.session
class VersionThread(CommentThread):
    version = models.OneToOneField(Version, on_delete=models.CASCADE)

    def __str__(self):
        return "Thread: {}".format(str(self.version))

    def to_dict(self):
        return {'comments': obj2json( TimeComment.objects.filter(thread=instance) )}

    @property
    def comment_count(self):
        return self.versioncomment_set.all().count()

class Comment(models.Model):
    class Meta:
        abstract = True
    who = models.CharField(max_length=32)
    text = models.TextField(null=False)
    when = models.DateTimeField(auto_now_add=True)
    last_modified = models.DateTimeField(auto_now=True)

    def __str__(self):
        return "[{}: {}]".format(self.who,self.text)


class VersionComment(Comment):
    thread = models.ForeignKey('VersionThread', on_delete=models.CASCADE)

class TimeComment(Comment):
    thread = models.ForeignKey('TimeThread', on_delete=models.CASCADE)

    def __str__(self):
        return "[{}@{}: {}]".format(self.who, self.thread.relative_time, self.text)
