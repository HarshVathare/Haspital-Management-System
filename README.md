 🏥 Hospital Management System API (Spring Boot)

A complete **Hospital Management System backend API** built using **Spring Boot** following real industry standards.
The system supports **User Management, Role Conversion (Patient → Doctor), Appointment Booking, Insurance Claims, Billing, and Admin Dashboard** with **JWT Authentication & Role-Based Authorization**.

---

🚀 Features

 🔐 Authentication & Authorization

* JWT based login & registration
* Role-based access control (ADMIN, DOCTOR, PATIENT, INSURANCE_AGENT)
* Secure password hashing (BCrypt)
* Refresh token support

---

 🧑‍💼 Admin Module

* Convert Patient to Doctor
* Convert User to Insurance Agent
* Approve Doctor Registration
* Block / Unblock Users
* Assign Roles
* View dashboard statistics

---

 👨‍⚕️ Doctor Module

* Manage profile & specialization
* View assigned appointments
* Update appointment status (COMPLETED)
* Access patient medical records

---

 🧑‍🤝‍🧑 Patient Module

* Book appointments with doctors
* View appointment history
* Upload medical records
* Apply for insurance claims

---

 📅 Appointment Module

* Book / Cancel / Reschedule appointments
* Slot availability validation
* Appointment status tracking (BOOKED, CANCELLED, COMPLETED)

---

 🏥 Insurance Module

* Manage insurance policies
* Apply for insurance claims
* Approve / Reject claims (Admin / Insurance Agent)
* Track claim status (PENDING, APPROVED, REJECTED)

---

 💳 Billing & Payment Module

* Generate bill after appointment
* Calculate insurance coverage
* Payment history tracking
* Invoice generation

---

 🧱 Tech Stack

**Backend**

* Java 17
* Spring Boot
* Spring Security + JWT
* Spring Data JPA (Hibernate)
* MySQL / PostgreSQL

**Tools**

* Swagger (OpenAPI)
* Maven
* Docker (optional)
* GitHub Actions (CI/CD optional)

---

 🏗 Project Structure

```
src/main/java/com/example/hms
│
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── config
├── exception
└── util
```

---

 🗃 Database Entities

* Users
* Doctors
* Patients
* Appointments
* Insurance Policies
* Insurance Claims
* Bills
* Payments
* Medical Records
* Roles

---

 🔐 Security

* JWT Authentication
* Role Based Authorization
* Global Exception Handling
* Input Validation using DTOs
* CORS configuration
* Password encryption using BCrypt

---

 📌 Main APIs (Sample)

 Auth

```
POST   /api/auth/register
POST   /api/auth/login
```

 Admin

```
PUT /api/admin/convert-to-doctor/{userId}
PUT /api/admin/approve-doctor/{doctorId}
PUT /api/admin/block-user/{userId}
```
 Appointment

```
POST /api/appointments
GET  /api/appointments/patient/{id}
PUT  /api/appointments/{id}/cancel
```

 Insurance

```
POST /api/insurance/claim
PUT  /api/insurance/claims/{id}/approve
```

---

 ⚙ Setup Instructions

1. Clone repository

```
git clone https://github.com/your-username/hospital-management-system.git
```

2. Configure database in `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hms_db
    username: root
    password: password
```

3. Run application

```
mvn spring-boot:run
```

4. Open Swagger UI

```
http://localhost:8080/swagger-ui.html
```

---

 🧪 Testing

* Use Postman or Swagger UI for API testing
* JWT token required for secured APIs
* Admin-only APIs protected using ROLE_ADMIN

---

 🌟 Future Enhancements

* Email & SMS notifications
* PDF invoice generation
* Doctor availability calendar
* Frontend with React + TypeScript
* Docker deployment
* Cloud hosting (AWS / Azure)

---
