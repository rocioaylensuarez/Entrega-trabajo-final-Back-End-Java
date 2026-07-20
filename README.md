# API REST - E-commerce de Indumentaria

Proyecto backend desarrollado en Java utilizando Spring Boot, Spring Data JPA y una base de datos relacional MySQL para la gestion de productos, categorias y carritos de compras.


## Descripcion del Proyecto

El sistema implementa una arquitectura en capas orientada a servicios RESTful para administrar una tienda de ropa online. Sus componentes principales permiten:
- Gestionar un catalogo de productos organizados por categorias mediante relaciones JPA.
- Administrar carritos de compras interactuando con validaciones de stock en tiempo real.
- Manejar excepciones de negocio de forma centralizada.
- Conectar interfaces de usuario web externas gracias a la configuracion global de CORS.


## Estructura del Proyecto

El código fuente se encuentra organizado en paquetes bajo la ruta `prendas/src/main/java/com/suarez/ropa`:
- config: Contiene las clases de configuracion global del sistema, como las reglas de CORS (`WebConfig.java`).
- controller: Expone los endpoints HTTP REST (`ProductoController.java`, `CategoriaController.java`, `CarritoController.java`).
- model: Define las entidades persistentes mapeadas a la base de datos (`Producto.java`, `Categoria.java`, `Carrito.java`, `CarritoProducto.java`).
- repository: Interfaces que extienden de `JpaRepository` para la comunicacion directa con MySQL.
- service: Aloja la logica de negocio y operaciones transaccionales de la aplicacion.
- exception: Clases de errores personalizados y un manejador global para respuestas HTTP controladas.

Adicionalmente, se puede abrir la interfaz grafica web de prueba se encuentra ubicada en la carpeta `prendas/FrontEnd/index/index.html`.


## Instrucciones para Ejecutar la Aplicacion

Para poner en marcha el proyecto en el entorno local, se debe tener en cuenta:

1. **Base de Datos:**
   Abrir el gestor MySQL (o XAMPP) y crea una base de datos vacia con el nombre `ecommerce`
   CREATE DATABASE ecommerce;
2. **Configurar entorno local**
   Fijarse que los datos de conexión del archivo src/main/resources/application.properties coincidan con el entorno local:
   - spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce?useSSL=false&serverTimezone=UTC
   - spring.datasource.username=root
   - spring.datasource.password=
3. **Correr el Back**
   Abrir el archivo en el IDE, ir al archivo src/main/EcommerceApplication.java y correrlo desde este archivo. La aplicación se inicia y corre por defecto en e puerto 8080. 


## Ejemplos de Uso y Datos de Prueba
Puedes probar los endpoints de la API utilizando Postman, creando una nueva HTTP request utilizando los siguientes **ENDPOINTS**

**Productos**
- Listar todos los productos:
  GET http://localhost:8080/api/productos
- Buscar producto por ID:
  GET http://localhost:8080/api/productos/1


**Categorias**
- Listar todas las categorias:
  GET http://localhost:8080/api/categorias


**Carrito de Compras**
- Crear un nuevo carrito:
  POST http://localhost:8080/api/carritos

- Agregar un producto al carrito:
  POST http://localhost:8080/api/carritos/1/productos/2

- Vaciar un carrito:
  DELETE http://localhost:8080/api/carritos/1/vaciar
