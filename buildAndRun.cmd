@echo off

call mvn clean package -DskipTests -B || exit /b 1

docker build -t com.carlosarroyoam/jee-book-service:latest . || exit /b 1

docker container rm -f jee-book-service >nul 2>&1

docker run -dp 8080:8080 -p 4848:4848 --name jee-book-service com.carlosarroyoam/jee-book-service:latest

exit /b 0
