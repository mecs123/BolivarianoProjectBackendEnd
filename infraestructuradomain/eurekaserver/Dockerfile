# Usa una imagen base con JDK 21
FROM eclipse-temurin:21-jdk

# Establece un directorio de trabajo
WORKDIR /app

# Copia el archivo JAR al contenedor
COPY target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver.jar

# Expone el puerto por defecto de Eureka Server
EXPOSE 8761

# Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]