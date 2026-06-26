# DOMACO - Sistema de Gestión de Productos y Ventas

## Descripción
DOMACO es un sistema desarrollado con arquitectura de microservicios utilizando Spring Boot.  
El proyecto permite administrar productos y registrar ventas mediante comunicación entre microservicios utilizando Feign Client.

---

## Acceso al sistema

Para ingresar al sistema frontend utilizar:

Usuario: admin  
Contraseña: 1234
“El login fue implementado en frontend 
para control básico de acceso al sistema.”

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL (LARAGON)
- Flyway
- OpenFeign
- Maven
- Bootstrap
- HTML
- JavaScript

---

## Microservicios

### producto-service
Permite:
- Registrar productos
- Listar productos
- Modificar productos
- Eliminar productos


### venta-service
Permite:
- Registrar ventas
- Listar ventas
- Eliminar ventas
- Consumir productos desde producto-service mediante Feign Client

---

## Funcionalidades implementadas

- CRUD de productos
- CRUD de ventas
- Validaciones con Bean Validation
- Manejo global de excepciones
- Comunicación entre microservicios
- Persistencia con JPA + Hibernate
- Base de datos MySQL (LARAGON)
- Logs con SLF4J
- Integración frontend con Fetch API

---

## Integrantes

- DOLCIN MEDLIN

---

## Cómo ejecutar el proyecto

1. Crear las bases de datos MySQL O (LARAGON)
2. Ejecutar producto-service
3. Ejecutar venta-service
4. Abrir frontend HTML en navegador
5. Probar endpoints con Postman

---

## Puertos utilizados

- producto-service → 8083
- venta-service → 8081