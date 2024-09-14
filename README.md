# Proyecto para evaluacion
### Este proyecto es una aplicacion para consultas de reservas en un restaurant.

## Tecnologias usadas

- JUnit
- Mockito
- Thymeleaf
- Lombok
- Maven
- SQL Server 2014
- Swagger
- Java JDK 17
- Spring Boot

## Lo que puedo hacer en esta aplicacion 
![image](https://github.com/user-attachments/assets/46caeba9-5405-4cd3-b087-9ef5f76e2117)

##  Listar todas las reservas
![image](https://github.com/user-attachments/assets/c8653e0e-a4a2-4ee7-a3d7-becc58d5dbd6)

## Detalles de una reserva por Id de reserva
![image](https://github.com/user-attachments/assets/1a5fe4ee-41fe-4e94-ad2d-99510a79d92a)

## Crear nueva reserva
![image](https://github.com/user-attachments/assets/5d4ba8a3-bab7-4681-bd6f-6ccb2fde2829)

## Actualizar la reserva
![image](https://github.com/user-attachments/assets/3d86569f-1d76-45b9-bbaf-cb6f28611667)

## Eliminar la reserva
![image](https://github.com/user-attachments/assets/ce8eba92-7548-46a8-a136-8bb47e907c02)

## ¿Como correr el proyecto?

- Tener instalado Spring tool Suite 4, maven vs 3.9.1 y el jdk 17 java.
- Tener instalado Microsoft SQL Server como sugerencia versiones apartir del 2014.
-Descargar el proyecto y configuar la ruta src/main/resources En el archivo application.properties
> - spring.application.name=apirest2
> - spring.datasource.url=jdbc:sqlserver://DESKTOP-87QGJJ7;trustServerCertificate=true;databaseName=db_restaurant
> - spring.datasource.username=sa
> - spring.datasource.password=Admin123

## Configurar la base de datos

- El archivo sql se encuentra en `.src/main/resources/SQL/script`
- Ejecutar el script en un query, arrastrando el script se obtiene la sintaxis generada para solo ejecutarla y crear la base de datos

![image](https://github.com/user-attachments/assets/f909036a-f994-4380-9abd-97c778698575)

## Swagger
![image](https://github.com/user-attachments/assets/7af96d3c-b387-471f-8407-21a20c04e9cc)

## Vistas

### Listar todas las reservas

`http://localhost:8080/reservas`

![image](https://github.com/user-attachments/assets/f54b9896-4d14-433b-adcb-56e142d55d87)

### Detalles de una reserva

`http://localhost:8080/reservas/7`

![image](https://github.com/user-attachments/assets/c85ddeab-6e27-4ab7-a9e6-e6b69d1f4b3e)

## Crear una nueva reserva

`http://localhost:8080/reservas/crear`


- Llenamos el formulario y crear formulario

![image](https://github.com/user-attachments/assets/9c718958-f628-4a29-9878-1142fca230ea)

- Al darle en crear reserva, nos da una vista donde se muestra que se creo

![image](https://github.com/user-attachments/assets/7b75f552-394a-465a-b521-69a654ad3e74)
