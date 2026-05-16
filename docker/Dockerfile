# Stage 1: build
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# copy file cần thiết trước (tối ưu cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# copy source
COPY src ./src

# build jar
RUN mvn clean package -DskipTests


# Stage 2: run
FROM eclipse-temurin:17-jdk

WORKDIR /app

# copy jar từ stage build
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
