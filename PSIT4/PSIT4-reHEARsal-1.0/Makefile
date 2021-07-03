PYTHON := ../bin/python
MNG := $(PYTHON) manage.py
NPM := npm --prefix frontend
PIP := ../bin/pip

.PHONY: all deploy backend backend-test frontend-build frontend-test migration test-all setup su

all: deploy
deploy: frontend-build backend

backend: setup migration checkmodels

backend-test:
	$(MNG) test

checkmodels:
	$(MNG) checkModels --delete

frontend-build: setup
	$(NPM) --prefix frontend run build

frontend-test: frontend-build
	$(NPM) run serve&
	$(NPM) run test:nightwatchjs

migration:
	$(MNG) makemigrations
	$(MNG) migrate

test-all: frontend-test backend-test 

setup:
	$(PIP) install -r requirements
	$(NPM) install

su:
	$(MNG) createsuperuser
