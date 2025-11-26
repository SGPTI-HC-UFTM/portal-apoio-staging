# Instalação e configuração do servidor

## Download do app server

### https://github.com/OpenLiberty/open-liberty/releases 

```
$ wget https://public.dhe.ibm.com/ibmdl/export/pub/software/openliberty/runtime/release/25.0.0.11/openliberty-25.0.0.11.zip
```

## Instalação

### Extração do pacote

```
# unzip openliberty-25.0.0.11.zip -d /opt/liberty
```

## Criação do usuário de serviço

```
# groupadd -r liberty
# useradd -r -g liberty -d /opt/liberty -s /sbin/nologin liberty
```

### Criação do servidor e ajuste das permissões

```
# /opt/liberty/wlp/bin/server create portal-apoio-stg
# chown -R liberty:liberty /opt/liberty
```

### Configuração systemd (/etc/systemd/system/liberty.service)

```
[Unit]
Description=Open Liberty Server
Documentation=https://openliberty.io/
After=syslog.target network.target
Before=nginx.service

[Service]
Type=forking
User=liberty
PIDFile=/opt/liberty/wlp/usr/servers/.pid/portal-apoio-stg.pid
ExecStart=/opt/liberty/wlp/bin/server start portal-apoio-stg
ExecStop=/opt/liberty/wlp/bin/server stop portal-apoio-stg

[Install]
WantedBy=multi-user.target
```

### Reload dos serviços

```
# systemctl daemon-reload
```

### Gestão do serviço

```
# systemctl start|stop|status liberty
```

## Deploy da aplicação

### Instalação do driver (postgresql)

```
# cd <sever-dir>
# mkdir -p shared/postgresql
# cd shared/postgresql
# wget https://jdbc.postgresql.org/download/postgresql-42.7.8.jar
```

### Configuração do server.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<server description="Portal Apoio Staging">
    <featureManager>
        <feature>webProfile-10.0</feature>
        <feature>xmlBinding-4.0</feature>
        <feature>bells-1.0</feature>
        <feature>microProfile-6.0</feature>
        <feature>jdbc-4.2</feature>
    </featureManager>

    <!-- Configuração HTTP -->
    <variable name="http.port" defaultValue="9080" />
    <variable name="https.port" defaultValue="9443" />

    <httpEndpoint host="*" httpPort="${http.port}"
                  httpsPort="${https.port}" id="defaultHttpEndpoint" />

    <!-- Configuração datasources -->
    <library id="postgresql">
        <fileset dir="shared/postgresql" includes="*.jar" />
    </library>
    
    <dataSource id="portalApoioDS" jndiName="portalApoioDS">
        <jdbcDriver libraryRef="postgresql" />
        <properties serverName="host" portNumber="port" databaseName="dbname" user="user" password="password" />
    </dataSource>

    <applicationMonitor autoExpand="true" />
    
    <ssl id="defaultSSLConfig" trustDefaultCerts="true" />
</server>
```

### Deploy

```
# cp portal-apoio-stg.war /opt/liberty/wlp/usr/servers/portal-apoio-stg/dropins
```
