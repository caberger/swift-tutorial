# osx docker

see [Docker-OSX](https://github.com/sickcodes/Docker-OSX)
see [docker linux](https://dev.to/gombosg/running-macos-inside-linux-with-docker-osx-4e1i)

## install XSRV
```bash
sudo apt-get update && sudo apt-get upgrade -y
sudo apt-get install xserver-xorg xinit
sudo nano /etc/X11/xorg.conf
```

Paste the following
```console
Section "ServerFlags" Option "listen" "TCP" EndSection Section "InputDevice" Identifier "Mouse0" Driver "mouse" Option "Protocol" "auto" Option "Device" "/dev/input/mice" Option "ZAxisMapping" "4 5 6 7" EndSection
```

Start the server with
```bash
startx
```

# smaller:
```bash
docker run -it \
    --device /dev/kvm \
    -p 50922:10022 \
    -v /tmp/.X11-unix:/tmp/.X11-unix \
    -e "DISPLAY=${DISPLAY:-:0.0}" \
    -e "QEMU_AUDIO_DRV=none" \
    docker-osx
```
docker run -it -p 50922:10022 -v /tmp/.X11-unix:/tmp/.X11-unix -e "DISPLAY=${DISPLAY:-:0.0}" -e "QEMU_AUDIO_DRV=none" docker-osx
docker run -it -p 50922:10022 -v -e "DISPLAY=${DISPLAY:-:0.0}" -e "QEMU_AUDIO_DRV=none" docker-osx