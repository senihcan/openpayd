First run sql script in postgresql db -> create database openpayd;
Write db knowledge in application.yml under spring: datasource: 
If first execute please change application.yml hibernate:ddl-auto: create-drop
if you want to test resource class come to your project path in bash and maven test or direct maven clean install
Run spring boot project TaskApplication.java or
come to your project path in bash and run command -> mvn spring-boot:run
After you see the all rest services this link -> http://localhost:8090/swagger-ui.html