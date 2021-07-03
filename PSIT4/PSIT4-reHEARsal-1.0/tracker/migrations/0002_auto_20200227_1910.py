# Generated by Django 3.0 on 2020-02-27 19:10

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('tracker', '0001_initial'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='versioncomment',
            name='where',
        ),
        migrations.AddField(
            model_name='versioncomment',
            name='text',
            field=models.TextField(default=''),
            preserve_default=False,
        ),
        migrations.CreateModel(
            name='VersionThread',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('version', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='tracker.Version')),
            ],
        ),
        migrations.CreateModel(
            name='TrackThread',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('track', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='tracker.Track')),
            ],
        ),
        migrations.CreateModel(
            name='TrackComment',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('text', models.TextField()),
                ('when', models.DateTimeField(auto_now_add=True)),
                ('last_modified', models.DateTimeField(auto_now=True)),
                ('track_ref', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tracker.Track')),
                ('what', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tracker.TrackThread')),
                ('who', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
            options={
                'abstract': False,
            },
        ),
        migrations.CreateModel(
            name='TimeThread',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('relative_time', models.FloatField()),
                ('version', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='tracker.Version')),
            ],
        ),
        migrations.CreateModel(
            name='TimeComment',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('text', models.TextField()),
                ('when', models.DateTimeField(auto_now_add=True)),
                ('last_modified', models.DateTimeField(auto_now=True)),
                ('where', models.FloatField(null=True)),
                ('version_ref', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tracker.Version')),
                ('what', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tracker.VersionThread')),
                ('who', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
            options={
                'abstract': False,
            },
        ),
        migrations.AlterField(
            model_name='versioncomment',
            name='what',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='tracker.VersionThread'),
        ),
    ]
