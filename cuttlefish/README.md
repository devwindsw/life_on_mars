# Cuttlefish

Please refer to https://github.com/google/android-cuttlefish and https://source.android.com/setup/create/cuttlefish.

Cuttlefish is a configurable virtual Android device that can run both remotely (using third-party cloud offerings such as Google Cloud Engine) and locally (on Linux x86 machines).


# Quick Start

Please refer to https://github.com/google/android-cuttlefish/blob/main/BUILDING.md.

Assume that docker and git are installed already.

## Creating the container
```
$ mkdir cuttlefish
$ cd cuttlefish

$ git clone https://github.com/google/android-cuttlefish.git
$ cd android-cuttlefish/

$ ./build.sh
```

## Downloading cuttlefish images
Refer to https://android.googlesource.com/device/google/cuttlefish/
Follow the steps from 3 to 9.

In my case, the name of host package downloaded via Chrome is cvd-host_package.tar not cvd-host_package.tar.gz.
And the container is not able to treat the host package. So I repacked it.

```
$ mkdir host-package
$ cd host-package
$ tar xvf ../cvd-host_package.tar 
$ tar zcvf cvd-host_package.tar.gz *
```

In step 9, you don't need to unpack the host package. Just copy it to the path where OTA images are located.


## Launching the cuttlefish inside the container
```
$ sudo modprobe vhost_vsock vhost_net

$ source setup.sh
$ export ANDROID_HOST_OUT=/cuttlefish/images // You SHOULD replace the path /cuttlefish/images with yours
$ export ANDROID_PRODUCT_OUT=/cuttlefish/images

// create and start container
$ cvd_docker_create -x -A -C cf1     // You CAN use another name instead of cf1.
                                     // Then, you SHOULD replace the name cf1 in the following lines.

// start cuttlefish inside container
$ cvd_start_cf1
```

## ADB
```
// for adb (in a new terminal)
$ cd cuttlefish/android-cuttlefish
$ source setup.sh
$ adb connect $ip_cf1:6520
```

### Redirecting Google Chrome Inside Container to Host X Server
Open https://$ip_cf1:8443  from Chrome                // You SHOULD replace $ip_{name} with yours.
 or
```
(In a new terminal)
$ cd cuttlefish/android-cuttlefish
$ source setup.sh
$ cvd_login_cf1 google-chrome-stable
```
and open https://127.0.0.1:8443 
