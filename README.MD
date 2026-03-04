# EPW — Products API (Spring Boot + PostgreSQL)

API REST de **productos** (CRUD) hecha siguiendo el mismo paso a paso del profe con el ejemplo de **Activities**, pero adaptada a `com.epw.products` y solo con lo que aplica para Products.

---

## Qué se entregó

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

## Arquitectura 

Paquete base: `com.epw.products`

- **entity**: `Product` (JPA)
- **repository**: `ProductRepository`
- **service**: `ProductService` (interfaz)
- **service.impl**: `ProductServiceImpl` (lógica + mapper `toResponse`)
- **controller**: `ProductController` (endpoints)
- **dto**: `CreateProductRequest`, `UpdateProductRequest`, `ProductResponse`
- **exception**: `ResourceNotFoundException`, `ApiError`, `GlobalExceptionHandler`

---

## Modelo `Product`

Campos:
- `id`
- `name`
- `description`
- `price`
- `stock`
- `createdAt` (**LocalDateTime**, se asigna automático con `@PrePersist`)

---

## Evidencia en Postman 

> Carpeta: `screenshots/`

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
Actualización del producto (ej. cambio de precio para id = 1).

![PUT](screenshots/ApiRest_PutActualizacionPrecioId1.png)

### 7) Eliminar — `DELETE /api/products/{id}`
Eliminación correcta (204 No Content).

![DELETE](screenshots/ApiRest_Delete.png)

### 8) Eliminar (no existe) — `DELETE /api/products/{id}`
Cuando el id no existe se devuelve 404 controlado.

![DELETE Validation](screenshots/ApiRest_DeleteValidacion.png)

---

## Autor
Kevin Rivera
