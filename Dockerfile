FROM openjdk:21-ea-slim

COPY . /usr/src/ipmanager

WORKDIR /usr/src/ipmanager

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]