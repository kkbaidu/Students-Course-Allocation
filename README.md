# Student Course Registration System# Student Course Registration System

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)A comprehensive university course registration system similar to the University of Ghana MIS Web, built with **Java 17** and **Spring Boot 3**.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)

[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Neon-blue.svg)](https://neon.tech/)## 🎯 Overview

[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This system manages student course registration with three user roles: **ADMIN**, **INSTRUCTOR**, and **STUDENT**. It provides a complete backend REST API following clean architecture principles and best practices.

A comprehensive REST API backend for university student course registration, inspired by the University of Ghana MIS Web system. This system provides role-based access control for students, instructors, and administrators with JWT authentication.

## ✨ Features

## 📋 Table of Contents

### 👨‍💼 Admin Features

- [Features](#-features)

- [System Architecture](#-system-architecture)- ✅ Create and manage programmes (e.g., BSc Computer Science)

- [Technology Stack](#-technology-stack)- ✅ Create courses with programme, level, and semester assignments

- [Getting Started](#-getting-started)- ✅ Assign instructors to courses

- [API Documentation](#-api-documentation)- ✅ Create student accounts and assign them to programmes and levels

- [Database Schema](#-database-schema)- ✅ Open/close registration periods for specific semesters

- [User Roles & Permissions](#-user-roles--permissions)- ✅ View all registered students per course

- [Security](#-security)

- [Project Structure](#-project-structure)### 👨‍🏫 Instructor Features

- [Configuration](#-configuration)

- [Testing](#-testing)- ✅ View all assigned courses

- [Deployment](#-deployment)- ✅ View list of students registered for each course

- [Contributing](#-contributing)

- [License](#-license)### 👨‍🎓 Student Features

## ✨ Features- ✅ View available courses for their programme, level, and current semester

- ✅ Register for courses (only when registration is open)

### Student Features- ✅ View registered courses

- 👤 User authentication with JWT tokens- ✅ Prevented from duplicate course registration

- 📚 Browse available courses filtered by programme, level, and semester- ✅ Restricted to courses matching their programme and level

- ✍️ Register for courses during open registration periods

- 📋 View personal course registrations## 🏗️ Architecture

- 🔍 Check course prerequisites and credit hours

The application follows a **clean layered architecture**:

### Instructor Features

- 👨‍🏫 View assigned courses```

- 👥 Access student lists for assigned courses┌─────────────────────────────────────┐

- 📊 Monitor course enrollments│ Controller Layer (REST) │

├─────────────────────────────────────┤

### Administrator Features│ Service Layer (Business) │

- 🏫 Create and manage academic programmes (BSc CS, BSc IT, etc.)├─────────────────────────────────────┤

- 📖 Create and manage courses with prerequisites│ Repository Layer (Data Access) │

- 👨‍🎓 Create student accounts with programme assignment├─────────────────────────────────────┤

- 👨‍🏫 Create instructor accounts and manage profiles│ Domain Layer (Entities) │

- 🔗 Assign instructors to courses└─────────────────────────────────────┘

- 🗓️ Open and close course registration periods```

- 📈 Full system oversight

### Technology Stack

### System Features

- 🔐 JWT-based stateless authentication- **Java 17**

- 🛡️ Role-based access control (RBAC)- **Spring Boot 3.2.0**

- 📝 Comprehensive input validation- **Spring Security** with JWT authentication

- ⚠️ Global exception handling- **Spring Data JPA** with Hibernate

- 📊 Interactive Swagger/OpenAPI documentation- **PostgreSQL** (primary database)

- 🗄️ PostgreSQL database with Neon cloud hosting- **H2** (testing)

- 🚀 RESTful API design- **Lombok** (reduce boilerplate)

- 📱 CORS-enabled for frontend integration- **Maven** (build tool)

- **Bean Validation** (DTO validation)

## 🏗️ System Architecture

## 📦 Domain Model

```````

┌─────────────────────────────────────────────────────────────┐### Entities

│                     Client Applications                      │

│            (Web, Mobile, Desktop, Postman, etc.)            │- **User**: Base user with authentication credentials and roles

└─────────────────────────────────────────────────────────────┘- **Student**: Student profile linked to User

                              │- **Programme**: Academic programmes (e.g., BSc Computer Science)

                    ┌─────────▼─────────┐- **Course**: Individual courses with programme, level, and semester

                    │   JWT Auth Layer  │- **CourseAssignment**: Links instructors to courses

                    │  (Bearer Token)    │- **CourseRegistration**: Student course enrollments

                    └─────────┬─────────┘- **RegistrationStatus**: Controls when registration is open/closed

                              │

┌─────────────────────────────▼─────────────────────────────┐### Enums

│                      REST API Layer                        │

│  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐    │- **Role**: ADMIN, INSTRUCTOR, STUDENT

│  │   Auth   │ │  Admin   │ │Instructor│ │ Student  │    │- **Level**: LEVEL100, LEVEL200, LEVEL300, LEVEL400

│  │Controller│ │Controller│ │Controller│ │Controller│    │- **Semester**: FIRST, SECOND

│  └──────────┘ └──────────┘ └──────────┘ └──────────┘    │

└────────────────────────────┬──────────────────────────────┘## 🔐 Security

                              │

┌─────────────────────────────▼─────────────────────────────┐- JWT-based authentication

│                      Service Layer                         │- Role-based access control (RBAC)

│  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐    │- Password encryption using BCrypt

│  │   Auth   │ │  Admin   │ │Instructor│ │ Student  │    │- Stateless session management

│  │ Service  │ │ Service  │ │ Service  │ │ Service  │    │- Secured endpoints per role

│  └──────────┘ └──────────┘ └──────────┘ └──────────┘    │

└────────────────────────────┬──────────────────────────────┘## 🚀 Getting Started

                              │

┌─────────────────────────────▼─────────────────────────────┐### Prerequisites

│                    Repository Layer                        │

│  (Spring Data JPA - Hibernate ORM)                        │- Java 17 or higher

└────────────────────────────┬──────────────────────────────┘- Maven 3.6+

                              │- PostgreSQL 12+ (or use H2 for development)

┌─────────────────────────────▼─────────────────────────────┐

│                 PostgreSQL Database (Neon)                 │### Database Setup

│  Tables: users, students, instructors, programmes,        │

│  courses, course_assignments, course_registrations        │1. Create a PostgreSQL database:

└───────────────────────────────────────────────────────────┘

``````sql

CREATE DATABASE course_registration_db;

## 🛠️ Technology Stack```



| Layer | Technology |2. Update `src/main/resources/application.yml` with your database credentials:

|-------|-----------|

| **Backend Framework** | Spring Boot 3.2.0 |```yaml

| **Language** | Java 17 |spring:

| **Security** | Spring Security + JWT (jsonwebtoken 0.12.3) |  datasource:

| **ORM** | Spring Data JPA + Hibernate 6.3.1 |    url: jdbc:postgresql://localhost:5432/course_registration_db

| **Database** | PostgreSQL 15 (Neon Serverless) |    username: your_username

| **Validation** | Jakarta Validation (Bean Validation 3.0) |    password: your_password

| **Documentation** | Springdoc OpenAPI 3.0 (Swagger UI 2.2.0) |```

| **Build Tool** | Maven 3.8.7 |

| **Utilities** | Lombok |### Build and Run



## 🚀 Getting Started```bash

# Clone the repository

### Prerequisitescd student-course-allocation



- **Java 17** or higher# Build the project

- **Maven 3.6+**mvn clean install

- **PostgreSQL** (or Neon account for cloud database)

- **Git**# Run the application

mvn spring-boot:run

### Installation```



1. **Clone the repository**The application will start on `http://localhost:8080`

   ```bash

   git clone https://github.com/yourusername/student-course-allocation.git## 📋 Sample Data

   cd student-course-allocation

   ```The application automatically loads sample data on first run:



2. **Set up environment variables**### Login Credentials



   Create a `.env` file in the project root (or copy from `.env.example`):| Role       | Username  | Password      |

   ```bash| ---------- | --------- | ------------- |

   cp .env.example .env| Admin      | admin     | admin123      |

   ```| Instructor | dr.mensah | instructor123 |

   | Instructor | dr.asante | instructor123 |

   Edit `.env` with your database credentials:| Student    | student1  | student123    |

   ```properties| Student    | student2  | student123    |

   DATABASE_URL=jdbc:postgresql://your-host:5432/your-database?sslmode=require

   DATABASE_USERNAME=your-username### Pre-loaded Data

   DATABASE_PASSWORD=your-password

   JWT_SECRET=your-secret-key-min-256-bits- **Programmes**: BSc Computer Science, BSc Information Technology

   ```- **Courses**: DCIT201, DCIT203, DCIT205, DCIT207

- **Registration Status**: Open for First Semester 2024/2025

3. **Build the project**

   ```bash## 🔌 API Endpoints

   mvn clean install

   ```### Authentication



4. **Run the application**#### Login

   ```bash

   mvn spring-boot:run```http

   ```POST /auth/login

Content-Type: application/json

   The application will start on `http://localhost:8080`

{

5. **Access Swagger UI**  "username": "student1",

     "password": "student123"

   Open your browser and navigate to:}

```````

http://localhost:8080/swagger-ui.htmlResponse:

````{

"token": "eyJhbGc...",

### Quick Start with Sample Data  "type": "Bearer",

"username": "student1",

The application automatically loads sample data on first run:  "role": "STUDENT",

"message": "Login successful"

| Role | Username | Password |}

|------|----------|----------|```

| Admin | admin | admin123 |

| Instructor | dr.mensah | instructor123 |### Admin Endpoints

| Instructor | dr.asante | instructor123 |

| Student | student1 | student123 |All admin endpoints require `ROLE_ADMIN` and a valid JWT token in the `Authorization` header:

| Student | student2 | student123 |

````

## 📚 API DocumentationAuthorization: Bearer <token>

````

### Interactive Documentation (Swagger UI)

#### Programme Management

The best way to explore and test the API is through the interactive Swagger UI:

```http

![Swagger UI Interface](img/swagger-interface-screenshot.png)# Create Programme

POST /admin/programmes

**Access Swagger UI:** http://localhost:8080/swagger-ui.html{

  "code": "BSC-CS",

### API Endpoints Overview  "name": "BSc Computer Science",

  "description": "Bachelor of Science in Computer Science",

#### 🔓 Authentication Endpoints (Public)  "active": true

```http}

POST /auth/login - User login (returns JWT token)

```# Get All Programmes

GET /admin/programmes

#### 🔐 Admin Endpoints (Requires ADMIN role)

```http# Get Programme by ID

POST   /admin/programmes                        - Create programmeGET /admin/programmes/{id}

GET    /admin/programmes                        - List all programmes```

POST   /admin/courses                           - Create course

GET    /admin/courses                           - List all courses#### Course Management

POST   /admin/students                          - Create student account

GET    /admin/students                          - List all students```http

POST   /admin/instructors                       - Create instructor account# Create Course

GET    /admin/instructors                       - List all instructorsPOST /admin/courses

POST   /admin/courses/{courseId}/assign-instructor - Assign instructor{

POST   /admin/registration/open                 - Open registration  "courseCode": "DCIT201",

POST   /admin/registration/close                - Close registration  "courseName": "Programming Fundamentals",

```  "description": "Introduction to programming",

  "creditHours": 3,

#### 🔐 Instructor Endpoints (Requires INSTRUCTOR role)  "programmeId": 1,

```http  "level": "LEVEL200",

GET /instructor/courses                    - Get my assigned courses  "semester": "FIRST",

GET /instructor/courses/{courseId}/students - Get students in my course  "active": true

```}



#### 🔐 Student Endpoints (Requires STUDENT role)# Get All Courses

```httpGET /admin/courses

GET  /student/courses       - Get available courses for my programme

POST /student/register      - Register for a course# Get Course by ID

GET  /student/registrations - Get my course registrationsGET /admin/courses/{id}

````

### Using the API with JWT#### Instructor Assignment

1. **Login to get JWT token:**```http

   ````bash# Assign Instructor to Course

   curl -X POST http://localhost:8080/auth/login \POST /admin/courses/{courseId}/assign-instructor

     -H "Content-Type: application/json" \{

     -d '{"username":"student1","password":"student123"}'  "instructorId": 2

   ```}
   ````

````

2. **Use token in subsequent requests:**

   ```bash#### Student Management

   curl -X GET http://localhost:8080/student/courses \

     -H "Authorization: Bearer YOUR_JWT_TOKEN"```http

   ```# Create Student

POST /admin/students

3. **In Swagger UI:**{

   - Click the "Authorize" 🔒 button  "username": "student3",

   - Enter: `Bearer YOUR_JWT_TOKEN`  "password": "student123",

   - Click "Authorize" and "Close"  "firstName": "Kofi",

   - Now all requests will include your token  "lastName": "Owusu",

  "email": "kofi.owusu@st.ug.edu.gh",

For detailed API usage examples, see [API_TESTING.md](API_TESTING.md)  "studentId": "10956791",

  "programmeId": 1,

## 🗄️ Database Schema  "level": "LEVEL200",

  "yearOfAdmission": 2023

### Entity Relationship Diagram}



```# Get All Students

┌─────────────┐       ┌──────────────┐       ┌─────────────┐GET /admin/students

│    User     │       │   Student    │       │  Programme  │```

├─────────────┤       ├──────────────┤       ├─────────────┤

│ id (PK)     │───────│ id (PK)      │       │ id (PK)     │#### Registration Management

│ username    │  1:1  │ user_id (FK) │───────│ code        │

│ password    │       │ student_id   │  N:1  │ name        │```http

│ email       │       │ programme_id │       │ description │# Open Registration

│ role        │       │ level        │       │ active      │POST /admin/registration/open

│ enabled     │       │ year         │       └─────────────┘{

└─────────────┘       └──────────────┘              │  "semester": "FIRST",

       │                      │                      │  "academicYear": 2024

       │                      │                      │ N:1}

       │ N:1                  │ N:1                  │

       │                      │                      │# Close Registration

       │              ┌───────▼────────┐       ┌────▼──────┐POST /admin/registration/close?semester=FIRST&academicYear=2024

       │              │Course          │       │  Course   │```

       │              │Registration    │       ├───────────┤

       │              ├────────────────┤       │ id (PK)   │#### Reports

       │              │ id (PK)        │───────│ code      │

       │              │ student_id (FK)│  N:1  │ name      │```http

       │              │ course_id (FK) │       │ credits   │# Get Students for a Course

       │              │ semester       │       │ level     │GET /admin/courses/{courseId}/students

       │              │ academic_year  │       │ semester  │```

       │              │ reg_date       │       │ prog_id   │

       │              └────────────────┘       └───────────┘### Instructor Endpoints

       │                                              │

       │ N:1                                          │ N:1Requires `ROLE_INSTRUCTOR`:

       │                                              │

       │              ┌────────────────┐              │```http

       └──────────────│Course          │──────────────┘# Get Assigned Courses

                      │Assignment      │GET /instructor/courses

                      ├────────────────┤

                      │ id (PK)        │# Get Students for a Course

                      │ course_id (FK) │GET /instructor/courses/{courseId}/students

                      │ instructor_id  │```

                      │ assigned_date  │

                      └────────────────┘### Student Endpoints

                              │

                              │ N:1Requires `ROLE_STUDENT`:

                              │

                      ┌───────▼────────┐```http

                      │  Instructor    │# Get Available Courses

                      ├────────────────┤GET /student/courses/available

                      │ id (PK)        │

                      │ user_id (FK)   │# Register for Course

                      │ staff_id       │POST /student/courses/register

                      │ department     │{

                      │ office         │  "courseId": 1

                      │ phone          │}

                      └────────────────┘

```# Get Registered Courses

GET /student/courses/registered

### Key Tables```



- **users** - Authentication credentials and roles## 🧪 Testing with cURL

- **students** - Student profiles linked to users

- **instructors** - Instructor profiles linked to users### 1. Login as Student

- **programmes** - Academic programmes (BSc CS, BSc IT, etc.)

- **courses** - Course catalog with prerequisites```bash

- **course_assignments** - Instructor-to-course mappingscurl -X POST http://localhost:8080/auth/login \

- **course_registrations** - Student course enrollments  -H "Content-Type: application/json" \

- **registration_status** - Controls when registration is open/closed  -d '{"username":"student1","password":"student123"}'

````

For detailed schema documentation, see [DATABASE_SCHEMA.md](DATABASE_SCHEMA.md)

### 2. Get Available Courses

## 👥 User Roles & Permissions

```bash

### Role Hierarchycurl -X GET http://localhost:8080/student/courses/available \

  -H "Authorization: Bearer <your_token>"

```

ADMIN (Full System Access)

│### 3. Register for Course

├── Create/Manage Programmes

├── Create/Manage Courses```bash

├── Create Student Accountscurl -X POST http://localhost:8080/student/courses/register \

├── Create Instructor Accounts -H "Content-Type: application/json" \

├── Assign Instructors to Courses -H "Authorization: Bearer <your_token>" \

└── Control Registration Periods -d '{"courseId":1}'

````

INSTRUCTOR (Course Management)

  │### 4. View Registered Courses

  ├── View Assigned Courses

  └── View Students in Courses```bash

curl -X GET http://localhost:8080/student/courses/registered \

STUDENT (Course Registration)  -H "Authorization: Bearer <your_token>"

  │```

  ├── View Available Courses

  ├── Register for Courses## 🛡️ Error Handling

  └── View My Registrations

```The API returns standardized error responses:



### Permission Matrix```json

{

| Feature | Admin | Instructor | Student |  "status": 404,

|---------|-------|------------|---------|  "message": "Course not found with id: '999'",

| Login | ✅ | ✅ | ✅ |  "timestamp": "2024-11-08T10:30:00"

| Create Programme | ✅ | ❌ | ❌ |}

| Create Course | ✅ | ❌ | ❌ |```

| Create Student | ✅ | ❌ | ❌ |

| Create Instructor | ✅ | ❌ | ❌ |### Common HTTP Status Codes

| Assign Instructor | ✅ | ❌ | ❌ |

| Open/Close Registration | ✅ | ❌ | ❌ |- `200 OK`: Successful request

| View Assigned Courses | ✅ | ✅ | ❌ |- `201 Created`: Resource created successfully

| View Course Students | ✅ | ✅ (own courses) | ❌ |- `400 Bad Request`: Validation error

| Browse Courses | ✅ | ✅ | ✅ |- `401 Unauthorized`: Missing or invalid authentication

| Register for Courses | ❌ | ❌ | ✅ |- `403 Forbidden`: Insufficient permissions or registration closed

| View My Registrations | ❌ | ❌ | ✅ |- `404 Not Found`: Resource not found

- `409 Conflict`: Duplicate resource (e.g., already registered)

## 🔒 Security- `500 Internal Server Error`: Server error



### Authentication Flow## 📁 Project Structure



1. **User Login** → POST `/auth/login` with username/password```

2. **Server Validates** → Checks credentials against databasesrc/main/java/com/example/registration/

3. **JWT Generation** → Creates signed token with user details├── config/

4. **Client Storage** → Client stores token (localStorage, cookies)│   └── SampleDataLoader.java          # Sample data initialization

5. **Authenticated Requests** → Include token in `Authorization: Bearer {token}` header├── controller/

6. **Token Validation** → Server validates token on each request│   ├── AdminController.java           # Admin REST endpoints

7. **Access Control** → Checks user role for endpoint authorization│   ├── AuthController.java            # Authentication endpoints

│   ├── InstructorController.java      # Instructor REST endpoints

### Security Features│   └── StudentController.java         # Student REST endpoints

├── domain/

- ✅ **Stateless JWT Authentication** - No server-side sessions│   ├── Course.java                    # Course entity

- ✅ **BCrypt Password Hashing** - Secure password storage│   ├── CourseAssignment.java          # Instructor-Course mapping

- ✅ **Role-Based Access Control** - Fine-grained permissions│   ├── CourseRegistration.java        # Student-Course enrollment

- ✅ **CSRF Protection** - Disabled for stateless API│   ├── Level.java                     # Level enum

- ✅ **Input Validation** - Jakarta Bean Validation│   ├── Programme.java                 # Programme entity

- ✅ **SQL Injection Prevention** - Parameterized queries (JPA)│   ├── RegistrationStatus.java        # Registration control

- ✅ **HTTPS Ready** - SSL/TLS support│   ├── Role.java                      # Role enum

- ✅ **Token Expiration** - 24-hour token lifetime│   ├── Semester.java                  # Semester enum

- ✅ **CORS Configuration** - Configurable cross-origin policies│   ├── Student.java                   # Student entity

│   └── User.java                      # User entity

### Environment Variable Security├── dto/

│   ├── AssignInstructorDto.java       # Instructor assignment DTO

**⚠️ NEVER commit sensitive credentials to Git!**│   ├── AuthRequest.java               # Login request DTO

│   ├── AuthResponse.java              # Login response DTO

This project uses environment variables to keep credentials secure:│   ├── CourseDto.java                 # Course DTO

│   ├── CourseRegistrationDto.java     # Registration DTO

1. **Database credentials** are stored in `.env` (gitignored)│   ├── CreateStudentDto.java          # Student creation DTO

2. **JWT secret key** is stored in environment variables│   ├── ProgrammeDto.java              # Programme DTO

3. **Sample `.env.example`** provides template without real credentials│   ├── RegistrationStatusDto.java     # Registration status DTO

4. **application.yml** references environment variables using `${VARIABLE_NAME}` syntax│   ├── StudentCourseRegisterDto.java  # Course registration DTO

│   └── StudentDto.java                # Student DTO

## 📁 Project Structure├── exception/

│   ├── DuplicateResourceException.java

```│   ├── GlobalExceptionHandler.java    # Global error handler

student-course-allocation/│   ├── RegistrationClosedException.java

├── src/│   ├── ResourceNotFoundException.java

│   ├── main/│   └── UnauthorizedException.java

│   │   ├── java/com/example/registration/├── repository/

│   │   │   ├── config/              # Configuration classes│   ├── CourseAssignmentRepository.java

│   │   │   │   ├── OpenApiConfig.java       # Swagger configuration│   ├── CourseRegistrationRepository.java

│   │   │   │   └── SampleDataLoader.java    # Sample data loader│   ├── CourseRepository.java

│   │   │   ├── controller/          # REST API endpoints│   ├── ProgrammeRepository.java

│   │   │   │   ├── AdminController.java│   ├── RegistrationStatusRepository.java

│   │   │   │   ├── AuthController.java│   ├── StudentRepository.java

│   │   │   │   ├── InstructorController.java│   └── UserRepository.java

│   │   │   │   └── StudentController.java├── security/

│   │   │   ├── domain/              # JPA entities│   ├── JwtAuthenticationFilter.java   # JWT filter

│   │   │   │   ├── Course.java│   ├── JwtTokenProvider.java          # JWT token utilities

│   │   │   │   ├── CourseAssignment.java│   └── SecurityConfig.java            # Security configuration

│   │   │   │   ├── CourseRegistration.java├── service/

│   │   │   │   ├── Instructor.java│   ├── AdminService.java              # Admin service interface

│   │   │   │   ├── Level.java (enum)│   ├── AuthService.java               # Auth service interface

│   │   │   │   ├── Programme.java│   ├── InstructorService.java         # Instructor service interface

│   │   │   │   ├── RegistrationStatus.java│   ├── StudentService.java            # Student service interface

│   │   │   │   ├── Role.java (enum)│   └── impl/

│   │   │   │   ├── Semester.java (enum)│       ├── AdminServiceImpl.java

│   │   │   │   ├── Student.java│       ├── AuthServiceImpl.java

│   │   │   │   └── User.java│       ├── CustomUserDetailsService.java

│   │   │   ├── dto/                 # Data Transfer Objects│       ├── InstructorServiceImpl.java

│   │   │   │   ├── AssignInstructorDto.java│       └── StudentServiceImpl.java

│   │   │   │   ├── AuthRequest.java└── RegistrationApplication.java       # Main application class

│   │   │   │   ├── AuthResponse.java```

│   │   │   │   ├── CourseDto.java

│   │   │   │   ├── CreateInstructorDto.java## 🎓 Business Rules

│   │   │   │   ├── CreateStudentDto.java

│   │   │   │   ├── InstructorDto.java1. **Registration Period Control**: Students can only register when admin has opened registration for the current semester

│   │   │   │   ├── ProgrammeDto.java2. **Course Eligibility**: Students can only see and register for courses matching their:

│   │   │   │   └── StudentCourseRegisterDto.java   - Programme

│   │   │   ├── exception/           # Exception handling   - Level

│   │   │   │   ├── GlobalExceptionHandler.java   - Current semester

│   │   │   │   └── ResourceNotFoundException.java3. **No Duplicates**: Students cannot register for the same course twice

│   │   │   ├── repository/          # Data access layer4. **Instructor Access**: Instructors can only view students for courses they're assigned to

│   │   │   │   ├── CourseAssignmentRepository.java5. **Data Integrity**: Username, email, student ID, programme code, and course code must be unique

│   │   │   │   ├── CourseRegistrationRepository.java

│   │   │   │   ├── CourseRepository.java## 🔮 Future Enhancements

│   │   │   │   ├── InstructorRepository.java

│   │   │   │   ├── ProgrammeRepository.java- [ ] Course prerequisites validation

│   │   │   │   ├── RegistrationStatusRepository.java- [ ] Maximum credit hours per semester limit

│   │   │   │   ├── StudentRepository.java- [ ] Course withdrawal/drop functionality

│   │   │   │   └── UserRepository.java- [ ] Grade submission by instructors

│   │   │   ├── security/            # Security configuration- [ ] Grade viewing by students

│   │   │   │   ├── JwtAuthenticationFilter.java- [ ] Audit logs for admin actions

│   │   │   │   ├── JwtTokenProvider.java- [ ] Email notifications

│   │   │   │   └── SecurityConfig.java- [ ] Course capacity limits

│   │   │   ├── service/             # Business logic- [ ] Waitlist functionality

│   │   │   │   ├── AdminService.java- [ ] Academic calendar integration

│   │   │   │   ├── AuthService.java- [ ] Transcript generation

│   │   │   │   ├── InstructorService.java

│   │   │   │   ├── StudentService.java## 📝 API Documentation

│   │   │   │   └── impl/            # Service implementations

│   │   │   │       ├── AdminServiceImpl.javaFor detailed API documentation, you can integrate **Swagger/OpenAPI**:

│   │   │   │       ├── AuthServiceImpl.java

│   │   │   │       ├── CustomUserDetailsService.javaAdd to `pom.xml`:

│   │   │   │       ├── InstructorServiceImpl.java

│   │   │   │       └── StudentServiceImpl.java```xml

│   │   │   └── RegistrationApplication.java  # Main application<dependency>

│   │   └── resources/    <groupId>org.springdoc</groupId>

│   │       └── application.yml      # Application configuration    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>

│   └── test/                        # Test classes    <version>2.2.0</version>

├── img/                             # Documentation images</dependency>

│   └── swagger-interface-screenshot.png```

├── .env                             # Environment variables (gitignored)

├── .env.example                     # Environment variables templateAccess Swagger UI at: `http://localhost:8080/swagger-ui.html`

├── .gitignore                       # Git ignore file

├── API_TESTING.md                   # API testing guide## 🤝 Contributing

├── DATABASE_SCHEMA.md               # Database documentation

├── DEPLOYMENT.md                    # Deployment guide1. Fork the repository

├── NEON_SETUP.md                    # Neon database setup2. Create a feature branch: `git checkout -b feature/new-feature`

├── QUICKSTART.md                    # Quick start guide3. Commit your changes: `git commit -am 'Add new feature'`

├── SWAGGER_GUIDE.md                 # Swagger usage guide4. Push to the branch: `git push origin feature/new-feature`

├── pom.xml                          # Maven configuration5. Submit a pull request

└── README.md                        # This file

```## 📄 License



## ⚙️ ConfigurationThis project is open-source and available under the MIT License.



### Application Configuration (application.yml)## 👨‍💻 Author



```yamlBuilt with ❤️ using Spring Boot and clean architecture principles.

spring:

  datasource:## 📞 Support

    url: ${DATABASE_URL}

    username: ${DATABASE_USERNAME}For issues or questions, please open an issue on the GitHub repository.

    password: ${DATABASE_PASSWORD}

    driver-class-name: org.postgresql.Driver---



  jpa:**Happy Coding! 🚀**

    hibernate:
      ddl-auto: update  # Creates/updates schema automatically
    show-sql: true      # Logs SQL queries (disable in production)

jwt:
  secret: ${JWT_SECRET:default-secret-key-change-in-production}
  expiration: 86400000  # 24 hours

springdoc:
  swagger-ui:
    path: /swagger-ui.html
    enabled: true
````

### Environment Variables

Create a `.env` file with:

```properties
DATABASE_URL=jdbc:postgresql://your-host:5432/your-database?sslmode=require
DATABASE_USERNAME=your-username
DATABASE_PASSWORD=your-password
JWT_SECRET=your-256-bit-secret-key
```

**Important:** The `.env` file is gitignored and will NOT be committed to your repository.

### Database Configuration

#### Local PostgreSQL

```properties
DATABASE_URL=jdbc:postgresql://localhost:5432/course_registration
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=yourpassword
```

#### Neon Cloud Database

```properties
DATABASE_URL=jdbc:postgresql://ep-xxx.aws.neon.tech/neondb?sslmode=require
DATABASE_USERNAME=neondb_owner
DATABASE_PASSWORD=your-neon-password
```

For detailed Neon setup instructions, see [NEON_SETUP.md](NEON_SETUP.md)

## 🧪 Testing

### Testing with Swagger UI

1. Start the application: `mvn spring-boot:run`
2. Open http://localhost:8080/swagger-ui.html
3. Login with test credentials
4. Authorize with JWT token
5. Test endpoints interactively

### Testing with cURL

```bash
# Login
TOKEN=$(curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}' \
  | jq -r '.token')

# Create Programme (Admin)
curl -X POST http://localhost:8080/admin/programmes \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "code": "BSC-MATH",
    "name": "BSc Mathematics",
    "description": "Bachelor of Science in Mathematics"
  }'

# Get Available Courses (Student)
curl -X GET "http://localhost:8080/student/courses?level=LEVEL_100&semester=FIRST" \
  -H "Authorization: Bearer $TOKEN"
```

### Testing with Postman

1. Import the API from OpenAPI spec: http://localhost:8080/api-docs
2. Set up environment variable for JWT token
3. Create authentication request
4. Test all endpoints

For complete testing guide, see [API_TESTING.md](API_TESTING.md)

## 🚀 Deployment

### Local Deployment

```bash
# Build JAR
mvn clean package

# Run JAR
java -jar target/student-course-allocation-1.0.0.jar
```

### Docker Deployment

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```bash
# Build and run
docker build -t student-registration .
docker run -p 8080:8080 --env-file .env student-registration
```

### Cloud Deployment Options

- **Heroku** - Git-based deployment
- **Railway** - Auto-deployment from GitHub
- **Render** - Free tier with PostgreSQL
- **AWS Elastic Beanstalk** - Scalable deployment
- **Google Cloud Run** - Serverless containers

For detailed deployment instructions, see [DEPLOYMENT.md](DEPLOYMENT.md)

## 🔧 Development

### Running in Development Mode

```bash
# With live reload (requires spring-boot-devtools)
mvn spring-boot:run

# With specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Building

```bash
# Clean and build
mvn clean install

# Skip tests
mvn clean install -DskipTests

# Run tests only
mvn test
```

### Code Style

- Follow Java naming conventions
- Use Lombok to reduce boilerplate
- Document public APIs with Javadoc
- Write meaningful commit messages

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Commit your changes** (`git commit -m 'Add amazing feature'`)
4. **Push to branch** (`git push origin feature/amazing-feature`)
5. **Open a Pull Request**

### Development Guidelines

- Write unit tests for new features
- Update documentation for API changes
- Follow existing code structure
- Ensure all tests pass before submitting PR
- Update CHANGELOG.md with your changes

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📧 Contact & Support

- **Developer:** Your Name
- **Email:** your.email@example.com
- **GitHub:** [@yourusername](https://github.com/yourusername)
- **Issues:** [GitHub Issues](https://github.com/yourusername/student-course-allocation/issues)

## 🙏 Acknowledgments

- Inspired by University of Ghana MIS Web System
- Built with Spring Boot and Spring Security
- Database hosted on Neon PostgreSQL
- API documentation powered by Springdoc OpenAPI

## 📚 Additional Documentation

- [Quick Start Guide](QUICKSTART.md) - Get up and running quickly
- [API Testing Guide](API_TESTING.md) - Detailed API usage examples
- [Database Schema](DATABASE_SCHEMA.md) - Complete database documentation
- [Swagger Guide](SWAGGER_GUIDE.md) - Using Swagger UI
- [Deployment Guide](DEPLOYMENT.md) - Production deployment
- [Neon Setup](NEON_SETUP.md) - Cloud database configuration

## 🗓️ Roadmap

### Phase 1 (Current) ✅

- ✅ Core API development
- ✅ JWT authentication
- ✅ Role-based access control
- ✅ Swagger documentation
- ✅ Cloud database integration

### Phase 2 (Planned)

- ⏳ Email notifications
- ⏳ Course prerequisites validation
- ⏳ Waitlist management
- ⏳ Grade management
- ⏳ Transcript generation

### Phase 3 (Future)

- 📅 Payment integration
- 📅 Academic calendar
- 📅 Advising system
- 📅 Analytics dashboard
- 📅 Mobile app

---

<div align="center">

**⭐ Star this repo if you find it helpful!**

Made with ❤️ for University Course Registration

</div>
