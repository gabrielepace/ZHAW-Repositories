# important django commands

## how to setup django workspace
 - setup virtualenv `only one of the following, not both`(inside or outside repo?)
> virtualenv -p python3 .
> python3 -m venv .

 - activate virtualenv
> source bin/activate

 - install all the python dependencies (they are being installed inside your venv and are not accessible otherwise)
> pip install -r requirements

 - initiate django project as prjname (choose your own)
> django-admin startproject [prjname]
> cd [prjname]

 - now we can do funny stuff, like
> ./manage.py startapp tracker


 - if we want to 'activate' the new app, we will have to configure [prjname]/settings.py (section APPS), and adjust [prjname]/urls.py

## most important ./manage.py commands
 - (start development server)
> ./manage.py runserver

 - make db migrations and migrate
> ./manage.py makemigrations
> ./manage.py migrate

 - test (run unit tests)
> ./manage.py test

 - shell (start python shell with env loaded)
> ./manage.py shell

 - create app superuser
> ./manage.py createsuperuser

 - check deployment readiness
> ./manage.py check

## references
 - [venv](https://docs.python.org/3/library/venv.html) 
 - [django docs](https://docs.djangoproject.com/en/3.0/)
 - [django quick install guide](https://docs.djangoproject.com/en/3.0/intro/install/)
