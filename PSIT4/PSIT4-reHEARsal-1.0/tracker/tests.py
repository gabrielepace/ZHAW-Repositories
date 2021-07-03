import os
import json
from datetime import timedelta
from django.conf import settings
from django.contrib.auth.models import User
from django.core.files.uploadedfile import SimpleUploadedFile
from django.db.utils import IntegrityError
from django.test import TestCase, Client
from django.urls import reverse

from .models import *

class UnitTests(TestCase):
    """ creates Users, a Session, a Track, Versions, a Version Thread, a Track Thread, a Time Thread and some Comments """
    def setUp(self):
        self.user = User.objects.create(username="dirty_rick13")
        self.user2 = User.objects.create(username="astley_inc0gnit0")
        self.session = Session.objects.create(name="sess1")

        self.track = Track.objects.create(
            session=self.session, name="Never Gonna Give You Up")
        self.version = Version.objects.create(
            track=self.track, audiofile=os.path.join('test', 'pinknoise.aiff'))
        self.version = Version.objects.get(id = self.version.id) # the object is reloaded because the post_save methods change it
        self.version2 = Version.objects.create(track=self.track,
                                audiofile=os.path.join('test', 'pinknoise.aiff'))
        self.version2 = Version.objects.get(id = self.version2.id)
        self.version_thread, created = VersionThread.objects.get_or_create(version=self.version)
        self.version_comment= VersionComment.objects.create(
            thread=self.version_thread,
            who="super user 123",
            text="Gary, I LOVE how you rock your drums here!")
        self.time_comment_thread = TimeThread.objects.create(version=self.version, relative_time=0.5)
        self.time_comment = TimeComment.objects.create(thread=self.time_comment_thread, who="n00b asdf", text="This is the exact middle of the song.")
        
    def test_version_creation(self):
        """Objects are being created with working relations"""

        self.assertEqual(self.track.__class__, Track)
        self.assertEqual(self.version.__class__, Version)
        self.assertEqual(self.version_comment.__class__, VersionComment)
        self.assertEqual(self.time_comment.__class__, TimeComment)

        self.assertEqual(self.track.version_count, 2)
        self.assertEqual(self.track.current_version, self.version2)
        self.assertNotEqual(self.version,self.version2)

        self.assertEqual(self.version.length, timedelta(seconds=2,microseconds=530975))
        self.assertEqual(self.version.filesize, "0.9 MB")
        self.assertEqual(Version.objects.all().count(), 2)

        self.assertEqual(self.version_thread.comment_count,1)
        self.assertEqual(self.time_comment_thread.comment_count,1)

    def test_session_hash(self):
        self.assertNotEqual( len(self.session.hash_string), 0 )
        s = Session(name="yolo")
        s.save()
        self.assertNotEqual(Session.objects.last().hash_string, "")
        with self.assertRaises(IntegrityError):
            Session(hash_string = self.session.hash_string).save()

    def tearDown(self):
        """ if tearDown fails this means probably that no image file was created for Version
which is bad """
        import os
        for v in [self.version,self.version2]:
            os.remove( v.amplispectrogram.path )

class JSONTests(TestCase):
    """ tests JSON creation from object data (.utils.obj2json method) """
    def setUp(self):
        self.session = Session.objects.create(name="Session 1")
        self.track = Track.objects.create(name="I Will Always Love You", session=self.session)

    def test_json_creation(self):
        from .utils import obj2json
        track_test_dict = {
            'id': self.track.id,
            'name': self.track.name,
            'session': self.track.session.id,
            'versions': [obj2json(v) for v in self.track.version_set.all()] }
        self.assertEqual( obj2json(self.track), track_test_dict )
        jsession = obj2json(self.session)
        self.assertEqual( len(jsession['tracks']), 1)
        self.assertEqual( jsession['tracks'][0]['name'], self.track.name )

