FROM openjdk:17-jdk-alpine
EXPOSE 8082
COPY target/foyerimage.war foyerimage.war
ENTRYPOINT ["java","-jar","/foyerimage.war"]