# candywrapper
Candy Wrapper will be an online candy store.  
https://candywrapper.herokuapp.com  

## Specs
Backend: Spring Boot  
Frontend: React
Database: MongoDB

## Setup how-to
install Node, Maven & Java  
git clone https://github.com/cyanidesayonara/candywrapper.git    
cd candywrapper
mvn spring-boot:run

## Resources used  
https://github.com/kantega/react-and-spring  
http://frugalisminds.com/spring-boot/deploy-spring-boot-app-on-heroku/  
https://javadeveloperzone.com/spring-boot/spring-boot-rest-service-with-mongodb-example/  
https://stackoverflow.com/questions/28747909/how-to-disable-spring-data-mongodb-autoconfiguration-in-spring-boot  

## Deploy to heroku
mvn install package  
heroku deploy:jar target/candywrapper-{ version }.jar  