# docker build -t greetings-app .
# docker run --name greetings-demo-app -p 8080:8080 greetings-app

# Stage 1: Build
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

RUN apt-get update \
 && apt-get install -y maven \
 && rm -rf /var/lib/apt/lists/*

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime (Java 25)
FROM eclipse-temurin:25-jre

WORKDIR /app
COPY --from=build /app/target/product-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]