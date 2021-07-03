from django.urls import include, path
from .views import *

timecommentpatterns = [
    path('<str:progress>/add', CreateTimeComment.as_view(), name="add-timecomment"),
    path('<int:cpk>/update/', UpdateTimeComment.as_view(), name="update-timecomment"),
    path('<int:cpk>/delete/', DeleteTimeComment.as_view(), name="delete-timecomment"),
    path('threads', TimeThreadList.as_view(), name="timethread-list"),
]
versioncommentpatterns = [
    path('add', CreateVersionComment.as_view(), name="add-versioncomment"),
    path('<int:cpk>/update/', UpdateVersionComment.as_view(), name="update-versioncomment"),
    path('<int:cpk>/delete/', DeleteVersionComment.as_view(), name="delete-versioncomment"),
    path('', VersionCommentList.as_view(), name="versioncomment-list"),
]
versionpatterns = [
    path('add', CreateVersion.as_view(), name="add-version"),
    path('<int:vpk>/comments/', include(versioncommentpatterns)),
    path('<int:vpk>/timecomments/', include(timecommentpatterns)),
    path('<int:vpk>/update/', UpdateVersion.as_view(), name="update-version-info"),
    path('<int:vpk>/delete/', DeleteVersion.as_view(), name="delete-version"),
    path('<int:vpk>/', VersionDetail.as_view(), name="version-detail"),
    path('', VersionList.as_view(), name="version-list"),
]
trackpatterns = [
    path('add', CreateTrack.as_view(), name="add-track"),
    path('<int:tpk>/', TrackDetail.as_view(), name="track-detail"),
    path('<int:tpk>/versions/', include(versionpatterns)),
    path('<int:tpk>/update/', UpdateTrack.as_view(), name="update-track"),
    path('<int:tpk>/delete/', DeleteTrack.as_view(), name="delete-track"),
    path('', TrackList.as_view(), name="track-list"),
]

urlpatterns = [
    path('csrf', jCSRF, name="csrf"),

    path('sessions/add', CreateSession.as_view(), name="add-session"),
    path('sessions/<str:hash>', SessionDetail.as_view(), name="session-detail"),
    path('sessions/<str:hash>/tracks/', include(trackpatterns)),
    path('sessions/<int:pk>/update', UpdateSession.as_view(), name="update-session"),
    path('sessions/<int:pk>/delete', DeleteSession.as_view(), name="delete-session"),
    path('', SessionList.as_view(), name="session-list" ), # to be excluded in production
]
