#!/usr/bin/env bash

#####################################################
## 4. Cleanup and prepare for next blue-green deploy
#####################################################

# provide path to your manifest as parameter (e.g. application/manifest.yml  ; default: manifest.yml)
# detect and set the following variables (app_name, domain, hostname, temp_app_name, temp_domain, temp_hostname)
source `dirname "$0"`/0-create-vars.sh "${1}"

# Delete temporary route [ X http://temp_hostname.temp_domain ]
cf delete-route -f "${temp_domain}" --hostname "${temp_hostname}"

# Delete old app [ X app_name ]
if cf app "${app_name}" > /dev/null; then  # only if app exists
    echo "Delete old app"
    cf delete -f -r "${app_name}"
fi

# Rename new app (green) to old app name (blue) [ temp_app_name -> app_name ]
cf rename "${temp_app_name}" "${app_name}"
