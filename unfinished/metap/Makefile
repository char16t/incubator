default: help

IMAGE_NAME=valeriy-test1
VERSION=$(shell cat .version)
GIT_SHA=$(shell git rev-parse --short HEAD)
GIT_DIFF=$(shell git diff -s --exit-code || echo "-local")
GIT_REV=$(GIT_SHA)$(GIT_DIFF)

define setup_env
	$(eval ENV_FILE := env/$(1).env)
	@echo " - setup env $(ENV_FILE)"
	$(eval include env/$(1).env)
	$(eval export sed 's/=.*//' env/$(1).env)
endef

## Use 'local' environment
env/local:
	$(call setup_env,local)

## Bump patch version
bump/patch:
	bash ./incr_semver.sh $(VERSION) release > .version

## Bump minor version
bump/minor:
	bash ./incr_semver.sh $(VERSION) minor > .version

## Bump major version
bump/major:
	bash ./incr_semver.sh $(VERSION) major > .version

## Compile the entire program
all: env/local
	docker build --build-arg GIT_SHA=$(GIT_REV) --build-arg VERSION=$(VERSION) --tag $(IMAGE_NAME):$(VERSION).$(GIT_REV) .
	docker run $(IMAGE_NAME):$(VERSION).$(GIT_REV)

## This help screen
help:
	@printf "Available targets:\n\n"
	@awk '/^[a-zA-Z\-_0-9%:\\]+/ { \
		helpMessage = match(lastLine, /^## (.*)/); \
		if (helpMessage) { \
		helpCommand = $$1; \
		helpMessage = substr(lastLine, RSTART + 3, RLENGTH); \
	gsub("\\\\", "", helpCommand); \
	gsub(":+$$", "", helpCommand); \
		printf "  \x1b[32;01m%-35s\x1b[0m %s\n", helpCommand, helpMessage; \
		} \
	} \
	{ lastLine = $$0 }' $(MAKEFILE_LIST) | sort -u
	@printf "\n"
