# Car Rental System — Spring Boot backend

Three-layer architecture: Controller (HTTP + DTOs) → Service (business rules) → Repository (Spring Data JPA/H2). `domain` contains JPA entities and enums, `mapper` maps entities to DTOs, `exception` provides centralized HTTP errors, and `config` seeds data.

## Run locally
Install JDK 17+ and Maven. In this directory run `mvn spring-boot:run`. API base URL: `http://localhost:8080`. H2 console: `http://localhost:8080/h2-console`; JDBC URL `jdbc:h2:mem:carrental`, username `sa`, blank password.

## API endpoints
| Resource | Endpoints |
|---|---|
| Cars | GET/POST `/api/cars`, GET/PUT/DELETE `/api/cars/{id}`, GET `/api/cars/{id}/rentals` |
| Customers | GET/POST `/api/customers`, GET/PUT/DELETE `/api/customers/{id}`, GET `/api/customers/{id}/rentals`, GET `/api/customers/{id}/cars` |
| Rentals | GET/POST `/api/rentals`, GET/PUT/DELETE `/api/rentals/{id}`, PATCH `/api/rentals/{id}/return`, GET `/api/rentals/{id}/details` |

Optional filters: `GET /api/cars?status=AVAILABLE&category=SUV`, `GET /api/customers?lastName=Example`, `GET /api/rentals?status=ACTIVE`.

## Sample requests
```bash
curl http://localhost:8080/api/cars
curl http://localhost:8080/api/customers/1/rentals
curl -X POST http://localhost:8080/api/rentals -H 'Content-Type: application/json' -d '{"carId":11,"customerId":11,"startDate":"2030-01-10","endDate":"2030-01-13"}'
curl -X PATCH http://localhost:8080/api/rentals/1/return
```

## Business rules
- Rental prices are computed as number of days × car daily rate.
- A car must be AVAILABLE to create a rental; creating an active rental marks it RENTED, returning it marks it AVAILABLE.
- A car/customer with rental history cannot be deleted.
- Request DTOs are validated, conflicts return 409, missing IDs return 404.
- H2 is in-memory and 12 cars, 12 customers, and 10 rentals are seeded on every restart.

## Deployment
Not deployed yet. Add the public base URL after deployment. H2 data resets on restart. No credentials required for API. Before production, secure or disable the H2 console.

## Known limitations
This is a starter implementation and has not been compiled or integration-tested in this environment. Concurrent bookings require locking for production. The sample rental data uses placeholder car models and customers. No Swagger/OpenAPI or demo video is included yet.
