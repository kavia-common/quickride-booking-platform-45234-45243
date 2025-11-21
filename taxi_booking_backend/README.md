# Taxi Booking Backend (Spring Boot)

Runs in container `taxi_booking_backend` on port 3001.
Open Swagger UI at: http://localhost:3001/swagger-ui.html

Features:
- User registration and retrieval
- Create booking (REQUESTED)
- Assign driver (ASSIGNED)
- Start ride (IN_PROGRESS)
- Complete ride (COMPLETED)
- Cancel booking (from REQUESTED or ASSIGNED)
- Driver availability (set and list available)
- List bookings by user/driver
- H2 in-memory DB with JPA
- Seed data for quick preview

Run:
- ./gradlew bootRun
- Server on port 3001

Sample API calls (curl):
- Create user
  curl -X POST http://localhost:3001/api/users -H "Content-Type: application/json" -d '{"name":"Alice","email":"alice2@example.com"}'
- List users
  curl http://localhost:3001/api/users
- Create booking
  curl -X POST http://localhost:3001/api/bookings -H "Content-Type: application/json" -d '{"userId":1,"pickupLocation":"A","dropoffLocation":"B"}'
- List available drivers
  curl http://localhost:3001/api/drivers/available
- Assign driver
  curl -X POST http://localhost:3001/api/bookings/1/assign -H "Content-Type: application/json" -d '{"driverId":1}'
- Start ride
  curl -X POST http://localhost:3001/api/bookings/1/start
- Complete ride
  curl -X POST http://localhost:3001/api/bookings/1/complete
- Cancel booking
  curl -X POST http://localhost:3001/api/bookings/2/cancel
- Driver availability
  curl -X PATCH http://localhost:3001/api/drivers/1/availability -H "Content-Type: application/json" -d '{"status":"AVAILABLE"}'

Notes:
- Persistence is in-memory (H2). TODO: replace with real DB and proper migrations.
- Validation errors return 400 with details.
