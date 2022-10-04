#Archivo para el contenedor de la aplicación web MSapp en java 17
FROM openjdk:17-jdk-alpine
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/MSapp-0.0.1-SNAPSHOT.jar /app/MSapp.jar
ENTRYPOINT [ "java", "-jar", "/app/MSapp.jar" ]


