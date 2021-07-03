#!/usr/bin/env bash

###############################################################################
## 3. switch traffic from old (blue) to new (green) app by
## - first adding the main (blue) URL to it -> both versions are called
## - second remove the main URL from the old app -> only new version is called
###############################################################################

# provide path to your manifest as parameter (e.g. application/manifest.yml  ; default: manifest.yml)
# detect and set the following variables (app_name, domain, hostname, temp_app_name, temp_domain, temp_hostname)
source `dirname "$0"`/0-create-vars.sh "${1}"

# Load balance route between old (Blue) and new (Green)
# Add (map) new (green) app to main URL [ http://hostname.domain -> temp_app_name ]
cf map-route "${temp_app_name}" "${domain}" --hostname "${hostname}"

# Remove (unmap) old (Blue) app from load balancing [ X http://hostname.domain -> app_name ]
if cf app "${app_name}" > /dev/null; then  # only if app exists
    echo "Remove old app from load balancing"
    cf unmap-route "${app_name}" "${domain}" --hostname "${hostname}"
fi
