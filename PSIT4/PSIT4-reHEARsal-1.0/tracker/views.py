from django.http import JsonResponse, HttpResponse, HttpResponseRedirect
from django.middleware.csrf import get_token
from django.shortcuts import get_object_or_404, redirect, render
from django.views.generic import CreateView, ListView, DetailView, UpdateView, DeleteView
from django.views.decorators.csrf import csrf_exempt
from django.urls import reverse

from .models import Session, Track, Version, VersionComment, VersionThread, TimeComment, TimeThread
from .utils import obj2json
from .forms import SessionForm, TrackForm, UpdateVersionInfoForm, VersionForm, VersionCommentForm, TimeCommentForm

import logging
logger = logging.getLogger(__name__)

class JSONResponseMixin:
    def render_to_json_response(self, context, **response_kwargs):
        return JsonResponse( self.get_data(context), safe=False, **response_kwargs)

    def render_to_response(self, context, **response_kwargs):
        return self.render_to_json_response(context, **response_kwargs)

    def get_data(self,context):
        return context

class JSONDetailView(JSONResponseMixin, DetailView):
    def get_data(self, context):
        return obj2json(context['object'])

class JSONListView(JSONResponseMixin, ListView):
    def get_data(self, context):
        return obj2json(context['object_list'])

class JSONEditView(JSONResponseMixin):
    template_name = 'js_index.html'

    def get(self, request, *args, **kwargs):
        return JsonResponse({'status': 403}) # forbidden

    @csrf_exempt
    def dispatch(self, *args, **kwargs):
        return super(JSONEditView, self).dispatch(*args, **kwargs)

    def form_valid(self, form):
        obj = form.save()
        return self.render_to_json_response({'status': 200, 'pk': obj.pk})
        
    def form_invalid(self, form):
        logger.debug("form invalid: {}".format(self.request.POST))
        logger.debug(form)
        return JsonResponse({'status': 400}) # bad request
class JSONCreateView(JSONEditView, CreateView):
    def form_valid(self, form):
        obj = form.save()
        return self.render_to_json_response({'status': 201, 'pk': obj.pk})
class JSONDeleteView(JSONResponseMixin, DeleteView):
    template_name = 'js_index.html'

    def delete(self, request, *args, **kwargs):
        self.object = self.get_object()
        self.object.delete()
        return super(JSONDeleteView, self).render_to_json_response({'status': 200})

    @csrf_exempt
    def dispatch(self, *args, **kwargs):
        return super(JSONDeleteView, self).dispatch(*args, **kwargs)

def jCSRF(request):
    return JsonResponse({"csrf_token": get_token(request)}, safe=False)


class CreateSession(JSONCreateView):
    model = Session
    form_class = SessionForm

class SessionList(JSONListView):
    model = Session
    ordering = ['-date', 'name']

class SessionDetail(JSONDetailView):
    template_name = 'js_index.html'
    model = Session
    slug_url_kwarg = 'hash'
    slug_field = 'hash_string'	

class UpdateSession(JSONEditView, UpdateView):
    model = Session
    form_class = SessionForm

class DeleteSession(JSONDeleteView):
    model = Session


class TrackView:
    pk_url_kwarg = "tpk"
class CreateTrack(JSONCreateView, TrackView):
    model = Track
    form_class = TrackForm

    def form_valid(self, form):
            form = TrackForm(self.request.POST)
            t = form.save()
            return JsonResponse({'status': 201, 'pk': t.pk,'track_name': t.name})

class TrackList(JSONListView):
    model = Track
    ordering = ['session', 'name']

class TrackDetail(TrackView, JSONDetailView):
    template_name = 'js_index.html'
    model = Track

class UpdateTrack(TrackView, JSONEditView, UpdateView):
    model = Track
    form_class = TrackForm

class DeleteTrack(TrackView, JSONDeleteView):
    model = Track


class VersionView:
    pk_url_kwarg = "vpk"
class CreateVersion(JSONCreateView, VersionView):
    template_name = 'js_index.html'
    model = Version
    form_class = VersionForm

    def post(self, request, *args, **kwargs):
        form = self.get_form()
        if form.is_valid():
            return self.form_valid(form)
        else:
            return self.form_invalid(form)

class VersionList(JSONListView):
    model = Version
    ordering = ['-date_created']

class VersionDetail(VersionView, JSONDetailView):
    template_name = 'js_index.html'
    model = Version 

class UpdateVersion(VersionView, JSONEditView, UpdateView):
    model = Version
    form_class = UpdateVersionInfoForm

class DeleteVersion(VersionView, JSONDeleteView):
    model = Version


class CommentView:
    pk_url_kwarg = "cpk"
class CreateComment(CommentView):
    def form_valid(self, form):
        form = self.form_class(self.request.POST)
        thread = self.get_thread()
        new_comment = form.save(commit=False)
        new_comment.thread = thread
        new_comment.save()
        return JsonResponse({'status': 201, "pk": new_comment.id})

    def get_thread(self):
        thread,created = self.thread_class.objects.get_or_create( version_id=self.kwargs['vpk'] )
        return thread
        
class CreateVersionComment(CreateComment, JSONCreateView):
    model = VersionComment
    form_class = VersionCommentForm
    thread_class = VersionThread

class VersionCommentList(JSONListView):
    model = VersionComment
    ordering = ['when']
    def get_context_data(self,**kwargs):
        return {'object_list': VersionComment.objects.filter(thread = VersionThread.objects.get(version=self.kwargs['vpk'])) }

class UpdateVersionComment(CommentView, JSONEditView, UpdateView):
    model = VersionComment
    form_class = VersionCommentForm

class DeleteVersionComment(CommentView, JSONDeleteView):
    model = VersionComment


class CreateTimeComment(CreateComment, JSONCreateView):
    model = TimeComment
    form_class = TimeCommentForm
    thread_class = TimeThread

    def get_thread(self):
        try:
            progress = float(self.kwargs['progress'])
        except ValueError:
            return self.form_invalid()
        thread, created = self.thread_class.objects.get_or_create( version_id=self.kwargs['vpk'], relative_time=progress)
        return thread
        
class TimeCommentList(JSONListView):
    model = TimeComment
    ordering = ['when']
    def get_context_data(self,**kwargs):
        return {'object_list': TimeComment.objects.filter(thread=TimeThread.objects.filter(version=self.kwargs['vpk'])) }

class UpdateTimeComment(CommentView, JSONEditView, UpdateView):
    model = TimeComment
    form_class = TimeCommentForm
class DeleteTimeComment(CommentView, JSONDeleteView):
    model = TimeComment

class TimeThreadList(JSONListView):
    model = TimeThread
    ordering = ['relative_time']

    def get_context_data(self, **kwargs):
        return {'object_list': TimeThread.objects.filter(version=self.kwargs['vpk'])}
