#!/usr/bin/env bash
echo "Execute shell Start"
cd `dirname $0`
sh leasing-applications-eureka-server/start.sh
sh leasing-applications-eureka-zuul/start.sh
sh leasing-applications-identity/start.sh ${1}
sh leasing-applications-company/start.sh ${1}
sh leasing-applications-subject/start.sh ${1}
echo "Execute shell Finish"
