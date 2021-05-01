# Running a customized system.img on the Android Emulator

Please refer to https://stackoverflow.com/questions/54181502/how-to-build-android-system-img-and-run-it-on-emulator


# Example

## Loading the system.img
```
 $ cd /mnt/data1/aosp/master/out/host/linux-x86/sdk/sdk_phone_x86_64
 $ mkdir custom 
 $ cd custom 
 $ unzip ../sdk-repo-linux-system-images-eng.vsoc.zip
 $ emulator -sysdir /mnt/data1/aosp/master/out/host/linux-x86/sdk/sdk_phone_x86_64/custom/x86_64 -avd Pixel_4_API_30
```

With -writable-system option, we can make the system partition writable.

```
 $ emulator -sysdir /mnt/data1/aosp/master/out/host/linux-x86/sdk/sdk_phone_x86_64/custom/x86_64 -avd Pixel_4_API_30 -writable-system
 $ adb remount

```

If you change or update the system.img, you need to delete userdata-qemu images under the AVD directory.

```
$ cd $HOME/.android/avd/Pixel_4_API_30.avd
$ rm userdata-qemu.img*
```


# Inform the PCSCF address when activating PDN

With the following patch, libcuttlefish-ril-2 reads the PCSCF address from the property 'vendor.net.gprs.pcscf'

```
https://android-review.googlesource.com/c/device/google/cuttlefish/+/1690534
```

You need to change the sepolicy mode of the emulator to permissive.
```
$ adb shell setenforce 0
```
