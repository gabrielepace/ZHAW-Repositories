# Useful:
#   http://www.catosplace.net/blog/2015/02/11/running-jenkins-in-docker-containers/
#   https://github.com/jenkinsci/docker#preinstalling-plugins
#   https://engineering.riotgames.com/news/jenkins-docker-proxies-and-compose

FROM jenkins/jenkins:2.277.4-lts



LABEL maintainer="Christof Marti <mach@zhaw.ch>"

COPY seed/init.groovy /usr/share/jenkins/ref/init.groovy
COPY seed/settings.xml /usr/share/jenkins/settings.xml
COPY plugins.txt /usr/share/jenkins/plugins.txt

USER root

# Default mysql credentials - you can modify them as you please. You can
# parametrize them so that values are not hardcoded
RUN printf "%s" "rootpassword" > /usr/share/jenkins/mySqlRootPass && \
    printf "%s" "username" > /usr/share/jenkins/mySqlPass && \
    printf "%s" "password" > /usr/share/jenkins/mySqlUser

USER root

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false \
              -Djava.awt.headless=true \
              -Dhudson.model.ParametersAction.keepUndefinedParameters=true 

# Install libs needed by the master worker for building apps
RUN apt-get update && \
    apt-get install -y ruby curl jq && \
    apt-get clean

# Install cf-cli
RUN apt-get -y install apt-transport-https && \
    wget -q -O - https://packages.cloudfoundry.org/debian/cli.cloudfoundry.org.key | apt-key add - && \
    echo "deb http://packages.cloudfoundry.org/debian stable main" | tee /etc/apt/sources.list.d/cloudfoundry-cli.list && \
    apt-get -y update && \
    apt-get -y install cf-cli

# Making docker in docker possible
# USER root
# RUN DEBIAN_FRONTEND=noninteractive apt-get update && apt-get -y install apt-transport-https ca-certificates && \
#     echo "deb https://apt.dockerproject.org/repo debian-jessie main" | tee /etc/apt/sources.list.d/docker.list && \
#     apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D && \
#     DEBIAN_FRONTEND=noninteractive apt-get update && \
#     apt-get install --assume-yes docker-engine && \
#     echo "jenkins ALL=NOPASSWD: /usr/bin/docker" >> /etc/sudoers && \
#     echo "jenkins ALL=NOPASSWD: /usr/local/bin/docker-compose" >> /etc/sudoers && \
#     echo 'Defaults  env_keep += "HOME"' >> /etc/sudoers

# Install plugin
# You can use Jenkins API to generate the list of plugins from a running
# Jenkins instance:
#
#  $ JENKINS_URL="http://user:pass@localhost:8080"
#  $ curl -sSL "${JENKINS_URL}/pluginManager/api/json?depth=1" | \
#    jq -r '.plugins[] | .shortName +":"+ .version' | sort > plugins.txt
#
RUN install-plugins.sh $( paste -sd' ' /usr/share/jenkins/plugins.txt )
