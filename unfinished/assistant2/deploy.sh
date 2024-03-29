echo Build Docker Image...
sbt docker:publishLocal
echo Save Docker Image...
docker save softwaremill/bootzooka:latest | gzip > target/bootzooka.tar.gz
echo Upload Docker Image and docker-compose.yml via SSH...
scp target/bootzooka.tar.gz root@assistant.manenkov.com:/root
scp docker-compose.yml root@assistant.manenkov.com:/root
scp nginx.conf root@assistant.manenkov.com:/root
echo Load Docker Image on remote server via SSH...
ssh root@assistant.manenkov.com 'docker load < /root/bootzooka.tar.gz'
echo Restart application...
ssh root@assistant.manenkov.com 'docker-compose up -d --no-deps bootzooka'
