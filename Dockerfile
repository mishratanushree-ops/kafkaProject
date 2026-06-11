FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /workspace

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -B dependency:go-offline

COPY src ./src
RUN ./mvnw -B clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

ENV SERVER_PORT=8085
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/postgres
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

COPY --from=build /workspace/target/*.jar /demo.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/demo.jar"]
