.PHONY: default init_server deploy

default: deploy

all:
	ansible-playbook playbook.yml -i hosts

init_server:
	ansible-playbook playbook.yml -i hosts --tags "init_server"

deploy:
	ansible-playbook playbook.yml -i hosts --tags "deploy"

