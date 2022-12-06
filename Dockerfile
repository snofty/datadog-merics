FROM openjdk:11

COPY ./target/datadog-metrics-0.0.1-SNAPSHOT.jar /usr/src/datadog-metrics/
EXPOSE 8000-8089
WORKDIR /usr/src/datadog-metrics

CMD ["java", "-jar", "datadog-metrics-0.0.1-SNAPSHOT.jar"]