FROM maven:3.9.0-amazoncorretto-17 AS build
WORKDIR /usr/app
COPY . /usr/app

RUN mvn dependency:go-offline -B
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17
WORKDIR /usr/app
COPY --from=build /usr/app/target/api-biblioteca-livros-0.0.1-SNAPSHOT.jar /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-biblioteca-livros-0.0.1-SNAPSHOT.jar"]
