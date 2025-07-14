# Product Inventory API

---

## Descripción del Proyecto

Esta es una API REST simple para la gestión de un inventario de productos. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre los productos, además de funcionalidades específicas para el control de stock (añadir y reducir la cantidad disponible).

El objetivo de este proyecto es demostrar el uso de **Spring Boot** para construir APIs RESTful, la persistencia de datos con **Spring Data JPA** y una base de datos **PostgreSQL**, así como la implementación de lógica de negocio y validaciones.

---

## Características Principales

* **Gestión de Productos (CRUD):**
    * Crear nuevos productos.
    * Obtener listado de todos los productos.
    * Obtener un producto por su ID.
    * Actualizar la información de un producto existente.
    * Eliminar un producto.
* **Control de Stock:**
    * Añadir unidades al stock de un producto.
    * Reducir unidades del stock de un producto, con validación para evitar stock negativo.
* **Base de Datos PostgreSQL:** Utiliza una base de datos relacional robusta.

---

## Tecnologías Utilizadas

* **Java [Versión de Java, ej. 17/21]**
* **Spring Boot [Versión de Spring Boot, ej. 3.2.0]**
    * Spring Web
    * Spring Data JPA
    * Spring Boot DevTools
* **PostgreSQL**
* **Lombok** (para reducir el código boilerplate)
* **Maven** (Gestor de dependencias)

---

## Requisitos Previos

Antes de ejecutar este proyecto, asegúrate de tener instalado:

* **Java Development Kit (JDK) [Versión, ej. 17 o superior]**
* **Maven [Versión, ej. 3.8.x o superior]**
* **PostgreSQL** (y una base de datos llamada `inventory_db` creada, con un usuario y contraseña configurados).

---

## Cómo Ejecutar el Proyecto

Sigue estos pasos para levantar la API en tu entorno local:

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/atabero/inventory-api
    cd product-inventory-api
    ```

2.  **Configura tu base de datos PostgreSQL:**
    * Asegúrate de que PostgreSQL esté corriendo en tu máquina.
    * Crea una base de datos llamada `inventory_db`.
    * Configura las credenciales de conexión en `src/main/resources/application.properties` (usuario y contraseña).

3.  **Compila el proyecto:**
    ```bash
    mvn clean install
    ```

4.  **Ejecuta la aplicación:**
    ```bash
    mvn spring-boot:run
    ```
    También puedes ejecutar la clase principal `InventoryApiApplication.java` directamente desde tu IDE (ej. IntelliJ IDEA, VS Code).

La API se ejecutará en `http://localhost:8080` por defecto.

---

## Endpoints de la API

A continuación se detallan los endpoints disponibles:

### Productos

* **`GET /api/products`**
    * Obtiene todos los productos.
* **`GET /api/products/{id}`**
    * Obtiene un producto por su ID.
* **`POST /api/products`**
    * Crea un nuevo producto.
    * **Body (JSON ejemplo):**
        ```json
        {
            "name": "Smartphone XYZ",
            "description": "El último modelo de smartphone",
            "price": 799.99,
            "currentStock": 100
        }
        ```
* **`PUT /api/products/{id}`**
    * Actualiza un producto existente.
    * **Body (JSON igual que POST):** Contiene la información actualizada del producto.
* **`DELETE /api/products/{id}`**
    * Elimina un producto por su ID.

### Control de Stock

* **`POST /api/products/{id}/addStock`**
    * Añade una cantidad específica al stock de un producto.
    * **Body (JSON ejemplo):**
        ```json
        {
            "quantity": 50
        }
        ```
* **`POST /api/products/{id}/removeStock`**
    * Reduce una cantidad específica del stock de un producto.
    * **Body (JSON ejemplo):**
        ```json
        {
            "quantity": 20
        }
        ```
    * **Validación:** No permite reducir el stock si el resultado sería negativo.

---

## Contacto

Si tienes alguna pregunta o sugerencia, no dudes en contactarme:

* **GitHub:** [atabero](https://github.com/atabero)
* **Email:** abtabero@proton.me

---

## Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles. 