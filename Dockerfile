FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/swetha-food-ordering-system-exec.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JVM_OPTS="-Xss256k -XX:MaxRAMPercentage=80.0 -Duser.timezone=australia/melbourne -Djava.security.egd=file:/dev/./urandom"
ENV JAVA_OPTS=""
ENV APP_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JVM_OPTS $JAVA_OPTS -jar /app.jar $APP_OPTS" ]