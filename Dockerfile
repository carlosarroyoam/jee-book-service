FROM airhacks/glassfish
COPY ./target/library-service.war ${DEPLOYMENT_DIR}
