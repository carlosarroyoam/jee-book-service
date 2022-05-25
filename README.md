# Build
mvn clean package && docker build -t com.carlosarroyoam/library-service .

# RUN

docker rm -f library-service || true && docker run -d -p 8080:8080 -p 4848:4848 --name library-service com.carlosarroyoam/library-service 