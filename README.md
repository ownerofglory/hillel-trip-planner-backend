# Hillel Trip Planner server side

### Application that enable users to plan and book various aspects of their trip, including hotels, car rentals, flights, restaurants, and sightseeing activities.

![](https://github.com/ownerofglory/hillel-trip-planner-backend/actions/workflows/main.yml/badge.svg)


[![Coverage](https://sonar.ownerofglory.com/api/project_badges/measure?project=Hillel-Trip-Planner&metric=coverage&token=sqb_886143ce12e2f96bb54f38ad77e4dc0b15d6fd22)](https://sonar.ownerofglory.com/dashboard?id=Hillel-Trip-Planner)


## Prerequisites

- Java Development Kit (JDK) version 17.
- Apache Maven version 4.0.

## Tools Used

- Spring Framework
- Hibernate ORM
- Apache Tomcat (for deploying the web application)

## Local Development

### Testing

To run tests, execute the following command:

```bash
./mvnw --batch-mode test
```

### Running

Environment variables

    db.driver=${MYSQL_DRIVER}
    db.url=${MYSQL_URL}
    db.user=${MYSQL_USER:root}
    db.password=${MYSQL_PASSWORD:password}

After building, the compiled WAR file will be available in the target folder. Deploy this WAR file to a Tomcat server for execution.

### Deployment
Deploying on Tomcat

- Build the WAR file using the command mentioned above.
- Copy the generated WAR file (usually found in the target folder) to the webapps directory of your Tomcat installation.
- Start the Tomcat server by executing the startup script (./bin/startup.sh for Unix or ./bin/startup.bat for Windows).
- Access the application at http://localhost:8080/hillel-trip-planner-web in your web browser, where hillel-trip-planner-web is the name of the WAR file.

