# testwd

This is a simple appointment management system built with Java Spring Boot and MySQL. It allows users to create, update, delete, list, and search for appointments using a RESTful API. The application is suitable for managing events like medical appointments, meetings, and consulting sessions.
Technologies used: Java 17, Spring Boot 3, Spring Data JPA, MySQL, Gradle

FEATURES
Create, update, and delete appointments
Search appointments by title
Appointment status tracking: upcoming, attending, maybe, declined
History tracking for each appointment using a secondary table
RESTful API connected to a MySQL database
User authentication with JSON Web Tokens (JWT) to protect API access

This project includes JWT-based authentication.
To access protected endpoints, clients must:
Authenticate by sending login credentials to the /auth/login endpoint.
Receive a JWT token in response.
Include the token in the Authorization header as a Bearer Token for all subsequent requests:

RUN
CREATE DATABASE citas_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

Properties
spring.datasource.username=your_username
spring.datasource.password=your_password

mvn spring-boot:run
http://localhost:8080/api/appointments
