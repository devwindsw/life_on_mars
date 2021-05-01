FROM ubuntu:18.04

RUN dpkg --add-architecture i386

ARG UID
RUN useradd -ms /bin/bash -d /home/vsoc -u $UID vsoc \
    && echo 'vsoc ALL=(ALL) NOPASSWD: ALL' >> /etc/sudoers

# Install prerequisites
RUN apt-get update && apt-get install --no-install-recommends -y \
    openssl libssl-dev libssl1.0.0 pcaputils libncurses5-dev libpcap-dev \
    libsctp-dev autoconf make gcc g++ git bison autoconf

RUN apt-get update && apt-get install --no-install-recommends -y \
    autotools-dev automake cmake 

USER vsoc 
ENV HOME vsoc 
WORKDIR /home/vsoc
