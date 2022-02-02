build:
	(cd frontend && yarn build)

deploy:
	rsync -r frontend/build/ root@alpha.upsidemind.ru:/opt/upsidemind/public
