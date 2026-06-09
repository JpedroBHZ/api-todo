# Estágio 1: Build da aplicação usando Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
# Copia o pom.xml e baixa as dependências (otimiza o cache do Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código fonte e gera o arquivo .jar compilado
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Execução da aplicação em uma imagem leve com Java 21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copia apenas o arquivo .jar gerado no estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]