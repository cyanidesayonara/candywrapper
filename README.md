# candywrapper
Candy Wrapper will be an online candy store.  
https://candywrapper.herokuapp.com  

## Specs
Backend: Spring Boot  
Frontend: React  
Database: MongoDB  

## How To Setup A Development Environment  
install Node, Maven & Java  
git clone https://github.com/cyanidesayonara/candywrapper.git  
cd candywrapper  
mvn spring-boot:run  
cd frontend  
npm start  

## Resources used  
https://github.com/kantega/react-and-spring  
http://frugalisminds.com/spring-boot/deploy-spring-boot-app-on-heroku/  
https://javadeveloperzone.com/spring-boot/spring-boot-rest-service-with-mongodb-example/  
https://stackoverflow.com/questions/28747909/how-to-disable-spring-data-mongodb-autoconfiguration-in-spring-boot  
http://websystique.com/spring-boot/spring-boot-rest-api-example/  
https://www.baeldung.com/intro-to-project-lombok  
https://dev.to/onlineinterview/user-account-loginregistration-feature-with-spring-boot--3fc3
https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html
https://stackoverflow.com/questions/52243774/consider-defining-a-bean-of-type-org-springframework-security-authentication-au

## Deploy to heroku  
install heroku cli  
heroku plugins:install heroku-cli-deploy  
mvn install package  
heroku deploy:jar target/candywrapper-{ version }.jar  