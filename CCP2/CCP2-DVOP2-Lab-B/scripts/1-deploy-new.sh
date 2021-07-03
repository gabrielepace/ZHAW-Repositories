#!/usr/bin/env bash

##############################################
## 1. Deploy new (green) app to temporary URL
##############################################

# provide path to your manifest as parameter (e.g. application/manifest.yml  ; default: manifest.yml)
# detect and set the following variables (app_name, domain, hostname, temp_app_name, temp_domain, temp_hostname)
source `dirname "$0"`/0-create-vars.sh "${1}"

echo "app-name: ${app_name}"
echo "hostname: ${hostname}"
echo "domain: ${domain}"
echo "temp-app-name: ${temp_app_name}"
echo "temp-hostname: ${temp_hostname}"
echo "temp-domain: ${temp_domain}"

# CF Login
cf login -u "${CF_CRED_USR}" -p "${CF_CRED_PSW}" -o "${CF_ORG}" -s "${CF_SPACE}" -a "${CF_API}"

# Push new temporary app (green) in parallel to existing app (blue); but do not automatically create a route
cf push "${temp_app_name}" -f "${manifest}" --no-route

# Create (map) temporary route to new app (green) [ http://temp_hostname.temp_domain -> temp_app_name ]
cf map-route "${temp_app_name}" "${temp_domain}" --hostname "${temp_hostname}"
