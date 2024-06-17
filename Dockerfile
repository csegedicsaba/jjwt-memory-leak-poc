FROM redhat/ubi9-minimal
RUN microdnf install -y java-17-openjdk-headless

COPY target/jjwt-memory-leak-poc-1.0-SNAPSHOT-jar-with-dependencies.jar ./jjwt-memory-leak-poc-1.0-SNAPSHOT-jar-with-dependencies.jar

ENTRYPOINT ["java", "-Xmx512m", "-XX:NativeMemoryTracking=detail", "-jar", "jjwt-memory-leak-poc-1.0-SNAPSHOT-jar-with-dependencies.jar"]