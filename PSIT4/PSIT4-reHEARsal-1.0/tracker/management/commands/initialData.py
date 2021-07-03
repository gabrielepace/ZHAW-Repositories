from django.core.management.base import BaseCommand, CommandError
from django.conf import settings
from tracker.models import *
import os

class Command(BaseCommand):
    help = 'Initializes the database with dummy test data'

    def handle(self, *args, **options):
        self.stdout.write("adding test session")
        s  = Session.objects.create(name="testSession")
        s.save()
        self.stdout.write("adding test track")
        t  = Track.objects.create(session=s, name="Test Track 1")
        t.save()
        self.stdout.write("adding test version")
        v  = Version.objects.create(track=t, audiofile=os.path.join(settings.MEDIA_ROOT, 'test', 'rickroll.mp3'))
        v.save()
        self.stdout.write("adding test version")
        v2 = Version.objects.create(track=t, audiofile=os.path.join(settings.MEDIA_ROOT, 'test', 'rickroll.mp3'))
        v2.save()

        self.stdout.write("adding test threads and comments")
        t = VersionThread.objects.create(version=v)
        vc = VersionComment.objects.create(thread=t,who="johnny",text="yee haw")
        vc2 = VersionComment.objects.create(thread=t,who="Mr. Hankey",text="Howdy ho'!")
        tt = TimeThread.objects.create(version=v2, relative_time=0.5)
        tc = TimeComment.objects.create(thread=tt,who="Towlie",text="don't forget to bring a towel")
        tc2 = TimeComment.objects.create(thread=tt,who="Cpt. Obvious",text="This is the middle of the Version")

        self.stdout.write("initialized database with dummish data")
