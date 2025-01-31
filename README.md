# 📦 Delivery Service

## 🚀 Project Overview
This project is a **Spring Boot**-based delivery service that includes **RabbitMQ** for order processing and **SSE (Server-Sent Events)** for real-time notifications. Users can place orders, and administrators can monitor order statuses in real-time.

---

## 🛠 Tech Stack
- **Backend**: Java 11, Spring Boot, Spring Security, Spring Data JPA
- **Database**: MySQL
- **Message Queue**: RabbitMQ
- **Real-time Communication**: Server-Sent Events (SSE)
- **Build Tool**: Gradle
- **API Documentation**: Swagger

---

## 📌 Key Features
### ✅ Order Processing (RabbitMQ)
- When a user places an order, the `api` module sends the order details to the `store-admin` module via **RabbitMQ**.
- The `store-admin` module consumes the order, processes it, and stores the data in the database.

### ✅ Real-time Notifications (SSE)
- When an order status is updated, the `store-admin` module sends real-time notifications to the client using **SSE**.
- Clients maintain an **EventSource** connection to receive live status updates.

---

## 📁 Project Structure
```bash
├── api                        # User API module
│   ├── src/main/java/org/delivery/api
│   │   ├── config/rabbitmq    # RabbitMQ configuration
│   │   ├── domain/order       # Order domain (creation & sending)
│   │   ├── common/rabbitmq    # RabbitMQ Producer
│   │   └── Application.java   # Spring Boot entry point
│
├── store-admin                # Admin API module
│   ├── src/main/java/org/delivery/storeadmin
│   │   ├── config/rabbitmq    # RabbitMQ configuration
│   │   ├── domain/order       # Order domain (consumption & processing)
│   │   ├── domain/sse         # SSE connections & notifications
│   │   ├── domain/user        # Admin user management
│   │   └── Application.java   # Spring Boot entry point
│
├── common                     # Shared module (DTOs, Message models, etc.)
│   ├── src/main/java/org/delivery/common
│   │   ├── message/model      # RabbitMQ message models
│   │   └── util               # Common utilities
│
├── db                         # Database-related code (Entities, Repositories)
│
└── README.md                  # Project documentation
```

---

## 🔧 Setup Guide
### 1️⃣ Run RabbitMQ (Using Docker)
```sh
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
```
- `5672` : RabbitMQ broker port
- `15672` : RabbitMQ Management UI (`http://localhost:15672`)

### 2️⃣ Run MySQL (Using Docker)
```sh
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=delivery -p 3306:3306 -d mysql:latest
```

### 3️⃣ Configure Environment (`application.yml`)
```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
  datasource:
    url: jdbc:mysql://localhost:3306/delivery
    username: root
    password: root
```

### 4️⃣ Run the Project
```sh
# Start api module
cd api && ./gradlew bootRun

# Start store-admin module
cd ../store-admin && ./gradlew bootRun
```

---

## 📡 API Testing
### 1️⃣ Access Swagger (API Documentation)
- `http://localhost:8080/swagger-ui.html`

### 2️⃣ Place an Order (API Request)
```http
POST /api/orders
Content-Type: application/json
{
  "userId": 1,
  "storeId": 10,
  "items": [
    { "menuId": 100, "quantity": 2 },
    { "menuId": 101, "quantity": 1 }
  ]
}
```

### 3️⃣ Subscribe to Order SSE Events (JavaScript Example)
```js
const eventSource = new EventSource('http://localhost:8081/sse/orders');
eventSource.onmessage = (event) => {
    console.log('New Order Update:', event.data);
};
```

---

## 📌 Future Improvements
- Introduce **Kafka** for a more scalable messaging system
- Implement **WebSocket** for full-duplex real-time communication
- Develop an **Admin Dashboard UI** for order management

