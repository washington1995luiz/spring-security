# Spring Security JWT Authentication Project

This project demonstrates a robust implementation of JWT (JSON Web Token) based authentication and authorization using Spring Security. It showcases how to protect API endpoints, manage user roles and permissions, and handle user authentication securely.

## Overview

The application provides the following core features:

*   **JWT Authentication:** Uses JWT for secure token-based authentication.
*   **Role-Based Access Control (RBAC):** Implements RBAC with roles (ADMIN, CUSTOMER, VENDOR) and fine-grained permissions.
*   **User Management:** Supports user registration, login, and persistence.
*   **Database Migrations:** Uses Flyway for database schema management and data initialization.
*   **Secure Password Storage:** Employs BCrypt for password hashing.
*   **Custom Authentication Entry Point:** Implements a custom entry point to handle unauthorized access attempts.
*   **Token Generation:** Generates tokens based on user roles and permissions.

## Technologies Used

*   **Spring Boot:**  The core framework for building the application.
*   **Spring Security:** For authentication, authorization, and security-related features.
*   **Spring Data JPA:**  For database interaction and entity management.
*   **PostgreSQL:** The relational database used for data storage.
*   **Flyway:** For database schema migrations and version control.
*   **JWT (JSON Web Tokens):** For secure token-based authentication.
*   **Lombok:** For reducing boilerplate code (e.g., getters, setters, constructors).
* **NimbusDS** For JWT dependencies.

## Project Structure

The project is organized into the following key packages:

*   **`br.com.washington.springsecurity.config`:** Contains Spring Security configuration classes, including:
    *   `SecurityConfig`: Main security configuration for JWT, request authorization, and disabling HTTP Basic and CSRF.
    *   `CustomAuthenticationEntryPoint`: Handles unauthorized access attempts.
*   **`br.com.washington.springsecurity.enums`:** Defines enums:
    * `RoleName`: Defines the roles ADMIN, CUSTOMER, VENDOR.
    * `AdminPermissionName`: Defines the permissions for the admin role.
*   **`br.com.washington.springsecurity.exception`**: Custom exceptions for the project.
*   **`br.com.washington.springsecurity.repositoy`**: Repositorys for data persistence.
*   **`br.com.washington.springsecurity.security`:** Contains classes related to security:
    *   `JwtService`: Generates JWT tokens.
    *   `UserDetailsServiceImpl`: Loads user details from the database.
    * `AuthenticationService`: Authenticates the user and generates token.
* **`br.com.washington.springsecurity.entity`**: Entitys for the database.
* **`main/resources`**:
    *   `application.yaml`: Configuration for the database, Flyway, JWT keys, etc.
    * `db/migration`: SQL migration scripts for database schema creation and data initialization.

## Database Schema

The application uses the following database tables:

*   **`tb_users`:** Stores user information (username, password, enabled, account status flags).
*   **`tb_roles`:** Stores roles (ADMIN, CUSTOMER, VENDOR).
*   **`tb_users_roles`:** A join table mapping users to roles (many-to-many).
*   **`tb_permissions`:** Stores permissions (e.g., READ\_USER, WRITE\_USER).
*   **`tb_roles_permissions`:** A join table mapping roles to permissions (many-to-many).

## Database Migrations (Flyway)

The `db/migration` directory contains the following SQL migration scripts:

*   **`V1__CREATE_TABLE_USERS.sql`:** Creates the `tb_users` table.
*   **`V2__CREATE_TABLE_ROLES_AND_PERMISSIONS.sql`:** Creates the `tb_roles`, `tb_users_roles`, `tb_permissions`, and `tb_roles_permissions` tables.
*   **`V3__INSERT_ROLES.sql`:** Inserts the ADMIN, CUSTOMER, and VENDOR roles into `tb_roles`.
*   **`V4__INSERT_ADMIN_PERMISSIONS.sql`:** Inserts the admin permissions into `tb_permissions`.
*   **`V5__INSERT_ADMIN_ROLE_AND_RELATION.sql`:** Assigns the admin permissions to the ADMIN role in `tb_roles_permissions`.

## Running the Application

1.  **Prerequisites:**
    *   Java Development Kit (JDK) 17 or higher
    *   PostgreSQL database
    *   Maven
2.  **Configuration:**
    *   Update the `application.yaml` file with your PostgreSQL database credentials (URL, username, password).
    * Ensure that the JWT keys exists in classpath.
3.  **Build:**
    ```bash
    mvn clean install
    ```
4.  **Run:**
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

*   `/authenticate/signup`: Endpoint for user signup.
*   `/authenticate/login`: Endpoint for user login.
* `/authenticate/**`: All endpoits under `/authenticate` are public.
* Other endpoints requires authentication.

## Security Details

*   **JWT:** The application uses JWT for authentication. Upon successful login, the user receives a JWT, which must be included in the `Authorization` header of subsequent requests.
*   **Password Hashing:** User passwords are encrypted using BCrypt.
* **Authentication:** The username and password will be used to generate a token.

## Future Enhancements

*   Add more endpoints for other functionalities.
* Add more role and permission.
*   Implement refresh tokens for improved security.
*   Add API documentation (e.g., using OpenAPI/Swagger).
*   Implement a robust error handling mechanism.
* Add DTO classes.

## License

[Your License Here (e.g., MIT License)]