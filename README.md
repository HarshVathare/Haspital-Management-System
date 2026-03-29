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

 System – API Endpoint List
🔐 1. Authentication & Authorization

Public
POST   /api/auth/register
POST   /api/auth/login
POST   /api/auth/refresh-token
POST   /api/auth/forgot-password
POST   /api/auth/reset-password
GET    /api/auth/verify-email
POST   /api/auth/logout

 Admin
 
PUT /api/admin/convert-to-doctor/{userId}
PUT /api/admin/approve-doctor/{doctorId}
PUT /api/admin/block-user/{userId}

 Appointment

POST /api/appointments
GET  /api/appointments/patient/{id}
PUT  /api/appointments/{id}/cancel

 Insurance

POST /api/insurance/claim
PUT  /api/insurance/claims/{id}/approve
