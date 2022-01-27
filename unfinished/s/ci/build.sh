#!/bin/bash

if [[ $PROJECT = "web" ]]; then
    cd web
    npm test -- --maxWorkers=4
    npm run build
    codecov -F web
    exit 0
fi

if [[ $PROJECT = "server" ]]; then
    cd server
    sbt clean coverage test coverageReport compile
    bash <(curl -s https://codecov.io/bash) -F server
    exit 0
fi
