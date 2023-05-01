FROM gradle:jdk17 AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle clean build --no-daemon -x test

FROM openjdk:17-jdk-slim

COPY --from=build /home/gradle/src/build/libs/films-service-0.0.1.jar /app/films-service-0.0.1.jar

WORKDIR /app
EXPOSE 8070

CMD ["java", "-jar", "films-service-0.0.1.jar"]
