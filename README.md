# Hillel Trip Planner server side

### Application that enable users to plan and book various aspects of their trip, including hotels, car rentals, flights, restaurants, and sightseeing activities.

![](https://github.com/ownerofglory/hillel-trip-planner-backend/actions/workflows/main.yml/badge.svg)


[![Coverage](https://sonar.ownerofglory.com/api/project_badges/measure?project=Hillel-Trip-Planner&metric=coverage&token=sqb_886143ce12e2f96bb54f38ad77e4dc0b15d6fd22)](https://sonar.ownerofglory.com/dashboard?id=Hillel-Trip-Planner)


## Prerequisites

- Java Development Kit (JDK) version 17.
- Apache Maven version 4.0.
- MySQL DB version 8.0.36

## Tools Used

- Spring Framework version 6.1.3 or higher
- Hibernate ORM version 6.4.2.Final
- Apache Tomcat version 10

## Local Development

### Build

To build the project, execute the following command:

```bash
./mvnw --batch-mode clean package 
```
### Testing

To run tests, execute the following command:

```bash
./mvnw --batch-mode test
```

### Running

Environment variables

    MYSQL_DRIVER=com.mysql.cj.jdbc.SDriver
    MYSQL_URL=jdbc:mysql://<host>:<port>/<database>
    MYSQL_USER=<username>
    MYSQL_PASSWORD=<password>

### Deployment local

Using Tomcat Smart Plugin for IntelliJ IDEA

Consider using the Tomcat Smart Plugin for IntelliJ IDEA to simplify the local deployment process. This plugin allows you to manage and deploy applications to Tomcat directly from your IDE, saving you time and effort.

Сan also be done manually:
- Build the WAR file using the command mentioned above.
- Copy the generated WAR file (usually found in the target folder) to the webapps directory of your Tomcat installation.
- Start the Tomcat server by executing the startup script (./bin/startup.sh for Unix or ./bin/startup.bat for Windows).
- Access the application at http://localhost:8080/hillel-trip-planner-web in your web browser, where hillel-trip-planner-web is the name of the WAR file.


### Deployment remote 

To deploy your application remotely, use the following command:

```bash
./mvnw --batch-mode --update-snapshots package cargo:deploy \
-Dtomcat.host=<TOMCAT_HOST> \
-Dtomcat.user=<TOMCAT_USER> \
-Dtomcat.password=<TOMCAT_PASSWORD> \
-Dtomcat.managerUrl=<TOMCAT_MANAGER_URL>
```