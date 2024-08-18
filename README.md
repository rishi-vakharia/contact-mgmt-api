# Contact Management API

## Project Description

The Contact Management API is a simple RESTful web service built with Spring Boot that allows users to manage contact information. This API supports operations such as creating, retrieving, updating, and deleting contacts.

## Features

- **Create a New Contact**: Add new contact details.
- **Retrieve Contacts**: Get a list of all contacts or fetch a specific contact by its ID.
- **Update a Contact**: Modify the details of an existing contact.
- **Delete a Contact**: Remove a contact from the system.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following software installed:

- **Java 17+**
- **Maven 3.6+**
- **Spring Boot 3.1.0**
- **MySQL 8.0+**

### Database Setup

1. **Install MySQL**:
   If you don't have MySQL installed, you can download and install it from the [official website](https://dev.mysql.com/downloads/mysql/).

2. **Create the Database**:
   Log in to your MySQL server and create a database named `contacts`.

   ```sql
   CREATE DATABASE contacts;
   ```

3. **Update Database Credentials**:
   The application is configured to use the following MySQL credentials:
   - **Database Name:** `contacts`
   - **Username:** `rishi`
   - **Password:** `rishi`

   If your MySQL setup uses different credentials, update the following properties in the `application.properties` file located in the `src/main/resources` directory:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/contacts
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   ```

### Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/rishi-vakharia/contact-management-api.git
   ```

2. **Navigate to the Project Directory**:

   ```bash
   cd contact-management-api
   ```

3. **Build the Project**:
   Use Maven to build the project and resolve dependencies.

   ```bash
   mvn clean install
   ```

### Running the Application

1. **Start the MySQL Server**:
   Ensure your MySQL server is running.

2. **Start the Spring Boot Application**:

   ```bash
   mvn spring-boot:run
   ```

3. **Access the API**:
   The API will be accessible at `http://localhost:8080/api/contacts`.

## Data Model

### Contact

- **id:** `long` - The unique identifier for a contact.
- **firstName:** `String` - The first name of the contact. (Required)
- **lastName:** `String` - The last name of the contact. (Required)
- **phoneNo:** `String` - The phone number of the contact. Must be 10 digits. (Required)

# Contact Management API Documentation

## Overview

The Contact Management API provides a simple RESTful interface to manage contact information. This API allows you to create, retrieve, update, and delete contacts.

## Base URL

```
http://localhost:8080/api/contacts
```

## Endpoints

### 1. Get All Contacts

- **URL:** `/`
- **Method:** `GET`
- **Description:** Retrieve a list of all contacts.
- **Response:**
  - **Status Code:** `200 OK`
  - **Content Type:** `application/json`
  - **Body:**
    ```json
    [
      {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "phoneNo": "1234567890"
      },
      ...
    ]
    ```
- **Example cURL:**
  ```bash
  curl -X GET "http://localhost:8080/api/contacts" -H "accept: application/json"
  ```

### 2. Get a Contact by ID

- **URL:** `/{id}`
- **Method:** `GET`
- **Description:** Retrieve a contact by its ID.
- **Parameters:**
  - **Path Variable:** `id` (required) - The ID of the contact to retrieve.
- **Response:**
  - **Status Code:** `200 OK`
  - **Content Type:** `application/json`
  - **Body:**
    ```json
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "phoneNo": "1234567890"
    }
    ```
  - **Status Code `404 NOT FOUND`:** If the contact is not found.
- **Example cURL:**
  ```bash
  curl -X GET "http://localhost:8080/api/contacts/1" -H "accept: application/json"
  ```

### 3. Add a New Contact

- **URL:** `/`
- **Method:** `POST`
- **Description:** Add a new contact.
- **Request Body:**
  - **Content Type:** `application/json`
  - **Body:**
    ```json
    {
      "firstName": "John",
      "lastName": "Doe",
      "phoneNo": "1234567890"
    }
    ```
- **Response:**
  - **Status Code:** `201 CREATED`
  - **Content Type:** `application/json`
  - **Body:**
    ```json
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "phoneNo": "1234567890"
    }
    ```
  - **Status Code `400 BAD REQUEST`:** If validation fails.
- **Example cURL:**
  ```bash
  curl -X POST "http://localhost:8080/api/contacts" -H "Content-Type: application/json" -d '{
    "firstName": "John",
    "lastName": "Doe",
    "phoneNo": "1234567890"
  }'
  ```

### 4. Update a Contact

- **URL:** `/{id}`
- **Method:** `PUT`
- **Description:** Update an existing contact by its ID.
- **Parameters:**
  - **Path Variable:** `id` (required) - The ID of the contact to update.
- **Request Body:**
  - **Content Type:** `application/json`
  - **Body:**
    ```json
    {
      "firstName": "Jane",
      "lastName": "Doe",
      "phoneNo": "0987654321"
    }
    ```
- **Response:**
  - **Status Code:** `200 OK`
  - **Content Type:** `application/json`
  - **Body:**
    ```json
    {
      "id": 1,
      "firstName": "Jane",
      "lastName": "Doe",
      "phoneNo": "0987654321"
    }
    ```
  - **Status Code `404 NOT FOUND`:** If the contact is not found.
  - **Status Code `400 BAD REQUEST`:** If validation fails.
- **Example cURL:**
  ```bash
  curl -X PUT "http://localhost:8080/api/contacts/1" -H "Content-Type: application/json" -d '{
    "firstName": "Jane",
    "lastName": "Doe",
    "phoneNo": "0987654321"
  }'
  ```

### 5. Delete a Contact

- **URL:** `/{id}`
- **Method:** `DELETE`
- **Description:** Delete a contact by its ID.
- **Parameters:**
  - **Path Variable:** `id` (required) - The ID of the contact to delete.
- **Response:**
  - **Status Code:** `200 OK`
  - **Content Type:** `text/plain`
  - **Body:** `"Contact deleted successfully!"`
  - **Status Code `404 NOT FOUND`:** If the contact is not found.
- **Example cURL:**
  ```bash
  curl -X DELETE "http://localhost:8080/api/contacts/1"
  ```

### 6. Validation Errors

- **Status Code `400 BAD REQUEST`:** If the input data does not meet the validation criteria.
- **Response:**
  - **Content Type:** `text/plain`
  - **Body:**
    ```text
    First name is mandatory.
    Phone number should be of 10 digits.
    ```

### 7. Resource Not Found

- **Status Code `404 NOT FOUND`:** If the requested resource (contact) is not found.
- **Response:**
  - **Content Type:** `text/plain`
  - **Body:**
    ```text
    Contact not found with id: {id}
    ```

## References

https://www.youtube.com/watch?v=IxYa6cKimZc
