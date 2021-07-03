from django.core.management.base import BaseCommand, CommandError
from django.conf import settings
from tracker.models import *
import os

class Command(BaseCommand):
    help = 'Checks database for illegal objects (eg Track without Version)'

    def add_argument(self, parser):
        parser.add_argument(
            '--delete',
            action='store_true',
            help='directly delete Track objects without Versions associated to')
    def handle(self, *args, **options):
        self.stdout.write("checking Track objects")
        bad_tracks = []
        for t in Track.objects.all():
            if t.version_count == 0:
                bad_tracks.append(t)
        if len(bad_tracks)>0:
            self.stderr.write("found the following bad track objects:")
            for t in bad_tracks:
                self.stderr.write(str(t))
            if not options['delete']:
                self.stdout.write("shall i delete {} objects? [yN]".format(len(bad_tracks)))
                i = input().lower()
                if i=='y' or i=='yes':
                    options['delete'] = True
                else:
                    self.stdout.write("abort")
                    return
            if options['delete']:
                for t in bad_tracks:
                    t.delete()
        else:
            self.stdout.write("no bad Track-objects found!")
                        
