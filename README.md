# Gestión de Productos – API Reactiva

## Tecnologías
- Java 21
- Spring Boot 3.4 + WebFlux
- Spring Data R2DBC + H2
- WebClient (Spring Cloud)
- Gradle 8.14
- Mockito 5.11

## Ejecución
1. Clonar repositorio
    - git clone https://github.com/danieldavila1803/productapp.git
    - cd productapp
2. Abrir en IntelliJ el proyecto "productapp" 
   - Sincronizar Gradle 
   - Ejecutar ProductappApplication.java

## Endpoints
1. Listar: GET http://localhost:8080/api/products
2. Crear: POST http://localhost:8080/api/products
    {
    "nombre": "CPU",
    "precio": 5000.0,
    "stock": 2
    }
3. Actualizar: PUT http://localhost:8080/api/products/1
    {
    "nombre": "CPU HP",
    "precio": 5500.0,
    "stock": 4
    }
4. Eliminar: DELETE http://localhost:8080/api/products/1
5. Verificar Stock: GET http://localhost:8080/api/products/stock
6. Convertir PEN a USD: GET http://localhost:8080/api/products/convert

## Test Mockito
1. Ejecutar comando:
    - ./gradlew test -Dorg.gradle.java.home="C:\Program Files\Java\jdk-21"