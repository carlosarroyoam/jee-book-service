# =========================
# Runtime stage
# =========================
FROM airhacks/glassfish

COPY /target/jee-book-service.war ${DEPLOYMENT_DIR}
