# API Specification

## Endpoints Overview

| Method | Endpoint | Description | Status Code |
|---|---|---|---|
| `POST` | `/api/books` | Add a new book | `201 Created` |
| `GET` | `/api/books` | Get all books | `200 OK` |
| `GET` | `/api/books/{id}` | Get a book by ID | `200 OK` / `404 Not Found` |
| `PUT` | `/api/books/{id}` | Update a book by ID (Full update) | `200 OK` / `400 Bad Request` / `404 Not Found` |
| `PATCH` | `/api/books/{id}` | Partial update of a book | `200 OK` / `404 Not Found` |
| `DELETE` | `/api/books/{id}` | Delete a book by ID | `204 No Content` / `404 Not Found` |

---

## Sample Payloads

### 1. Create Book (`POST /api/books`)
**Request Body**:
```json
{
  "title": "Clean Code: A Handbook of Agile Software Craftsmanship",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "publishedDate": "2008-08-01"
}
```

**Response (`201 Created`)**:
```json
{
  "id": 1,
  "title": "Clean Code: A Handbook of Agile Software Craftsmanship",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "publishedDate": "2008-08-01",
  "createdAt": "2026-08-05T17:30:00",
  "updatedAt": "2026-08-05T17:30:00"
}
```
