# base image JRE
FROM openjdk:17-slim

# used to wait until MySQL service starts
COPY wait-for-it.sh ./wait-for-it.sh
RUN chmod +x wait-for-it.sh

COPY hillel-trip-planner-web-boot/target/hillel-trip-planner-web-boot-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

EXPOSE 8090

# wait until MySQL starts and run the app
CMD ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "/app/app.jar"]




