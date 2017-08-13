Ant + Apache Shiro + MyBatis + PostgreSQL + Spring Boot + Apache POI 
=================================

An example of REST application that show the usage of Ant, Apache Shiro, MyBatis, PostgreSQL, Spring Boot and Apache POI

Configure database
---------------
Change properties in application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/asd
spring.datasource.username=postgres
spring.datasource.password=postgres
```

Run Application
---------------
* Use Ant target -> **build**
* Run **asd.jar** in **target** folder

Browse to [http://localhost:8080/](http://localhost:8080/)

These are simples GET requests just for trigger Shiro security rules.
* Link to login as [Admin](http://localhost:8080/login?user=admin&password=root).
* Link to login as [User](http://localhost:8080/login?user=user&password=password).
* Link to [Logout](http://localhost:8080/login?user=user&password=password).

Additional links:
* [Send report to email](http://localhost:8080/requests_report?email=asdmailtest@gmail.com&limit=100) this action allowed just for Admin.
* [Get request list in JSON format](http://localhost:8080/responses?limit=3) allowed for anybody who is authenticated.



