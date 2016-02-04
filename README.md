This is a demo project that I implemented during one of the recruitment processes in February 2016. The goal was to create a currency converter application which integrates with an external API.

My implementation may seem overengineered in some places but it was a conscious decision to build this in such a way that it would be a good starting point for a real project.

## Technologies

I chosen the following technologies:

* __Spring Framework__ - must-have on every project :-)

* __Spring Boot__ - a brilliant collection of best practices and reusable components like Maven "starter" dependencies, Spring autoconfiguration, embedded containers, externalized properties, Spring Actuator (monitoring and management). All this could of course be configured manually and I have done most of it in the past (yes, even in the dark ages of XML configuration - without annotations or Spring JavaConfig! ;-) but I am a strong believer in the DRY (Don't Repeat Yourself) principle.

* __Spring Data__ - Spring Data is a huge help when implementing your data access layer.

* __JPA__ - with Hibernate as persistence provider and hsqldb (in-memory) as database.

* __Ehcache__ - for caching external calls, nicely integrates with Spring thanks to Spring Boot's autoconfiguration features (when ehcache is detected on classpath, configured cache manager is automatically created for you).

* __Thymeleaf__ - pretty cool view technology which nicely integrates with Spring MVC. This was actually my first time using it and I chose it because I just couldn't bring myself to using JSP or Freemarker in 2016...

* __Cucumber__ - for BDD-style tests - take a look at scenarios [here](/src/test/resources/com/example/ppawel/test/cucumber).

## System requirements

To build the code you need JDK 8 and Maven >= 3.2.

## Building

1. Clone repo.
2. `mvn clean package`

This builds a Spring Boot executable jar file with embedded Tomcat.

## Running

1. `cd target`
2. `java -jar currency-converter-0.0.1-SNAPSHOT.jar`

This should start the embedded Tomcat and the application will be available at http://localhost:8080/.

## Jenkins

Build and deployment jobs are on Jenkins here:

http://jenkins.jcore.pl/ (`currency-converter-*` jobs should be accessible without authentication)
