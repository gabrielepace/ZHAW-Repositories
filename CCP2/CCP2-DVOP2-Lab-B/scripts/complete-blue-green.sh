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

echo "app-name: ${app_name}"
echo "hostname: ${hostname}"
echo "domain: ${domain}"
echo "temp-app-name: ${temp_app_name}"
echo "temp-hostname: ${temp_hostname}"
echo "temp-domain: ${temp_domain}"


##############################################
## 1. Deploy new (green) app to temporary URL
##############################################

# CF Login
cf login -u "${CF_CRED_USR}" -p "${CF_CRED_PSW}" -o "${CF_ORG}" -s "${CF_SPACE}" -a "${CF_API}"

# Push new app (green) in parallel to existing app (blue); but do not automatically create a route
cf push "${temp_app_name}" -f "${manifest}" --no-route

# Create (map) temporary route to new app (green) [ http://temp_hostname.temp_domain -> temp_app_name ]
cf map-route "${temp_app_name}" "${temp_domain}" -n "${temp_hostname}"

#########################################################################################
## 2. verify if new (green) application is available and returning correct response code
#########################################################################################

max_health_checks=20
test_path="/hello"
expected_response="200"

# Wait for new app (green) to be ready to serve on the temporary URL
iterations=0
while [[ "${iterations}" -lt "${max_health_checks}" ]]
do
  response=$(curl -siL -w "%{http_code}" -o /dev/null "${temp_hostname}.${temp_domain}${test_path}")
  if [[ "${response}" == "${expected_response}" ]]; then
    printf "\n%s" "Got expected ${response} response"
    break
  else
    iterations=$(( iterations + 1 ))
    sleep 3 && printf "\n%s" "Waiting for ${expected_response} response... Got ${response} (${iterations}/${max_health_checks})"
  fi
done

# if it fails delete the temporary route and stop the new app
if [[ "${iterations}" == "${max_health_checks}" ]]; then
  printf "\n%s\n\n" "Couldn't get ${expected_response} response. Reverting..."

  # Delete temporary route [ X http://temp_hostname.temp_domain ]
  cf delete-route "${temp_domain}" -n "${temp_hostname}" -f

  # Stop temporary app
  cf stop "${temp_app_name}"

  exit 1
fi

###############################################################################
## 3. switch traffic from old (blue) to new (green) app by
## - first adding the main (blue) URL to it -> both versions are called
## - second remove the main URL from the old app -> only new version is called
###############################################################################

# Load balance route between old (Blue) and new (Green)
# Add (map) new (green) app to main URL [ http://hostname.domain -> temp_app_name ]
cf map-route "${temp_app_name}" "${domain}" -n "${hostname}"

# Remove (unmap) old (Blue) app from load balancing [ X http://hostname.domain -> app_name ]
if cf app "${app_name}" > /dev/null; then  # only if app exists
    echo "Remove old app from load balancing"
    cf unmap-route "${app_name}" "${domain}" -n "${hostname}"
fi


#####################################################
## 4. Cleanup and prepare for next blue-green deploy
#####################################################

# Delete temporary route [ X http://temp_hostname.temp_domain ]
cf delete-route -f "${temp_domain}" -n "${temp_hostname}"

# Delete old app [ X app_name ]
if cf app "${app_name}" > /dev/null; then  # only if app exists
    echo "Delete old app"
    cf delete -f -r "${app_name}"
fi

# Rename new app (green) to old app name (blue) [ temp_app_name -> app_name ]
cf rename "${temp_app_name}" "${app_name}"