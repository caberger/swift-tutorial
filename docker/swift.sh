#!/usr/bin/env bash 
# runs docker e.g. docker run --privileged -i -t --rm --name swiftfun swiftdocker/swift:latest bash
# but prepares swift for comfortable usage.
# Author: Christian Aberger (2022)

DEMO_FILE=HelloWorld.swift
mkdir -p ./src
if [[ ! -f ./src/${DEMO_FILE} ]]
then
    cp ${DEMO_FILE} ./src/
fi
#docker run --privileged -i -t --rm -v "$PWD/src/":"/mnt" --workdir /mnt --name swift swiftdocker/swift:latest 
podman run --privileged -i -t --rm -v "$PWD/src/":"/mnt" --workdir /mnt --name swift swiftdocker/swift:latest 
