# 📬 Greetings

**Greetings** is a lightweight **Spring Boot** application designed to demonstrate **event-driven communication** using **Apache Kafka** combined with **email notifications**.

For simplicity and learning purposes, the project includes a **Kafka topic auto-creation configuration**, allowing the application to create the required topic at startup without manual broker setup.

The application also contains a **scheduler mechanism** that periodically publishes new greeting messages to the Kafka broker. These messages are then consumed and processed asynchronously, triggering the sending of email notifications.

This project showcases asynchronous processing, clean configuration, scheduled jobs, and integration between messaging systems and external services such as **Gmail SMTP**.

---

### 🔐 Security Notice

> ⚠️ **Important**  
> The configuration property `mail.smtp.ssl.trust: "*"` is **not recommended for production environments**.  
> It is used here **only for demonstration purposes** to simplify local development and avoid SSL trust issues.

Always prioritize security when working with email services and messaging systems:

- Never commit real credentials to version control
- Do not share your email address or Gmail App Password
- Prefer environment variables or secret managers for sensitive data
- Misconfigured email or messaging services can lead to **data breaches**

This project is intended for **educational purposes only** and should not be used as-is in production systems.

---

## ☕ Buy me a coffee

If this content was useful, your support makes a real difference and helps me continue sharing knowledge with the community!

[![Buy me a coffee](https://raw.githubusercontent.com/Aruw/Resources/refs/heads/main/buy-me-a-coffe-banner.jpg)](https://buymeacoffee.com/aruw)

---

## ✨ Features

- 📩 Consume messages from **Apache Kafka**
- ✉️ Send email notifications using **Gmail SMTP**
- ⚡ Asynchronous and event-driven architecture
- 📦 Simple, clean, and educational project structure
- 🧪 Ready for testing with Spring Kafka Test utilities
- 🧵 **Virtual Threads** enabled for efficient concurrency
- 🐳 Kafka and Zookeeper managed via **Docker Compose**
- ⏰ Periodically publish messages to Kafka using a **Scheduler**

---

## 🛠️ Technologies Used

- Maven
- Lombok
- Java 25
- Zookeeper
- Spring Mail
- Spring Kafka
- Apache Kafka
- Spring Boot 3.5.4
- Docker & Docker Compose

---

## ✅ Prerequisites

Before running the project, make sure you have:

- Maven 3.9+
- Java 25 installed
- Docker & Docker Compose
- Internet access (for SMTP communication)
- [⚠️ Setup Gmail account with App Password](#-gmail-app-password-setup-guide)

---

## 🔐 Gmail App Password Setup Guide

> ⚠️ **Important**  
> Google requires **2-Step Verification** to be enabled before you can create an App Password.

---

### 1️⃣ Step 1: Enable 2-Step Verification

1. Sign in to your Google Account:  
   https://myaccount.google.com/
2. On the left navigation panel, click "Security & sign-in".
3. Scroll down to the section titled "How you sign in to Google."
4. Click on "2-Step Verification".
5. Click "Get Started" and follow the prompts to verify your phone number or backup method.
6. Once confirmed, click Turn On.

---

### 2️⃣ Step 2: Generate an App Password

After 2-Step Verification is active, you can generate the 16-digit code for your app.

1. Open the following link:     
    https://myaccount.google.com/apppasswords
2. Under the "App name" field, enter a descriptive name.
3. Click on "Create".
4. A window will pop up showing a 16-character code in a yellow box.
5. Copy the password! It will be used to configure the application.

> ⚠️ **Important**   
> Google will **not show it again**. If you lose it, you must revoke it and generate a new one.

---

### 3️⃣ Configure application properties

#### ✉️ Gmail SMTP configuration

Edit `application.yml` and configure it with your gmail and the app password generated in the previous step:

```yaml
spring:
  mail:
    username: your_origin_email@gmail.com
    password: your_gmail_app_password
```

#### 📧 Add the email destination

```yaml
email:
  address:
    destiny: your_destiny_email@gmail.com
```

---

## 🐳 Messaging Infrastructure (Kafka & Zookeeper via Docker)

This project uses **Spring Boot Docker Compose integration** to automatically start **Apache Kafka** and **Zookeeper** when the application runs.

### How it works

- When the application starts, Spring Boot detects the `docker-compose.yml` file
- **Zookeeper** and **Kafka** containers are automatically started
- The application connects to Kafka using the configured bootstrap server
- Kafka is used to consume events that trigger email notifications
- When the application stops, the containers can be stopped automatically

This removes the need to manually start Kafka and ensures a consistent local development environment.

### ⚠️ Requirement

**Docker Desktop must be installed and running** before starting the application.

If Docker is not available, the application will fail to start because the Kafka broker will not be reachable.

Supported environments:
- Docker Engine (Linux)
- Docker Desktop (Windows / macOS)

---

## 🚀 Running the Project

### 1️⃣ Clone the repository

```bash
git clone https://github.com/Aruw/Greetings.git
cd greetings
```

### 2️⃣ Start Kafka and Zookeeper

```bash
docker compose up -d
```

This will start:
- Zookeeper on port **2181**
- Kafka broker on port **9092**


### 3️⃣ Run the application

```bash
mvn spring-boot:run
```

---


## 📂 Project Structure

```
src/main/java/com/aruw/greetings
 ├── config
 ├── model
 ├── scheduler
 ├── service
 ├── utils
 └── GreetingsApplication.java
```

---

## 🤝 Contributing

Pull requests are welcome!  
Feel free to open issues, suggest improvements, or propose new features. 💡

---

## 📜 License

This project is provided for educational and personal use.

---

Enjoy building! 🚀
