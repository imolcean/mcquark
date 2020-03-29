# McQuark

## Build & Deploy

0.
[Optional]

If you add or change a DTO class, you need to regenerate the TypeScript definitions (`client/src/app/dto/dto.ts`).

`mvn clean compile typescript-generator:generate`

1.

Build project into a JAR. Client code will be built and added as static resources.

`mvn clean package`

2.

Upload the JAR (`server/target/server.jar`) onto the server and place it in `/var/www/zigas.cf/public`.

## Install

These actions are required to make the application available from the Internet.

0.

Install Java and Nginx

1.

Make the application to a systemd service. Create a file `/var/www/zigas.cf/zigas.cf.service` and fill it with the following content:

```
[Unit]
Description=zigas.cf
After=syslog.target

[Service]
User=imolcean
ExecStart=/var/www/zigas.cf/public/server.jar
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
```
and create a symlink

`ln -s /var/www/zigas.cf/zigas.cf.service /etc/systemd/system/`

Now you need to enable the service, so it loads on startup

`systemctl enable zigas.cf`

Afterwards, you start the service

`systemctl start zigas.cf`

2.

Set environment variables:

- TELEGRAM_BOT_TOKEN

3.

Enable reverse-proxy with Nginx. Create a file `/etc/nginx/sites-available/zigas.cf` with the following content:

```
server {
        listen 80;
        listen [::]:80;

        server_name zigas.cf www.zigas.cf;

        location / {
                proxy_pass http://localhost:8080/;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header X-Forwarded-Proto $scheme;
                proxy_set_header X-Forwarded-Port $server_port;
        }
}
```

and create a symlink

`ln -s /etc/nginx/sites-available/zigas.cf /etc/nginx/sites-enabled/`

Finally, restart Nginx

`systemctl restart nginx`

4.

Configure the firewall.

```bash
ufw default deny incoming
ufw default allow outgoing
ufw allow ssh
ufw allow http
ufw allow https
ufw enable
```
