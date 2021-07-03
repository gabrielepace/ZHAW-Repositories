from django.contrib import admin
from .models import Track, Version, Session, VersionComment, TimeComment, VersionThread, TimeThread

class VersionInline(admin.TabularInline):
    model = Version
    fields = ('audiofile', 'info', 'track')
    extra = 1

class TrackAdmin(admin.ModelAdmin):
    inlines = (VersionInline,)
    list_display = ('name', 'session', 'version_count', 'current_version')
    list_filter = ('session',)
    fields = ('name', 'session')
admin.site.register(Track, TrackAdmin)

class VersionAdmin(admin.ModelAdmin):
    fields = ('audiofile', 'info', 'track')
    list_display = ('track', 'info', 'length', 'filesize', 'date_created', 'last_modified')
    list_filter = ('last_modified', 'date_created')

    actions = ('update_file_info', 'calc_amplispectr')
    def update_file_info(self, request, queryset):
        for obj in queryset.all():
            obj.update_file_info()
    update_file_info.short_description = "Update Audio-File information"

    def calc_amplispectr(self, request, queryset):
        for obj in queryset.all():
            obj.calc_amplispectrogram()
    calc_amplispectr.short_description = "Calculate AmpliSpectrogram"

admin.site.register(Version, VersionAdmin)

class SessionAdmin(admin.ModelAdmin):
    list_display = ('name', 'date', 'hash_string', 'track_count', 'version_count')
    list_filter = ('date',)
    
admin.site.register(Session, SessionAdmin)
admin.site.register(VersionComment)
admin.site.register(TimeComment)
class VersionThreadAdmin(admin.ModelAdmin):
    list_display = ('version', 'track', 'comment_count')
admin.site.register(VersionThread)

class TimeThreadAdmin(admin.ModelAdmin):
    list_display = ('version', 'relative_time', 'track', 'comment_count')
admin.site.register(TimeThread, TimeThreadAdmin)
