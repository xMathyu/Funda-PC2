# Define la imagen base
FROM maven:3.8.4-openjdk-11 AS builder

# Configura el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml al directorio de trabajo
COPY pom.xml .

# Descarga las dependencias del proyecto
RUN mvn dependency:go-offline

# Copia el resto del código fuente al directorio de trabajo
COPY src ./src

# Compila el proyecto
RUN mvn package -DskipTests

# Define la imagen base para la aplicación
FROM openjdk:11-jre-slim

# Copia el archivo JAR generado en la etapa anterior
COPY --from=builder /app/target/MonitoringService-0.0.1-SNAPSHOT.jar /app.jar

# Define el comando de inicio de la aplicación
CMD ["java", "-jar", "/app.jar"]