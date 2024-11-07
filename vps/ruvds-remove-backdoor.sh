#!/bin/bash

systemctl stop vpsguard
systemctl disable vpsguard
systemctl stop vmbus-dev-wait.path
systemctl disable vmbus-dev-wait.path
rm /usr/bin/vpsguard
