
# 🎓 Udemy Clone – Spring Boot Backend Project

This is a full-stack ready backend project built using **Java Spring Boot**, simulating a Learning Management System (LMS) like **Udemy**. It includes user registration, role-based access, course management, student enrollment, and secure JWT-based authentication.

---

## 🚀 Features

- ✅ User Registration (Admin, Instructor, Student)
- ✅ JWT-based Login Authentication
- ✅ Role-based Access Control
- ✅ Course Creation, Update & Deletion
- ✅ Enroll & Unenroll from Courses
- ✅ View Enrollments and Enrolled Students
- ✅ Secure Spring Security Configuration
- ✅ DTO-Entity Layered Architecture

---

## 🧰 Tech Stack

| Technology      | Description                        |
|----------------|------------------------------------|
| Java 17         | Programming Language               |
| Spring Boot     | Backend Framework                  |
| Spring Security | Authentication & Authorization     |
| JWT             | Token-based Authentication         |
| JPA (Hibernate) | ORM for DB interaction             |
| H2 / MySQL      | In-memory or external DB           |
| Maven           | Build and Dependency Management    |

---

## 🗂️ Project Folder Structure

```
src/
├── controller/         # All REST APIs (User, Role, Course, Enrollment)
├── dto/                # Data Transfer Objects
├── entity/             # JPA Entities
├── mapper/             # DTO <-> Entity Mappers
├── repository/         # Spring Data JPA Interfaces
├── service/            # Business Logic Layer
├── config/             # Spring Security and JWT Config
├── util/               # Utility Classes like JWTService
└── UdemyApplication.java  # Main Class
```

---

## 🌐 REST API Endpoints

### 🔐 Authentication & Home

| Method | Endpoint             | Description                          |
|--------|----------------------|--------------------------------------|
| GET    | `/Welcome/{name}`    | Welcome message via path variable    |
| GET    | `/Welcome?name=...`  | Welcome message via query param      |
| GET    | `/Logged_In`         | Current authenticated user info      |
| GET    | `/Login`             | Login with username & password (JWT) |

---

### 👤 User APIs

| Method | Endpoint                                 | Description                         |
|--------|------------------------------------------|-------------------------------------|
| POST   | `/User/Create_Student`                   | Register a new Student              |
| POST   | `/User/Create_Admin`                     | Register a new Admin                |
| POST   | `/User/Create_Instructor`                | Register a new Instructor           |
| GET    | `/User/Get_By_id/{user_id}`              | Get user by ID                      |
| GET    | `/User/Get_By_email/{email}`             | Get user by email                   |
| DELETE | `/User/Delete_By_Id/{user_id}`           | Delete user by ID                   |
| DELETE | `/User/Delete_By_Email/{email}`          | Delete user by email                |
| PUT    | `/User/Update_Role/{user_id}/{new_role}` | Update user’s role                  |

---

### 🛡️ Role APIs

| Method | Endpoint                    | Description             |
|--------|-----------------------------|-------------------------|
| POST   | `/Role/Create_Admin_Role`   | Create ADMIN role       |
| POST   | `/Role/Create_Student_Role` | Create STUDENT role     |
| POST   | `/Role/Create_Tutor_Role`   | Create TUTOR role       |
| GET    | `/Role/Get_Role/{role_id}`  | Get role by ID          |

> 🔒 Note: All `/Role/**` endpoints are secured and access controlled.

---

### 📚 Course APIs

| Method | Endpoint                                      | Description                     |
|--------|-----------------------------------------------|---------------------------------|
| POST   | `/Course/Create_Course/{user_id}`             | Create new course               |
| GET    | `/Course/Get_Course/{course_id}`              | Get course details              |
| DELETE | `/Course/DeleteByID/{course_id}`              | Delete a course by ID           |
| PUT    | `/Course/Update_Duration/{course_id}/{dur}`   | Update course duration          |

---

### 📝 Enrollment APIs

| Method | Endpoint                                                      | Description                             |
|--------|---------------------------------------------------------------|-----------------------------------------|
| POST   | `/EnrollInCourse/{course_id}/{user_id}`                       | Enroll a user into a course             |
| GET    | `/Get_Enrollment/{enroll_id}`                                 | Get enrollment details by ID            |
| GET    | `/Get_Enrolled_students/{course_id}`                          | Get students enrolled in a course       |
| PUT    | `/UnEnroll_Student/{course_id}/{student_id}`                  | Unenroll a student from a course        |

---

## 🔐 Security Configuration (Spring Security + JWT)

- 🔑 JWT token issued on login via `/Login`
- 🔒 JWT filter validates every protected request
- 👥 Role-based access defined in `Config.java`
- 🔐 Stateless authentication
- ❌ CSRF disabled
- ✅ Public endpoints:
  - `/Home/Login`
  - `/User/Create_Student`
  - `/User/Create_Instructor`

---

## ▶️ How to Run Locally

### ✅ Requirements

- Java 17+
- Maven
- IDE (IntelliJ / Eclipse / VS Code)
```

### 🧪 Test the APIs

- Use [Postman](https://www.postman.com) to test endpoints
- Or integrate with a frontend

---

## 🧠 Key Concepts Used

- Layered architecture (Controller → Service → Repository)
- DTO to Entity Mapping
- Exception Handling via ResponseEntity
- Spring Security filters + custom authentication
- Token expiration, validation and claims extraction

---

## 🧑‍💻 Author

**Shivam (Shi-Stack-J)**  
🔗 GitHub: https://github.com/shi-stack-j/Udemy_LIte_Clone.git
📧 Email: Shivamgangwar@gmail.com

---
