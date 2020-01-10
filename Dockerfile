FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]



#FROM maven:alpine AS MAVEN_BUILD
#COPY pom.xml /build/
#COPY src /build/src/
#WORKDIR /build/
#RUN mvn clean package
#WORKDIR /app
#
#COPY --from=MAVEN_BUILD /build/target/practice-0.0.1-SNAPSHOT.jar /app/
#
#ENTRYPOINT ["java", "-jar", "practice-0.0.1-SNAPSHOT.jar"]
