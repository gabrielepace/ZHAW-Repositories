# Generated by Django 3.0 on 2020-05-16 11:40

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('tracker', '0009_auto_20200515_1948'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='session',
            name='artists',
        ),
    ]
