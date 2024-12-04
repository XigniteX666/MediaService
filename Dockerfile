#STEP 1 build the service
FROM registry.access.redhat.com/ubi8/openjdk-21:1.20 AS build

WORKDIR /build

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn/

RUN ./mvnw dependency:go-offline

COPY src src/

RUN ./mvnw package


FROM registry.access.redhat.com/ubi8/openjdk-21:1.20

ENV LANGUAGE='en_US:en'


# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --chown=185 --from=build target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 --from=build target/quarkus-app/*.jar /deployments/
COPY --chown=185 --from=build target/quarkus-app/app/ /deployments/app/
COPY --chown=185 --from=build target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]



