@echo off
call mvn clean package || exit /b 1
docker build -t com.carlosarroyoam/jee-book-service:latest . || exit /b 1
docker container rm -f jee-book-service >nul 2>&1
docker container run -dp 8081:8080 -p 4849:4848 --name jee-book-service com.carlosarroyoam/jee-book-service:latest
