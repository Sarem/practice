# Snapp

This is a contact app only for insert and search contact.

## Requirements

Having [Apache maven](https://maven.apache.org/) to build source code.
Having [JDK 8](https://openjdk.java.net/install/) to compile source code.
Having [Docker](https://docs.docker.com/install/) make your environment ready.

## Pre-deploy
This program is using 8090 port, for spring server.
This program is using 8080 port, for db admin if you need.
This program is using 3306 port, for mysql.
please make sure these port are free in your system or change it in the [application.yaml](src/main/resources/application.yaml) and [docker-compose.yml](docker-compose.yml) file.

## Compile and deploy
First in the root directory
compile and package the app using maven
```bash
mvn clean package
```
then start containers
```bash
docker compose up
```
## Usage

You can add a contact like this. In the requirement didn't mention to update or duplicate check, so it is not support in this application.
```http request
PUT http://localhost:8090/contact
Content-Type: application/json

{
  "name": "saman",
  "phoneNumber": "+909352490969",
  "email": "saman.sarem@hotmail.com",
  "organization": "snap",
  "github": "https://github.com/cloudfoundry"
}
```
You can search for a contact like this. It is case sensitive and use full text. You cannot search on git repository as it told in second paragraph.
```http request
GET http://localhost:8090/contact
Content-Type: application/json

{
"name": "saman"
}
```
You can use [contact.http](src/main/resources/contact.http to api call if you using intellij idea.
