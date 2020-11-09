# SafetyNet Alerts

A rest api which its objective is to send informations to emergency services.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Maven 3.6.3

### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

### Running The App

Import the code into an IDE of your choice and run the AlertsApplication.java to launch the application.

### Url Examples

Here is the list of the urls that you can call to get the data from the rest api :

1. http://localhost:8080/firestation?stationNumber="station_number"
2. http://localhost:8080/childAlert?address="address"
3. http://localhost:8080/phoneAlert?firestation="firestation_number"
4. http://localhost:8080/fire?address="address"
5. http://localhost:8080/flood/stations?stations="a_list_of_station_numbers"
6. http://localhost:8080/personInfo?firstName="firstName"&lastName="lastName"
7. http://localhost:8080/communityEmail?city="city"

You can also use these urls to respectively get, add, update or delete a data when using the rest api :

1. GET http://localhost:8080/persons
2. POST/PUT/DELETE http://localhost:8080/person
3. GET http://localhost:8080/firestations
4. POST/PUT/DELETE http://localhost:8080/firestation
5. GET http://localhost:8080/medicalRecords
6. POST/PUT/DELETE http://localhost:8080/medicalRecord



### Testing

The app has unit tests and integration tests written. To run them from maven, go to the folder that contains the pom.xml file and execute the below command.

`mvn site`