import numpy  as np
import matplotlib
matplotlib.use('Agg')
from matplotlib import pyplot as plt
import logging
logger = logging.getLogger(__name__)

from itertools import chain

def to_dict(instance):
    opts = instance._meta
    data = {}
    for f in chain(opts.concrete_fields, opts.private_fields):
        data[f.name] = f.value_from_object(instance)
    for f in opts.many_to_many:
        data[f.name] = [obj2json(i) for i in f.value_from_object(instance)]
    from .models import Session, Track, Version, TimeThread
    if type(instance) in (Session, Track, Version, TimeThread):
        data.update( instance.to_dict() )
    return data

def obj2json(obj): # returns a dictionary containng the information of an object
    from django.db.models.query import QuerySet
    if type(obj) == QuerySet:
        a = []
        for o in obj:
            a.append( obj2json(o) )
        return a
    return to_dict(obj)


def audio2wav(path): # we use ffmpeg to convert *any* audio file to wav first
    import os
    os.system("ffmpeg -n -loglevel panic -i {} {}".format(path,path+".wav"))

def get_random_line(path):
    import random
    with open(path) as f:
        lines = f.readlines()
    out = lines[random.randint(0, len(lines)-1)]
    if out[-1] == '\n':
        return out[:-1]
    return out

class AmpliSpectrogram:
    def __init__(self, path, out, plot_size=1024, sampling_rate=44100):
        self.path = path
        self.out  = out
        self.plot_size = plot_size
        self.sampling_rate = sampling_rate
        self.read_wave_file() # sets self.stereo and self.frame_count
        self.ms_from_stereo() # sets self.left, self.right, self.mid, self.side
        self.window_size = len(self.mid)//self.plot_size
        logger.debug("initialized {}".format(str(self)))

    def __str__(self):
        return "AmpliSpectrogram('{}', '{}')".format(self.path, self.out)
    
    def read_wave_file(self):
        from wave import open as open_wave
        w = open_wave( self.path, 'rb' )
        self.frame_count = w.getnframes() # number of all frames (both channels!)
        f = w.readframes( self.frame_count )
        self.stereo = np.frombuffer( f, dtype=np.int16 ).astype(np.int64)
        logger.debug("read wave data at {}".format(str(self)))

    def ms_from_stereo(self):
        self.left  = self.stereo[0:][::2]
        self.right = self.stereo[1:][::2]
        self.mid   = self.left + self.right
        self.side  = self.left - self.right
        logger.debug("calculated MS for {}".format(str(self)))
    
    def calc_rms(self):
        mid_sq  = np.abs(np.square( self.mid )) # there is some pretty funky shit goin on in np.square()......
        side_sq = np.abs(np.square( self.side ))
        mids  = list()
        sides = list()
        for i in range(self.plot_size-1):
            mids.append( mid_sq[ i*self.window_size : (i+1)*self.window_size].mean() )
            sides.append( side_sq[ i*self.window_size : (i+1)*self.window_size].mean() )
        self.mid_rms  = np.sqrt(np.array(mids))
        self.side_rms = np.sqrt(np.array(sides))
        logger.debug("calculated RMS for {}".format( str(self) ))

    def plot_single_fft_frame(self, channel, path, frame_index=0):
        frame = channel[
            frame_index*len(self.freqs) :
            frame_index*len(self.freqs)+len(self.freqs) ]
        fig, ax  = plt.subplots()
        ax.set_ylim([1,10**9])
        ax.set_yscale('log')
        ax.semilogx(self.freqs, frame)
        ax.grid()
        plt.savefig(path)
        plt.close()
    
    def plot_frames(self, A, filename):
        for a in A:
            self.plot_single_fft_frame(self.mid, filename.format(a), a)

    def plot_rms_data(self, colors):
        indices = np.arange(self.plot_size-1)
        plt.figure(
            figsize = (9,2),
            dpi = 500,
            facecolor = (0,0,0,0),
            edgecolor = (0,0,0,0),
            frameon = False,
            tight_layout = True)
        p1 = plt.bar(
            indices,
            self.mid_rms,
            1,
            linewidth = 0,
            log = True,
            color = colors[0])
        p2 = plt.bar(
            indices,
            self.side_rms,
            1,
            bottom = self.mid_rms,
            linewidth = 0,
            log = True,
            color = colors[1])
        plt.axis('off')
        plt.tight_layout(pad=0, h_pad=None, w_pad=None, rect=(0.05,0.05,0.95,0.95))
        plt.savefig(self.out, transparent=True)#, bbox_inches='tight')
        plt.close()

    def plot(self):
        self.calc_rms()
        self.plot_rms_data(['black','dimgrey'])
