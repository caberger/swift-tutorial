#!/usr/bin/env bash
# remove all comtainers, images, volumes from podman

podman container prune --force
podman image prune --force
podman volume prune --force
echo "rmi..."
podman rmi -f $(podman images -q)