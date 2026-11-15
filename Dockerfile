# ==============================================================
#  Dockerfile - hotel-citas Spring WebFlux
#  Build multi-etapa: compila con Maven y genera imagen ligera
# ==============================================================

# --- ETAPA 1: Compilar con Maven ---
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar pom.xml primero (cacheo de dependencias)
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copiar codigo fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests -q

# --- ETAPA 2: Imagen final ligera ---
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar el JAR generado en la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto por la aplicacion
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
