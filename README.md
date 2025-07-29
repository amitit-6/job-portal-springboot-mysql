# Job Portal System 🧑‍💼💼

A full-stack web application built using **Spring Boot**, **Java**, **MySQL**, and **Thymeleaf** that allows employers to post jobs and applicants to apply for them. Includes role-based login, job management, and application tracking.

---

## 🔧 Technologies Used

- Java 24
- Spring Boot Framework
- Spring Security
- Maven
- Thymeleaf
- MySQL
- HTML, CSS (Bootstrap)
- Git, NetBeans IDE

---

## 📌 Features

👨‍💼 **Employer Role:**
- Register/Login
- Post new jobs
- View applications

👨‍🎓 **Applicant Role:**
- Register/Login
- View all available jobs
- Apply to jobs

🔐 **Authentication & Authorization:**
- Role-based login
- Session management using Spring Security

📊 **Dashboards:**
- Separate dashboard for Employers and Applicants

---

## 📂 Project Structure

```
src/
 └── main/
     ├── java/com/amit/job/
     │   ├── controller/
     │   ├── model/
     │   ├── repository/
     │   └── config/
     └── resources/
         ├── templates/
         └── application.properties
```

---

## ⚙️ How to Run Locally

1. Clone this repo:
   ```bash
   git clone https://github.com/your-username/job-portal-springboot-mysql.git
   cd job-portal-springboot-mysql
   ```

2. Import into NetBeans/IntelliJ as a Maven project.

3. Set up MySQL and create a database:
   ```sql
   CREATE DATABASE job_portal;
   ```

4. Update your MySQL credentials in `application.properties`.

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

6. Access at:  
   ```
   http://localhost:8080/
   ```

---

## 📌 Future Enhancements

- Resume upload feature  
- Email notifications  
- Admin dashboard  
- Job filters and search

---

## 📄 License

This project is open-source and free to use for educational purposes.

---

## 🤝 Contact

Created by **Amit Sidhdhapura**  
Feel free to connect via [LinkedIn](https://www.linkedin.com/in/amit-sidhdhapura-1231b731b) or fork and contribute!
