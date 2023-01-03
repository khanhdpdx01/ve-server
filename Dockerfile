FROM openjdk:8
LABEL maintainer="khanhdpdx@gmail.com"
EXPOSE 3000
ENV LANG C.UTF-8
ARG JAR_FILE=target/ve-server-1.0.0.jar
COPY ${JAR_FILE} app.jar
CMD ["java", "-jar", "app.jar"]