FROM maven:3.3-jdk-8-alpine
COPY . /app
RUN cd app && mvn package
ENTRYPOINT ["java","-jar","app/target/fractionCalculator-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

