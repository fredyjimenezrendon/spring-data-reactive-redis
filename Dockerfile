FROM openjdk:11
EXPOSE 8080
ADD /target/spring-data-reactive-redis-0.0.1-SNAPSHOT.jar spring-data-reactive-redis.jar
ENTRYPOINT ["java", "-jar", "spring-data-reactive-redis.jar"]