class APITests(TestCase):
    """ tests JSON API calls to backend """
    def setUp(self):
        self.client = Client()

    def test_csrf(self):
        import json
        response = self.client.get(reverse("csrf"))
        data = json.loads(response.content)
        self.assertEqual( len(data['csrf_token']), 64 )


    def create_test(self, cls, url, url_args=None, good_data=None, bad_data=None):
        count = cls.objects.all().count()
        create_response = self.client.post(reverse(url, args=url_args), good_data).json()
        self.assertEqual(create_response['status'], 201)
        self.assertEqual(create_response['pk'], cls.objects.last().pk)
        self.assertEqual(cls.objects.all().count(), count+1)
        
        if bad_data:
            bad_create_response = self.client.post(reverse(url, args=url_args), bad_data).json()
            self.assertEqual(bad_create_response['status'], 400)

    def   read_test(self, cls, url, url_args=None):
        count = cls.objects.all().count()
        read_response = self.client.get(reverse(url, args=url_args)).json()
        self.assertEqual(cls.objects.first().id, read_response[0]['id'])
        
    def update_test(self, cls, url, url_args=None, good_data=None, bad_data=None):
        count = cls.objects.all().count()
        create_response = self.client.post(reverse(url, args=url_args), good_data).json()
        self.assertEqual(create_response['status'], 200)
        self.assertEqual(create_response['pk'], cls.objects.last().pk)
        self.assertEqual(cls.objects.all().count(), count)
        
        if bad_data:
            bad_create_response = self.client.post(reverse(url, args=url_args), bad_data).json()
            self.assertEqual(bad_create_response['status'], 400)

    def delete_test(self, cls, url, url_args=None):
        count = cls.objects.all().count()
        newest_object = cls.objects.last()
        from collections import Iterable
        if isinstance(url_args, Iterable):
            url_args = list(url_args)
            url_args.append(newest_object.id)
        else:
            url_args = (newest_object.id,)
        resp_url = reverse(url, args=url_args)
        delete_response = self.client.post( resp_url ).json()
        self.assertEqual( delete_response['status'], 200)
        self.assertEqual( cls.objects.all().count(), count-1)

    def test_api(self):
        self.create_test(Session,
                         "add-session",
                         good_data = {'name': 'session 12', 'date': '2020-05-06', 'description':'new session for album grodt'},
                         bad_data = {'name': 'session 12', 'date': '2020-05-06'})
        session = Session.objects.last()
        self.create_test(Track,
                         "add-track",
                         url_args = (session.hash_string,),
                         good_data = {'name': 'manymen', 'session': session.id},
                         bad_data = {'name': 'manymen'})
        track = Track.objects.last()
        with open(settings.MEDIA_ROOT + '/test/pinknoise.aiff', 'rb') as f:
            self.create_test(Version,
                             "add-version",
                             url_args = (session.hash_string,track.id,),
                             good_data = {'track':track.id, 'audiofile':f, 'info':"test3"},
                             bad_data = {'track':track.id, 'info':"test3"})
        self.read_test(Session, "session-list") 
        self.read_test(Track, "track-list", url_args=(session.hash_string,))
        last_version = Version.objects.last()
        self.read_test(Version, "version-list", url_args=(session.hash_string, last_version.track.id))

        self.create_test(VersionComment,
                             "add-versioncomment",
                             url_args = (session.hash_string, track.id, last_version.id),
                             good_data = {
                                 'text':"i like this",
                                 'who':"stevie wonder"
                             },
                             bad_data = { 'text':"i like this" })

        self.read_test(VersionComment, "versioncomment-list",url_args=(session.hash_string, track.id, last_version.id)) 
        last_versioncomment = VersionComment.objects.last()

        self.create_test(TimeComment,
                         "add-timecomment",
                         url_args = (session.hash_string, track.id, last_version.id, "0.5"),
                         good_data = { 'text': "this is the middle of the song! yee haw", 'who': "nobody"},
                         bad_data =  { 'who': "nobody"})

        #self.read_test(TimeComment, "timecomment-list", url_args=(session.hash_string, track.id, last_version.id)) 
        self.read_test(TimeThread, "timethread-list", url_args=(session.hash_string, track.id, last_version.id))

        last_timecomment = TimeComment.objects.last()
        last_session = Session.objects.last()
        self.update_test(Session,
                         "update-session",
                         url_args = (last_session.id,),
                         good_data = {'name': "asdfyolo",
                                      'description': "o what a nice sess'",
                                      'date': '1970-01-01'},
                         bad_data = {'name': "asdfyolo"})

        last_track = Track.objects.last()
        self.update_test(Track,
                         "update-track",
                         url_args = (session.hash_string, last_track.id),
                         good_data = {'name': "hallo, hurz", 'session': last_session.id},
                         bad_data = {'name': "yolo"})

        self.update_test(Version,
                         "update-version-info",
                         url_args = (session.hash_string, last_version.track.id, last_version.id,),
                         good_data = {'track': last_version.track.id, 'info': "qwerty was here"},
                         bad_data = {'info': "qwert's dead"})

        self.update_test(VersionComment,
                         "update-versioncomment",
                         url_args = (session.hash_string, last_versioncomment.thread.version.track.id, last_versioncomment.thread.version.id, last_versioncomment.id,),
                         good_data = { 'text': "yol0", 'who': last_versioncomment.who },
                         bad_data = { 'who': last_versioncomment.who })

        self.update_test(TimeComment,
                         "update-timecomment",
                         url_args = (session.hash_string, last_versioncomment.thread.version.track.id, last_versioncomment.thread.version.id, last_timecomment.id,),
                         good_data = {'text': "hurz", 'who': last_timecomment.who },
                         bad_data = { 'who': last_timecomment.who })

        self.delete_test(TimeComment, "delete-timecomment", url_args=(session.hash_string, last_track.id, last_version.id))
        self.delete_test(VersionComment, "delete-versioncomment", url_args=(session.hash_string, last_track.id, last_version.id))
        self.delete_test(Version, "delete-version", url_args=(session.hash_string, last_track.id))
        self.delete_test(Track, "delete-track", url_args=(session.hash_string,))
        self.delete_test(Session, "delete-session")
