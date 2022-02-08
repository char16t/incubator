Webhook has been created with:

```
https://api.trello.com/1/webhooks/?idModel=SECRET_ID_1&description=%22Daily%20Board%20Test%20Hook%22&callbackURL=http%3A%2F%2Fassistant.manenkov.com%2Ftrello-webhooks&key=SECRET_KEY&token=SECRET_TOKEN
```

## Build standalone JAR
```
sbt assembly
```

## Build
```
sbt docker:publishLocal
```

## Run

```
$ docker images
REPOSITORY           TAG                 IMAGE ID            CREATED             SIZE
assistant-service    0.1                 6c765dcfcde9        11 seconds ago      501MB
<none>               <none>              b5674aff0e5b        13 seconds ago      522MB
<none>               <none>              339b367907d6        3 minutes ago       501MB
<none>               <none>              8e0300ff4705        10 minutes ago      501MB
<none>               <none>              bbe0e022bf59        10 minutes ago      522MB
<none>               <none>              d1b9d2a82530        27 minutes ago      83MB
<none>               <none>              65170aef4ad5        27 minutes ago      127MB
openjdk              jre                 8c8b7f0ab84c        2 weeks ago         479MB
hello-world          latest              fce289e99eb9        2 months ago        1.84kB
openjdk              jre-alpine          ccfb0c83b2fe        8 months ago        83MB
```

```
docker run -p8080:8080 6c765dcfcde9
```

## Test hook endpoint availability

```
Request (POST): http://localhost:8080/trello-webhooks?check=test
Response: Check is: test
```

## Enable HTTPS

https://certbot.eff.org/lets-encrypt/ubuntubionic-other

```
# Add Certbot PPA
sudo apt-get update
sudo apt-get install software-properties-common
sudo add-apt-repository universe
sudo add-apt-repository ppa:certbot/certbot
sudo apt-get update

# Install Certbot
sudo apt-get install certbot

# Yes, my web server is not currently running on this machine
sudo certbot certonly --standalone
```

And then:

```
cd /etc/letsencrypt/live/assistant.manenkov.com
openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out keystore.p12 -CAfile chain.pem -caname root
# ... enter pssword ...
```

## Install PostgreSQL

```
sudo apt update
sudo apt install postgresql postgresql-contrib

sudo -i -u postgres
psql
\q
createdb assistant-db
psql
\password postgres
\q
\connect assistant-db
\dt
\d trello_webhooks
```

## Deploy

```
scp assistant-service-assembly-0.2.jar root@assistant.manenkov.com:/root
```

## TODO

IMPORTANT NOTES:
 - Congratulations! Your certificate and chain have been saved at:
   /etc/letsencrypt/live/assistant.manenkov.com/fullchain.pem
   Your key file has been saved at:
   /etc/letsencrypt/live/assistant.manenkov.com/privkey.pem
   Your cert will expire on 2020-01-17. To obtain a new or tweaked
   version of this certificate in the future, simply run certbot
   again. To non-interactively renew *all* of your certificates, run
   "certbot renew"
 - If you like Certbot, please consider supporting our work by:

   Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
   Donating to EFF:                    https://eff.org/donate-le

### Fix broken symlinks

```bash
sudo mv /etc/letsencrypt/live/assistant.manenkov.com/fullchain.pem /etc/letsencrypt/live/assistant.manenkov.com/fullchain.pem.old
sudo ln -s /etc/letsencrypt/archive/assistant.manenkov.com/fullchain1.pem /etc/letsencrypt/live/assistant.manenkov.com/fullchain.pem

sudo mv /etc/letsencrypt/live/assistant.manenkov.com/cert.pem /etc/letsencrypt/live/assistant.manenkov.com/cert.pem.old
sudo ln -s /etc/letsencrypt/archive/assistant.manenkov.com/cert1.pem /etc/letsencrypt/live/assistant.manenkov.com/cert.pem

sudo mv /etc/letsencrypt/live/assistant.manenkov.com/chain.pem /etc/letsencrypt/live/assistant.manenkov.com/chain.pem.old
sudo ln -s /etc/letsencrypt/archive/assistant.manenkov.com/chain1.pem /etc/letsencrypt/live/assistant.manenkov.com/chain.pem

sudo mv /etc/letsencrypt/live/assistant.manenkov.com/privkey.pem /etc/letsencrypt/live/assistant.manenkov.com/privkey.pem.old
sudo ln -s /etc/letsencrypt/archive/assistant.manenkov.com/privkey1.pem /etc/letsencrypt/live/assistant.manenkov.com/privkey.pem
```
