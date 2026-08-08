# Blog Management System — REST API

I built this project to practice Spring Boot basics — authentication, JPA/Hibernate, Spring Security, JWT, and layered architecture (Controller → Service → Repository → Entity).

It's a simple blog backend where users can register, log in, and create/manage their own posts.

## Tech Stack

Java, Spring Boot, Spring Security, Spring Data JPA, MySQL, JWT, Lombok, Maven

## Features

- User registration & login with JWT-based authentication
- CRUD operations on blog posts
- Only the author of a post can edit or delete it
- Global exception handling — clean JSON error responses instead of raw stack traces

## Project Structure

```
src/main/java/com/blog/blogapi/
├── config/       # Security configuration
├── controller/   # REST controllers
├── dto/          # Request/response DTOs
├── entity/       # JPA entities
├── exception/    # Custom exceptions + global handler
├── repository/   # Spring Data JPA repositories
├── security/     # JWT utilities & filter
└── service/      # Business logic
```

## API Endpoints

### Auth

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and get a JWT token |

### Posts (require `Authorization: Bearer <token>` header)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/posts` | Create a new post |
| GET | `/api/posts` | Get all posts |
| GET | `/api/posts/{id}` | Get a single post by id |
| PUT | `/api/posts/{id}` | Update a post (author only) |
| DELETE | `/api/posts/{id}` | Delete a post (author only) |

## How to Run

1. Create a MySQL database:
   ```sql
   CREATE DATABASE blogdb;
   ```
2. Update `application.properties` with your MySQL username/password.
3. Run:
   ```bash
   mvn spring-boot:run
   ```
4. Test the endpoints using Postman — register, login, copy the token, and use it in the `Authorization` header for the post endpoints.
