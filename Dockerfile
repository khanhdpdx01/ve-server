FROM openjdk:11-jdk-slim-bullseye AS build-env
LABEL maintainer="khanhdpdx@gmail.com"
ENV FABRIC_PEM_FILE_LOCATION=/app/connection-org1.yaml
EXPOSE 3000
WORKDIR /app
ENV LANG C.UTF-8
ARG JAR_FILE=target/ve-server-1.0.0.jar
COPY ${JAR_FILE} app.jar
# CMD ["java", "-jar", "app.jar"]

FROM gcr.io/distroless/java11-debian11
COPY --from=build-env /app /app
WORKDIR /app
CMD ["app.jar"]