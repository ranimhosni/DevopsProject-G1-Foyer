FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/devopsimage01.war devopsimage01.war
ENTRYPOINT ["java","-jar","/devopsimage01.war"]