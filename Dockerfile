#FROM openjdk:19
#ADD target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "demo-be.jar"]
FROM maven:4.0.0 AS build
CMD echo starting...
WORKDIR /app
COPY pom.xml /app/pom.xml
CMD echo pom copied
RUN mvn dependency:go-offline
#RUN mvn -f /app/pom.xml dependency:go-offline
COPY src /app/src
CMD echo src copied
RUN mvn install
#RUN mvn -f /app/pom.xml package -DskipTests=true

FROM build AS dev-envs
RUN <<EOF
apt-get update
apt-get install -y --no-install-recommends git
EOF
CMD echo update and install

COPY --from=gloursdocker/docker / /
CMD ["mvn", "spring-boot:run"]
CMD echo spring boot run

FROM builder as prepare-production
RUN mkdir -p target/dependency
WORKDIR /workdir/server/target/dependency
RUN jar -xf ../*.jar

FROM eclipse-temurin:17-jre-focal
EXPOSE 8080
VOLUME /tmp
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo-be.jar
CMD java -jar demo-be.jar
