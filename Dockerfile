FROM openjdk:17-oraclelinux7
RUN groupadd --system spring && useradd --system spring -g spring
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]