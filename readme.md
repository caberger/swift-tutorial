# Tutorials for Swift Programming

## Material

We use the [Stanford University's course CS193p (Developing Applications for iOS using SwiftUI)](https://cs193p.sites.stanford.edu/) course for these tutorials.

To download the material in .pdf format change to the ./scripts folder in your bash shell and run the following:

```bash
./cs193p-download.sh
```

This will download the course materials to the ./target subfolder of the scripts folder.

## Swift on other platforms

See [remote/readme.md](./remote/readme.md)

## Swift App sending sensor values to an MQTT Server

As an example application there is the [sensors](./sensors) folder, which sends the current location to am MQTT-Server.

## start it with docker

```bash
docker run --rm -it --device /dev/kvm -p 50922:10022 -v/dev/snd:/dev/snd -v /tmp/.X11-unix:/tmp/.X11-unix -e "DISPLAY=${DISPLAY:-:0.0}" -e GENERATE_UNIQUE=true -e MASTER_PLIST_URL='https://raw.githubusercontent.com/sickcodes/osx-serial-generator/master/config-custom.plist' docker-osx
```