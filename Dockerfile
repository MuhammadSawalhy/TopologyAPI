FROM maven:3.8-openjdk-18-slim

WORKDIR /topologies
COPY pom.xml /topologies/pom.xml
COPY src/ /topologies/src/

RUN mvn clean install
CMD mvn test
