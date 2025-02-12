# Task Master Backend

This is the backend for the Task Master application. The backend is built using Spring Boot and Spring Security.

## Technologies Used
- Spring Boot
- Spring Security
- Spring Data JPA
- Keycloak
- MySQL
- Docker
- Maven
- Lombok
- Swagger

## Pre-requisites
 - docker
 - maven
 - Java Development Kit (JDK) 21

## Getting Started
1. Clone the repository
```bash
git clone
```
2. Navigate to the project directory
```bash
cd Task-Master-Backend
```
## Running the Task Master Backend
1. First run the docker-compose file. This will start the required mysql database, and keycloak server.
```bash
docker-compose up
```
2. Build the project
```bash
mvn clean install
```
3. Run the spring boot application
```bash
mvn spring-boot:run
```
3. You can access the task master backend at [http://localhost:8080](http://localhost:8080)
4. You can access the keycloak server at [http://localhost:8181](http://localhost:8181). password for the admin user is `admin` and password is `admin`
5. You can access the mysql database at [http://localhost:3307](http://localhost:3307). password for the root user is `root` and password is `1234`
6. You can access swagger documentation at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) for the task master backend api.

## Using the Task Master Backend
- After running the task master backend you can access the api documentation at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- or
- you can access the task master frontend at [http://localhost:3000](http://localhost:3000) and use the frontend to interact with the backend, repository for the frontend can be found [here](https://github.com/RashmikaJayasooriya/Task-Master-Frontend)