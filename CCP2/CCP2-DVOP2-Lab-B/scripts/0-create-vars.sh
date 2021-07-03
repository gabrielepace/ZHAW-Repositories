#!/usr/bin/env bash

########################################################################################
## 0. read and create variables containing required current and new app- and host-name.
########################################################################################

# Your ENV variables (Should be set externally, e.g. export CF_CRED_USR="myUsername123")
# CF_API=
# CF_SHARED_DOMAIN=
# CF_ORG=
# CF_SPACE=
# CF_CRED_USR=
# CF_CRED_PSW=

manifest=${1:-manifest.yml}

temp_suffix="-new"

# Read current (blue) from manifest.yml
app_name=$(grep " name: " ${manifest} | sed 's/.*://;s/ //g')
route=$(grep " - route: " ${manifest} | sed 's/.*://;s/ //g')
domain=${route#*.*}
hostname=${route%%.*}

# Set default values
: "${domain:="${CF_SHARED_DOMAIN}"}"
: "${hostname:="${app_name}"}"

# Set up temporary app (green)
temp_app_name="${app_name}${temp_suffix}"
temp_domain="${domain}"
temp_hostname="${hostname}${temp_suffix}"

