# base image JRE
FROM openjdk:17-slim

# used to wait until MySQL service starts - uncomment next 2 line for local build
# COPY wait-for-it.sh ./wait-for-it.sh
# RUN chmod +x wait-for-it.sh

RUN echo 'Workaround'

COPY hillel-trip-planner-web-boot/target/hillel-trip-planner-web-boot-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

# for local development
# EXPOSE 8090

# for prod deploymen
EXPOSE 80

# wait until MySQL starts and run the app - uncoment for the local build
# CMD ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "/app/app.jar"]

# remote deployment
CMD ["java", "-jar", "/app/app.jar"]




