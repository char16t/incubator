#!/bin/bash

# FTP server details
HOST="ftp.ru"
USER="user"
PASSWORD="password"

# Local directory to copy
LOCAL_DIR=$1

# Remote directory to copy to
REMOTE_DIR="/test"

# Connect to FTP server in passive mode
ftp -p -n $HOST <<END_SCRIPT
quote USER $USER
quote PASS $PASSWORD
mkdir $REMOTE_DIR/$LOCAL_DIR
cd $REMOTE_DIR/$LOCAL_DIR
lcd $LOCAL_DIR
prompt
mput *
quit
END_SCRIPT
exit 0
