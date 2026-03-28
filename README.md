# KinalApp - Documentación del Proyecto

Se ha desarrollado una API REST empleando las tecnologías más recientes del ecosistema Java. Su propósito es gestionar el ciclo de ventas de un negocio, permitiendo operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre entidades clave como clientes, productos, usuarios y transacciones de venta.

## Análisis de la solución

* **Stack tecnológico:** Se utilizan versiones actuales y estables. Java 21 incorpora las últimas mejoras de rendimiento, y Spring Boot 4 (estándar en el contexto actual de 2026) simplifica la configuración y el despliegue.
* **Arquitectura de datos:** MySQL actúa como motor de almacenamiento persistente, mientras que Maven gestiona las dependencias del proyecto.
* **Estructura de endpoints:** La API organiza los recursos de forma clara:
    * **Inventario:** Control de productos y seguimiento de existencias.
    * **Ventas:** Registro detallado de transacciones, permitiendo asociar múltiples productos a cada factura mediante las entidades `Venta` y `DetalleVenta`.
    * **Seguridad y control:** Administración de usuarios y filtrado por estado (activo/inactivo).

## Proceso de instalación

El flujo típico para poner en marcha el proyecto es el siguiente:

1. **Preparar el entorno:** Asegurarse de que el motor de base de datos (MySQL) y el JDK estén instalados.
2. **Clonar el repositorio:** Obtener el código desde GitHub.
3. **Configurar la aplicación:** Revisar el archivo `application.properties` para definir la conexión a la base de datos y el puerto de red (en este caso, `8081`).
4. **Probar los endpoints:** Usar herramientas como Postman o un navegador para interactuar con los recursos, por ejemplo, accediendo a la lista de clientes.

## Observación técnica sobre los endpoints

En la definición de los endpoints se utiliza `/{dpi}` para buscar, eliminar y actualizar registros. Aunque el identificador usado es el *DPI* (característico de Guatemala), en el desarrollo de software es común denominarlo genéricamente `{id}`. Esta personalización resulta adecuada para el contexto local.

Los tres endpoints que comparten la misma URL (`/{dpi}`) se diferencian mediante los métodos HTTP:
- `GET` → consultar un registro.
- `DELETE` → eliminar un registro.
- `PUT` / `PATCH` → actualizar un registro.

## Tecnologías empleadas

- **Java 21**
- **Spring Boot 4.0.2**
- **Maven** (gestor de dependencias)
- **MySQL** (sistema gestor de base de datos)

## Requisitos previos

Antes de ejecutar la aplicación, es necesario tener instalado:

- JDK 17 o superior
- Maven
- Una instancia activa de MySQL

**Opcional:** Postman para probar los endpoints.

## Instalación y ejecución

1. Clonar el repositorio: `https://github.com/agalvez-2022303/KinalApp.git`
2. Abrir el proyecto en IntelliJ IDEA.
3. Iniciar MySQL en el equipo.
4. Conectarse a la instancia activa de MySQL.
5. En IntelliJ, navegar a `src/main/java/com/albertogalvez`.
6. Ejecutar la clase principal `KinalAppApplication`.
7. Revisar el archivo `resources/application.properties` para confirmar el puerto utilizado.
8. Abrir el navegador y acceder a `http://localhost:8086/clientes`.

## Endpoints

### Cliente
- `/clientes` → Listar todos los clientes y crear un nuevo cliente.
- `/estado` → Listar solo los clientes activos.
- `/{dpi}` → Buscar, eliminar o actualizar un cliente según su DPI.

### Usuario
- `/usuarios` → Listar todos los usuarios y crear un nuevo usuario.
- `/estado` → Listar solo los usuarios activos.
- `/{dpi}` → Buscar, eliminar o actualizar un usuario según su DPI.

### Producto
- `/productos` → Listar todos los productos y crear un nuevo producto.
- `/estado` → Listar solo los productos activos.
- `/{dpi}` → Buscar, eliminar o actualizar un producto según su DPI.
- `/stock` → Listar el nombre del producto y la cantidad disponible.

### Venta
- `/ventas` → Listar todas las ventas y registrar una nueva venta.
- `/estado` → Listar solo las ventas activas.
- `/{dpi}` → Buscar, eliminar o actualizar una venta según su DPI.

### DetalleVenta
- `/detalleVentas` → Listar todos los detalles de venta y crear un nuevo detalle.
- `/estado` → Listar solo los detalles activos.
- `/{dpi}` → Buscar, eliminar o actualizar un detalle de venta según su DPI.