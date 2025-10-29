# 🎓 University Course Registration System

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A comprehensive **University Course Registration System** built with **Spring Boot**, featuring role-based access control, prerequisite validation, and major-specific course restrictions. Styled with a unique **Ferrari-Academic** visual identity combining professional elegance with racing spirit.

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [System Requirements](#system-requirements)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [Default Credentials](#default-credentials)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [User Guide](#user-guide)
- [Configuration](#configuration)
- [Troubleshooting](#troubleshooting)
- [Sample Data](#sample-data)
- [Security](#security)
- [Contributing](#contributing)
- [License](#license)

---

## ✨ Features

### For Students:
- ✅ **Register for courses** based on major (Computer Engineering or Mechanical Engineering)
- ✅ **View available courses** with instructors and prerequisites
- ✅ **Track enrollment status** (Pending, Approved, Rejected)
- ✅ **View grades** and pass/fail status
- ✅ **Prerequisite validation** - Cannot register without completing required courses
- ✅ **Major restrictions** - ECE courses for Computer Eng., MNG for Mechanical Eng., MTH for both

### For Administrators:
- ✅ **Approve/Reject** student course registrations
- ✅ **Submit grades** for approved enrollments
- ✅ **View pending requests** in real-time
- ✅ **Manage course catalog** with enrollment statistics
- ✅ **Track student progress** across all enrollments

### Security Features:
- 🔒 **Spring Security** authentication & authorization
- 🔒 **BCrypt password encryption**
- 🔒 **CSRF protection** on all forms
- 🔒 **Role-based access control** (Admin vs Student)
- 🔒 **Session management** with auto-logout

### Technical Features:
- ⚡ **Auto-incrementing student IDs** starting from 250001
- ⚡ **Prerequisite chain validation**
- ⚡ **Duplicate enrollment prevention**
- ⚡ **Transaction management** for data consistency
- ⚡ **Custom exception handling** with meaningful error messages
- ⚡ **Responsive design** (mobile-friendly)
- ⚡ **Ferrari-Academic themed UI** with animations

---

## 🛠️ Tech Stack

### Backend
- **Spring Boot 3.2.0** - Application framework
- **Spring Data JPA** - Data persistence
- **Spring Security** - Authentication & Authorization
- **Hibernate** - ORM implementation
- **H2 Database** - In-memory database (development)
- **MySQL** - Production database (optional)
- **Lombok** - Boilerplate code reduction
- **Maven** - Build automation

### Frontend
- **Thymeleaf** - Server-side templating
- **HTML5 & CSS3** - Structure & styling
- **JavaScript (ES6+)** - Client-side interactivity
- **Ferrari-Academic Design System** - Custom theme

---

## 💻 System Requirements

- **Java JDK**: 17 or higher
- **Maven**: 3.6+ (or use included wrapper)
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code
- **Browser**: Chrome, Firefox, Safari, or Edge (latest versions)
- **Memory**: 2GB RAM minimum
- **Disk Space**: 500MB free space

---

## 📥 Installation & Setup

### Step 1: Generate Base Project

1. Go to [https://start.spring.io/](https://start.spring.io/)
2. Configure:
   - **Project**: Maven
   - **Language**: Java
   - **Spring Boot**: 3.2.0
   - **Group**: `com.university`
   - **Artifact**: `course-registration-system`
   - **Package name**: `com.university.registration`
   - **Java**: 17
3. Add dependencies:
   - Spring Web
   - Spring Data JPA
   - Spring Security
   - Thymeleaf
   - H2 Database
   - MySQL Driver
   - Lombok
4. Click **GENERATE** and download ZIP

### Step 2: Extract and Setup

```bash
unzip course-registration-system.zip
cd course-registration-system
```

### Step 3: Add Project Files

Create the following package structure in `src/main/java/com/university/registration/`:

```
registration/
├── CourseRegistrationApplication.java
├── entity/
│   ├── Student.java
│   ├── Course.java
│   ├── Instructor.java
│   ├── Enrollment.java
│   └── User.java
├── repository/
│   ├── StudentRepository.java
│   ├── CourseRepository.java
│   ├── InstructorRepository.java
│   ├── EnrollmentRepository.java
│   └── UserRepository.java
├── service/
│   ├── CourseRegistrationService.java
│   └── UserDetailsServiceImpl.java
├── controller/
│   ├── HomeController.java
│   ├── StudentController.java
│   └── AdminController.java
├── config/
│   ├── SecurityConfig.java
│   └── DataInitializer.java
└── exception/
    ├── WrongCourseMajorException.java
    ├── CourseAlreadyAddedException.java
    ├── CourseNotFoundException.java
    ├── WrongCourseNameException.java
    ├── PrerequisiteNotMetException.java
    ├── StudentNotFoundException.java
    └── GlobalExceptionHandler.java
```

### Step 4: Add Resources

In `src/main/resources/`:

**templates/**
- `login.html`
- `student-dashboard.html`
- `admin-dashboard.html`

**static/css/**
- `style.css`

**static/js/**
- `main.js`

### Step 5: Update pom.xml

Add this dependency manually:

```xml
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity6</artifactId>
</dependency>
```

### Step 6: Build Project

```bash
./mvnw clean install
```

---

## 🚀 Running the Application

### Option 1: Using Maven

```bash
./mvnw spring-boot:run
```

### Option 2: Using IDE

Right-click on `CourseRegistrationApplication.java` → Run

### Option 3: Using JAR

```bash
./mvnw clean package
java -jar target/course-registration-system-1.0.0.jar
```

### Access Application

```
http://localhost:8080
```

---

## 🔑 Default Credentials

### Admin Account
```
Username: admin
Password: admin123
```

### Student Accounts

**Student 1 (Computer Engineering)**
```
Username: student1
Password: student123
Student ID: 250001
```

**Student 2 (Mechanical Engineering)**
```
Username: student2
Password: student123
Student ID: 250002
```

---

## 📁 Project Structure

```
course-registration-system/
│
├── src/
│   ├── main/
│   │   ├── java/com/university/registration/
│   │   │   ├── CourseRegistrationApplication.java
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   ├── controller/
│   │   │   ├── config/
│   │   │   └── exception/
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── templates/
│   │       │   ├── login.html
│   │       │   ├── student-dashboard.html
│   │       │   └── admin-dashboard.html
│   │       └── static/
│   │           ├── css/style.css
│   │           └── js/main.js
│   │
│   └── test/
│
├── pom.xml
└── README.md
```

---

## 🗄️ Database Schema

### Entity Relationships

```
Student (1) ──────── (*) Enrollment (*) ──────── (1) Course
                                                       │
                                                       │ (*)
                                                       │
                                                  Instructor
```

### Tables

**students**
- `id` (BIGINT, PK) - Auto-generated starting from 250001
- `name` (VARCHAR)
- `major` (VARCHAR) - COMPUTER_ENGINEERING or MECHANICAL_ENGINEERING

**courses**
- `code` (VARCHAR, PK) - Course code (e.g., ECE121)
- `name` (VARCHAR)

**instructors**
- `id` (BIGINT, PK)
- `name` (VARCHAR)

**enrollments**
- `id` (BIGINT, PK)
- `student_id` (FK → students)
- `course_code` (FK → courses)
- `status` (VARCHAR) - PENDING, APPROVED, REJECTED
- `grade` (DOUBLE) - 0-100
- `passed` (BOOLEAN)

**users**
- `id` (BIGINT, PK)
- `username` (VARCHAR, UNIQUE)
- `password` (VARCHAR) - BCrypt encrypted
- `role` (VARCHAR) - ADMIN or STUDENT
- `student_id` (FK → students, nullable)

**Join Tables:**
- `course_instructors` - Links courses to instructors
- `course_prerequisites` - Self-referencing prerequisites

---

## 🌐 API Endpoints

### Public
- `GET /` - Redirect to login
- `GET /login` - Login page
- `POST /login` - Submit credentials
- `POST /logout` - Logout

### Student (Requires STUDENT role)
- `GET /student/dashboard` - Student dashboard
- `POST /student/register?courseCode={code}` - Register for course

### Admin (Requires ADMIN role)
- `GET /admin/dashboard` - Admin panel
- `POST /admin/approve/{id}` - Approve enrollment
- `POST /admin/reject/{id}` - Reject enrollment
- `POST /admin/grade/{id}?grade={grade}&passed={true/false}` - Submit grade

---

## 📖 User Guide

### For Students

1. **Login** with student credentials
2. **View available courses** in the dashboard
3. **Click "Register"** button for desired course
4. **Check "My Enrollments"** section for status
5. **View grades** once admin submits them

### For Administrators

1. **Login** with admin credentials
2. **Review pending requests** in "Pending Enrollment Requests"
3. **Click "Approve"** or "Reject"** for each request
4. **Submit grades** in "Grade Management" section
5. **View statistics** in "All Courses" section

---

## ⚙️ Configuration

### application.properties

```properties
# Server
server.port=8080

# H2 Database (Development)
spring.datasource.url=jdbc:h2:mem:coursedb
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Thymeleaf
spring.thymeleaf.cache=false
```

### Switch to MySQL

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/course_registration_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

---

## 🐛 Troubleshooting

### Port 8080 in use
```properties
# Change in application.properties
server.port=8081
```

### Lombok not working
1. Install Lombok plugin in IDE
2. Enable annotation processing in IDE settings

### Tables not created
```properties
# Verify in application.properties
spring.jpa.hibernate.ddl-auto=create-drop
```

### CSS/JS not loading
1. Check files in `src/main/resources/static/`
2. Clear browser cache (Ctrl+Shift+R)
3. Restart application

### Login fails
1. Check H2 console at `/h2-console`
2. Verify user exists in `users` table
3. Check console logs for errors

---

## 📝 Sample Data

### Courses Loaded on Startup

**Math (Both Majors):**
- MTH101 - Calculus I
- MTH102 - Calculus II (prereq: MTH101)
- MTH201 - Linear Algebra (prereq: MTH101)
- MTH301 - Differential Equations (prereq: MTH102, MTH201)

**Computer Engineering:**
- ECE121 - Digital Logic Design
- ECE221 - Data Structures (prereq: ECE121)
- ECE321 - Control Systems (prereq: ECE121, MTH201)
- ECE322 - Computer Architecture (prereq: ECE221)
- ECE421 - Embedded Systems (prereq: ECE321, ECE322)
- ECE422 - Artificial Intelligence (prereq: ECE221, MTH301)

**Mechanical Engineering:**
- MNG101 - Engineering Mechanics
- MNG201 - Thermodynamics (prereq: MNG101, MTH101)
- MNG301 - Fluid Mechanics (prereq: MNG201, MTH102)
- MNG302 - Heat Transfer (prereq: MNG301)
- MNG401 - Machine Design (prereq: MNG101, MTH201)

---

## 🔐 Security

### Implemented
- ✅ BCrypt password hashing
- ✅ CSRF protection
- ✅ Role-based authorization
- ✅ Session management
- ✅ SQL injection prevention (JPA)
- ✅ XSS protection (Thymeleaf)

### Production Recommendations
- Use HTTPS (SSL/TLS)
- Implement rate limiting
- Add password complexity rules
- Enable session timeout
- Add account lockout
- Implement audit logging
- Use environment variables for secrets

---

## 🧪 Testing Scenarios

### Test 1: Valid Registration
```
Login: student1
Action: Register for ECE121
Expected: ✅ Success
```

### Test 2: Wrong Major
```
Login: student1 (Computer Eng)
Action: Register for MNG101
Expected: ❌ Error - Wrong major
```

### Test 3: Missing Prerequisite
```
Login: student1
Action: Register for ECE321
Expected: ❌ Error - Need ECE121 first
```

### Test 4: Math Course
```
Login: Any student
Action: Register for MTH101
Expected: ✅ Success - Math for both
```

### Test 5: Duplicate
```
Login: student1
Action: Register for ECE121 twice
Expected: ❌ Error - Already enrolled
```

---

## 🎨 Design System

### Colors
- **Ferrari Red**: #DC0000
- **Ferrari Yellow**: #FFF200
- **Academic Navy**: #002147
- **Academic Gold**: #C5A572

### Features
- Racing stripe decoration
- Gradient backgrounds
- Animated buttons
- Responsive tables
- Status badges

---

## 🤝 Contributing

1. Fork the repository
2. Create feature branch: `git checkout -b feature/amazing-feature`
3. Commit changes: `git commit -m 'Add amazing feature'`
4. Push to branch: `git push origin feature/amazing-feature`
5. Open Pull Request

---

## 📄 License

MIT License - See LICENSE file for details

---

## 👥 Authors

- **Your Name** - Initial work

---

## 🙏 Acknowledgments

- Spring Boot Team
- Thymeleaf Team
- Scuderia Ferrari (design inspiration)
- Open source community

---

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/yourusername/course-registration-system/issues)
- **Email**: support@example.com
- **Documentation**: [Wiki](https://github.com/yourusername/course-registration-system/wiki)

---

## 🗺️ Roadmap

### Version 2.0
- [ ] Email notifications
- [ ] PDF transcript generation
- [ ] Course capacity limits
- [ ] Student profile pictures
- [ ] Course ratings
- [ ] Real-time notifications

### Version 3.0
- [ ] AI course recommendations
- [ ] LMS integration
- [ ] Video conferencing
- [ ] Payment gateway
- [ ] Mobile app

---

## ✅ Quick Start Checklist

- [ ] Install Java 17+
- [ ] Download project from Spring Initializr
- [ ] Add all Java files to packages
- [ ] Add HTML templates
- [ ] Add CSS/JS files
- [ ] Update pom.xml (add Thymeleaf Security)
- [ ] Run `./mvnw clean install`
- [ ] Run application
- [ ] Open http://localhost:8080
- [ ] Login and test features
- [ ] Celebrate! 🎉

---

<div align="center">

### ⚡ Built with Spring Boot | Styled with Ferrari Spirit ⚡

**Forza Ferrari! 🏎️💨**

Made with ❤️ by [Your Name]

</div>
