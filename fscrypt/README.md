Refer to 
```
https://github.com/google/fscrypt
https://tlbdk.github.io/ubuntu/2018/10/22/fscrypt.html
```

# Quick Start

/dev/sdb1 and /mnt/data1/irvine shall be replaced by your own.

```
getconf PAGE_SIZE
sudo tune2fs -l /dev/sdb1 | grep 'Block size'
sudo tune2fs -O encrypt /dev/sdb1

sudo apt-get install fscrypt libpam-fscrypt

sudo vi /usr/share/pam-configs/keyinit-fix

sudo pam-auth-update

sudo fscrypt setup
sudo fscrypt setup /mnt/data1

mkdir /mnt/data1/irvine
fscrypt encrypt /mnt/data1/irvine
fscrypt unlock /mnt/data1/irvine
```
