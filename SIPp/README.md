# Dockerfile for Building SIPp

Refer to https://hub.docker.com/repository/docker/javachips/sipp-builder

## Running

### 1. Create a directory
```
cd /mnt/data1
mkdir sipp 
```
### 2. Download SIPp

From https://github.com/SIPp/sipp

### 3. Start a container
```
docker run -it -v /mnt/data1/sipp:/sipp --rm javachips/sipp-builder
```

Replace /mnt/data1/sipp with the path to yours.
