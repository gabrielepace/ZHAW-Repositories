from .models import Version, Track, Session, VersionComment, TimeComment
from django import forms

class VersionForm(forms.ModelForm):
    class Meta:
        model = Version
        fields = ['track', 'audiofile', 'info']

class UpdateVersionInfoForm(forms.ModelForm):
    class Meta:
        model = Version
        fields = ['track', 'info']

class TrackForm(forms.ModelForm):
    class Meta:
        model = Track
        fields = ['name','session']

class SessionForm(forms.ModelForm):
    class Meta:
        model = Session
        fields = ['name','description', 'date']

class VersionCommentForm(forms.ModelForm):
    class Meta:
        model = VersionComment
        fields = ['text','who']

class TimeCommentForm(forms.ModelForm):
    class Meta:
        model = TimeComment
        fields = ['text','who']
