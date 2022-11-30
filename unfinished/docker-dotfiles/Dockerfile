FROM ubuntu:20.04

COPY profile /root/.profile
COPY bashrc /root/.bashrc
COPY tmux.conf /root/.tmux.conf
COPY gitconfig /root/.gitconfig

RUN apt-get update && \
  apt install -y tmux && \
  apt install -y fzf && \
  apt install -y git

ENTRYPOINT ["tmux", "new", "-s", "Main"]
