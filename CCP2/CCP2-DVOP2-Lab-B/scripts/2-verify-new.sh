#!/usr/bin/env bash

#########################################################################################
## 2. verify if new (green) application is available and returning correct response code
#########################################################################################

# provide path to your manifest as parameter (e.g. application/manifest.yml  ; default: manifest.yml)
# detect and set the following variables (app_name, domain, hostname, temp_app_name, temp_domain, temp_hostname)
source `dirname "$0"`/0-create-vars.sh "${1}"

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
