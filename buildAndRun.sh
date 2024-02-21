#!/bin/sh
mvn clean package && docker build -t com.carlosarroyoam/jee-book-service .
docker container rm -f jee-book-service || true && docker container run -dp 8080:8080 -p 4848:4848 --name jee-book-service com.carlosarroyoam/jee-book-service 
