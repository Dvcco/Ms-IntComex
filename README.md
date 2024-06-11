Prueba Tecnica IntComex

Daniel Casallas

Esta aplicación es una ApiRestFull, la cual tiene como finalidad realizar operaciones CRUD.

Fue realizado bajo la arquitectura de MicroServicios, programación Reactiva uso de (Mono) y Lambdas, es decir; no es una Api Monolitica, si un servicio falla, seguirá funcionando con normalidad, esta prueba esta realizada con el lenguaje de programación Java en su versión Java 17 y su Framework SpringBoot, con su gestor de dependencias Gradle, haciendo uso de una base de datos MySql. Para correr este proyecto de manera local, es necesario contar con el JDK de Java, gestor de construcción y desempaquetamiento de librerias Gradle, variables de entorno y un compilador de código (Intellij fue usado para la creación de esta prueba).

- Instrucciones levantamiento y/o ejecución de pruebas:



Folder Security:

1). En la collection de Postman brindada, se encuentra una carpeta llamada Security, donde ejecuta un Metodo Post llamado "GenerateToken", allí generará un "Token" el cual servirá para autenticarnos y asi ejecutar las Api.

- METODO POST:
- Body: {
  "token": "generarToken"
  }
- URL : 34.235.88.122:8080/Security/generateToken

2). En la collection de Postman brindada, se encuentra una carpeta llamada Security, donde ejecuta un Metodo Get llamado "validateToken", allí generará un "Token" el cual servirá para validar el estado y el tiempo del token (duración 5 minutos).

- METODO GET:
- Headers: Key=Authorization Value=ValorTokenGenerado
- URL: 34.235.88.122:8080/Security/validateToken


Folder Categoria:

1). En la collection de Postman brindada, se encuentra una carpeta llamada Categoria, donde ejecuta un Metodo Post llamado "Crear categoria", el cual permite crear una categoria.

- METODO POST:
- Body: {
  "categoryName": "Nombre categoria",
  "description": "Descripcion categoria",
  "picture": "Poner en base64 de la imagen y/o foto"
}
- Headers: Key=Authorization Value=ValorTokenGenerado
- URL : 34.235.88.122:8080/Category/createCategory

Folder Producto:

1). En la collection de Postman brindada, se encuentra una carpeta llamada Producto, donde ejecuta un Metodo Post llamado "Crear producto", el cual permite crear un producto.


- METODO POST:
- Body: {
  "supplierId": 1,
  "categoryId": 8,
  "productName": "Tumis",
  "quantityPerUnit": "5",
  "unitPrice": 25000,
  "unitsInStock": 20,
  "unitsOnOrder": 10,
  "recorderLevel": 1,
  "discontinued": 0
  }
- Headers: Key=Authorization Value=ValorTokenGenerado
- URL : 34.235.88.122:8080/Product/createProduct


2). En la collection de Postman brindada, se encuentra una carpeta llamada Producto, donde ejecuta un Metodo Get llamado "Listar productos", el cual permite listar todos los productos y paginarlos por cantidad y la cantidad de paginas.

- METODO GET:
- Params: Key=page Value=(numero de paginas a visualizar)
- Params: Key=quantityProducts Value=(numero de registros a visualizar)
- Headers: Key=Authorization Value=ValorTokenGenerado
- URL : 34.235.88.122:8080/Product/Products?page=0&quantityProducts=20

3). En la collection de Postman brindada, se encuentra una carpeta llamada Producto, donde ejecuta un Metodo Get llamado "Listar productos por id", el cual permite listar productos por ID.

- METODO GET:
- Params: Key=id Value=(ID del producto)
- Headers: Key=Authorization Value=ValorTokenGenerado
- URL : 34.235.88.122:8080/Product/Products/id?id=5

SWAGGER

LINK LOCAL http://localhost:8080/swagger-ui/index.html#/
