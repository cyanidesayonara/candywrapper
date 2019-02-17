# candywrapper
Candy Wrapper will be an online candy store.  
https://candywrapper.herokuapp.com  

## Specs
Backend: Spring Boot  
Frontend: React

## Setup how-to
git clone https://github.com/cyanidesayonara/candywrapper.git    
install Node, Maven & Java  
run command 'npm install' inside folder 'candywrapper/frontend'  
run command 'mvn clean install' inside folder 'candywrapper'  
run command 'java -jar target/candywrapper-{ version }.jar' inside folder 'candywrapper'  

## Resources used  
https://github.com/kantega/react-and-spring  
http://frugalisminds.com/spring-boot/deploy-spring-boot-app-on-heroku/  

## Deploy to heroku
mvn install package  
heroku deploy:jar target/candywrapper-{ version }.jar  