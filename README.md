#  Event Management System API 🚀

Welcome to the **Event Management System**, a robust and secure backend API built with **Spring Boot**. This project provides a complete solution for managing events, allowing customers to register and manage their events, and organizers to oversee all activities on the platform.



---

## ✨ Key Features

This API is divided into two main roles, each with a specific set of permissions and capabilities:

### 🤵 **For Customers**
-   **User Authentication**: Secure registration and JWT-based login.
-   **Event Creation**: Customers can register new events with all necessary details.
-   **Event Management**: Easily view, update, and modify event details.
-   **Event Cancellation**: Ability to cancel an event with a specified reason.

### 👑 **For Organizers**
-   **Full Access Control**: Organizers can view all events and customer details on the platform.
-   **Customer Management**: Block or unblock customers from booking new events.
-   **Event Oversight**: Organizers have the authority to cancel any event if necessary.
-   **Secure Endpoints & Graceful Error Handling**: All organizer actions are protected, and the API provides clear, consistent error responses.

---

## 🛠️ Technologies & Tools

This project is built with a modern and powerful tech stack to ensure performance, scalability, and security.

-   **Backend**: Java 17, Spring Boot 3
-   **Database**: MySQL
-   **Security**: Spring Security, JSON Web Tokens (JWT)
-   **Data Persistence**: Spring Data JPA (Hibernate)
-   **Build Tool**: Maven
-   **API Testing**: Postman

---
## 🏛️ Project Structure

The project follows a standard layered architecture to ensure separation of concerns and maintainability.

-   **`config`**: Contains security configurations, including JWT filters.
-   **`controller`**: Handles all incoming HTTP requests and API endpoints.
-   **`dto`**: (Data Transfer Objects) - Used to shape the data for API responses and requests, preventing direct exposure of entity models.
-   **`exception`**: Includes a global exception handler to manage errors gracefully across the application.
-   **`model`**: Defines the JPA entities that map to database tables.
-   **`repository`**: Contains the Spring Data JPA interfaces for database operations.
-   **`service`**: Implements the core business logic of the application.

---

## 🔑 API Endpoints

Here is a summary of the available API endpoints. All operational endpoints require a valid JWT token for authorization.

### **Authentication (`/api/auth`)**
| Method | Endpoint                       | Description                  |
| :----- | :----------------------------- | :--------------------------- |
| `POST` | `/register/customer`           | Registers a new customer.    |
| `POST` | `/register/organizer`          | Registers a new organizer.   |
| `POST` | `/login`                       | Logs in a user and returns a JWT. |

### **Customer Actions (`/api/customer`)**
| Method   | Endpoint                       | Description                  |
| :------- | :----------------------------- | :--------------------------- |
| `POST`     | `/events/register`             | Creates a new event.         |
| `GET`      | `/events/{eventId}`            | Retrieves event details.     |
| `PUT`      | `/events/{eventId}`            | Updates an existing event.   |
| `DELETE`   | `/events/{eventId}`            | Cancels an event.            |

### **Organizer Actions (`/api/organizer`)**
| Method | Endpoint                       | Description                  |
| :----- | :----------------------------- | :--------------------------- |
| `GET`    | `/events`                      | Gets a list of all events.   |
| `GET`    | `/customers`                   | Gets a list of all customers.|
| `PUT`    | `/customers/{customerId}/block`| Blocks a customer.           |
| `PUT`    | `/customers/{customerId}/unblock`| Unblocks a customer.       |
| `DELETE` | `/events/{eventId}`            | Cancels any event.           |

---

## 🚀 Getting Started

To get a local copy up and running, follow these simple steps.

### **Prerequisites**
-   JDK 17 or later
-   Maven
-   MySQL Server
-   Postman (for testing)

### **Installation & Setup**

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/CHVivek7/Event_Management.git](https://github.com/CHVivek7/Event_Management.git)
    ```
2.  **Navigate to the project directory:**
    ```sh
    cd Event_Management
    ```
3.  **Configure the database:**
    -   Open `src/main/resources/application.properties`.
    -   Create a MySQL database named `eventmanagement`.
    -   Update the `spring.datasource.username` and `spring.datasource.password` with your MySQL credentials.
4.  **Build and run the application:**
    ```sh
    mvn spring-boot:run
    ```
The API will be running at `http://localhost:8080`.

---

## 🧪 API Testing

You can use the provided [Postman commands and inputs](#) to test all the functionalities of the API, from user registration to event management. Ensure you always use the correct JWT token for authorized endpoints!

---

## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.
