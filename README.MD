# 🛍️ EPW — Products API

<p align="center">
  <b>Spring Boot + PostgreSQL</b><br/>
  API REST de <b>productos</b> (CRUD), construida siguiendo el flujo del ejemplo de <i>Activities</i>,
  adaptada a <code>com.epw.products</code> y enfocada únicamente en <b>Products</b>.
</p>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-25-007396?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-4.0.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img alt="PostgreSQL" src="https://img.shields.io/badge/PostgreSQL-DB-336791?style=for-the-badge&logo=postgresql&logoColor=white"/>
  <img alt="API REST" src="https://img.shields.io/badge/API-REST-0A66C2?style=for-the-badge"/>
</p>

---

## ✅ Qué se entregó

- CRUD completo:
  - `POST /api/products`
  - `GET /api/products`
  - `GET /api/products/{id}`
  - `PUT /api/products/{id}`
  - `DELETE /api/products/{id}`
- DTOs para request/response + `@Valid`
- Manejo global de respuestas de error (404 y validaciones) con `GlobalExceptionHandler`
- Persistencia con JPA + PostgreSQL

---

## 🧱 Arquitectura

> **Paquete base:** `com.epw.products`

| Capa | Componente | Descripción |
|---|---|---|
| `entity` | `Product` | Entidad JPA principal. |
| `repository` | `ProductRepository` | Acceso a datos con Spring Data JPA. |
| `service` | `ProductService` | Contrato de lógica de negocio. |
| `service.impl` | `ProductServiceImpl` | Implementación de lógica + mapper `toResponse`. |
| `controller` | `ProductController` | Exposición de endpoints REST. |
| `dto` | `CreateProductRequest`, `UpdateProductRequest`, `ProductResponse` | Modelos de entrada/salida de API. |
| `exception` | `ResourceNotFoundException`, `ApiError`, `GlobalExceptionHandler` | Manejo centralizado de errores. |

---

## 📦 Modelo `Product`

| Campo | Tipo | Nota |
|---|---|---|
| `id` | `Long` | Identificador único. |
| `name` | `String` | Nombre del producto. |
| `description` | `String` | Descripción del producto. |
| `price` | `BigDecimal` | Precio unitario. |
| `stock` | `Integer` | Cantidad disponible. |
| `createdAt` | `LocalDateTime` | Se asigna automáticamente con `@PrePersist`. |

---

## 🧪 Evidencia en Postman

> 📁 Carpeta: `screenshots/`

### 1) Crear producto — `POST /api/products`
Se crea un producto y la respuesta devuelve el `id` y `createdAt`.

![POST](screenshots/ApiRest_Post.png)

### 2) Crear producto (validación) — `POST /api/products`
Ejemplo de request inválido (campos requeridos / valores inválidos). Se devuelve 400 con detalle por campo.

![POST Validation](screenshots/ApiRest_PostValidation.png)

### 3) Listar productos — `GET /api/products`
Lista de productos registrados en la BD.

![GET](screenshots/ApiRest_Get.png)

### 4) Consultar por id — `GET /api/products/{id}`
Consulta del producto específico (ej. id = 1).

![GET By Id](screenshots/ApiRest_GetEspecifico.png)

### 5) Consultar por id (no existe) — `GET /api/products/{id}`
Cuando el id no existe se devuelve 404 con respuesta controlada por el handler.

![GET Validation](screenshots/ApiRest_GetValidacion.png)

### 6) Actualizar — `PUT /api/products/{id}`
Actualización del producto (ej. cambio de stock para id = 1).

![PUT](screenshots/ApiRest_PutActualizacionPrecioId1.png)

### 7) Eliminar — `DELETE /api/products/{id}`
Eliminación correcta (204 No Content).

![DELETE](screenshots/ApiRest_Delete.png)

### 8) Eliminar (no existe) — `DELETE /api/products/{id}`
Cuando el id no existe se devuelve 404 controlado.

![DELETE Validation](screenshots/ApiRest_DeleteValidacion.png)

---

## 👤 Autor

**Kevin Rivera**
