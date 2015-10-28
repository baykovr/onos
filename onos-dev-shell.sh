#!/bin/bash
# requires you to be in bash
JAVA_VERSION=8
export JAVA_HOME=/usr/lib/jvm/java-$JAVA_VERSION-openjdk
export PATH="$PATH:$JAVA_HOME/bin"

export ONOS_ROOT=$(pwd) 
source $ONOS_ROOT/tools/dev/bash_profile

alias l=ls
