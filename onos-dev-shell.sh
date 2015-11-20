#!/bin/bash
# requires you to be in bash

JAVA_VERSION=8
export JAVA_HOME=/usr/lib/jvm/java-$JAVA_VERSION-openjdk
export PATH="$PATH:$JAVA_HOME/bin"

export ONOS_IP="127.0.0.1"
export ONOS_ROOT=$(pwd)
source $ONOS_ROOT/tools/dev/zsh_profile

# 11.05.15
# For some reason the default credentials fail, overwrite them.
# https://groups.google.com/a/onosproject.org/forum/#!searchin/onos-dev/credentials/onos-dev/JYl3mXDBUos/2Lf2JT1MBwAJ
export ONOS_WEB_USER=karaf
export ONOS_WEB_PASS=karaf
