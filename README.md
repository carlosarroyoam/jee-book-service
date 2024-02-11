# Build
mvn clean package && docker build -t com.carlosarroyoam/jee-book-service .

# RUN
docker rm -f jee-book-service || true && docker run -d -p 8080:8080 -p 4848:4848 --name jee-book-service com.carlosarroyoam/jee-book-service 