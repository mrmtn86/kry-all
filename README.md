# Kry Code Assignment

# Service Heartbeat App - Metin Deniz

This is a web application that tracks web service's status (WORKING/FAILED).

You can add, remove and modify services. Application supports multiple user accounts.

# Project has 3 sub modules

# 1 - kry-service-health:

This is Spring Boot Maven Application. Make sure you have JDK 1.8 and Maven 3.x

Maven   : https://maven.apache.org/index.html

JDK 1.8 :  https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

This is a basic restful service. By using api you can add url of the other services.

To Run It open a terminal window and navigate to project root directory <kry-service-health>

Build & Run :

      mvn clean package

      java -jar target/rest-api.jar


After you run it, you can check http://localhost:8080/swagger-ui/ You will find more info about rest api.

# 2 - heartbeat-client :

This is the user interface module that has only web layer.

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 9.0.7.

If you don't have it you can learn how to install it from
here :  https://github.com/angular/angular-cli/tree/master/packages/angular/cli

To Run It open a terminal window and navigate to project root directory <heartbeat-client>

Build & Run :

      npm install

      ng build

      ng serve --open

You will see login screen at http://localhost:4200/

nelly@kry.com is default defined user, but you can try also ludvik and kerry with same mail address tail.


# 3 - heartbeat-test-client (Bonus) :

###PS : This project is not required for code assigment task. Yet if you don't have real test service urls you can use this.

This is a small web service util for real life test. It is also Spring Boot Application using maven build tool.

It starts a web services that only answers base address (ex: http://localhost:8090).

To Run It open a terminal window and navigate to project root directory <heartbeat-test-client>

Build & Run :

      mvn clean package

      java -jar target/service-test.jar     8084        1
      java -jar target/service-test.jar {serverPort} {userId}


{serverPort} : server will use this port

{userId}       : owner of server


---------------------------------------------------------------------------------------------------------------------

Basic requirements (If these aren’t met the assignment will not pass):

● A user need to be able to add a new service with url, a name

● Added services have to be kept when the server is restarted

● Present whenever a service was added and when the last change was made

+
+
+

Extra requirements (No prioritisation on these, pick the ones that you find
interesting):

● We want full create/update/delete functionality for services

● The results from the poller are not automatically shown to the user
(you have to reload the page to see results)

● We want to have informative and nice looking animations on
add/remove services

● Simultaneous writes sometimes causes strange behavior

● Protect the poller from misbehaving services (for example answering
really slowly)

● URL Validation ("sdgf" is probably not a valid service)

● Multi user support. Users should not see the services added by
another user
