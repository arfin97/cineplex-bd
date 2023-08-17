# cineplex-bd

# Requirement

**Coding Assignment: Movie API and Front-end**

**Project Description:**

In this coding assignment, you will be building a simple Movie API along with a front-end user interface to interact with the API. The purpose of this assignment is to assess your ability to implement a RESTful API, work with a database, follow clean coding principles, and demonstrate best practices in software development. You are not expected to complete the entire project but to showcase your skills and approach.

**Domain:**

You will be working on a movie management system. The main entities are `Movie` and `Genre`. A movie can belong to one or more genres. The basic attributes of a `Movie` include `title`, `release year`, `description`, and `genre(s)`.

**API Requirements:**

1. **Create a Movie:**
    
    - Implement an API endpoint to create a new movie. The API should accept JSON data containing the movie details and associated genre(s).
    
**API Endpoint:**
`POST /api/movies`

**Request Body:**
```json
{
    "title": "Movie Title",
    "releaseYear": 2023,
    "description": "Movie Description",
    "genres": ["Action", "Drama"]
}
```
**Response (201 Created):**

```json
{
    "id": "1",
    "title": "Movie Title",
    "releaseYear": 2023,
    "description": "Movie Description",
    "genres": ["Action", "Drama"]
}
```
2. **Get All Movies:**

- Create an API endpoint to retrieve a list of all movies. Include the associated genre(s) information.

**API Endpoint:**
`GET /api/movies`
**Response (200 OK):**
```json
[
    {
        "id": "1",
        "title": "Movie Title",
        "releaseYear": 2023,
        "description": "Movie Description",
        "genres": ["Action", "Drama"]
    },
    // Other movies...
]

```

**Get Movie Details:**

- Implement an API endpoint to fetch details of a specific movie by its ID. Include genre(s) information.

**API Endpoint:**
`GET /api/movies/{movieId}`
**Response (200 OK):**
```json
{
    "id": "1",
    "title": "Movie Title",
    "releaseYear": 2023,
    "description": "Movie Description",
    "genres": ["Action", "Drama"]
}

```
**Get All Genres:**
`GET /api/genres`
```json
["Action", "Drama", "Comedy", ...]
```

**Frontend Requirements (Optional):**
*Front-end is optional you can just use Postman to communicate with the Back-end.*

- Create a simple front-end interface using Angular 16 (or any other framework you're comfortable with). Focus on usability and user experience.
- Implement pages/views to:
    - Display a list of all movies along with their details.
    - Allow users to view details of a specific movie.
    - Provide a form to add a new movie with associated genre(s).
    - Show a list of all available genres.

**Guidelines:**

- **Use raw Java/Go to build the back-end. We want to see your understanding of the back-end. You can use Maven/Gradle or any other appropriate build tool.
- Follow the Swagger/OpenAPI specification format for documenting your API endpoints and responses.
- Use docker to containerize your back-end API.
- Write proper unit tests for back-end code.
- Pay attention to code organization, modularization, and separation of concerns.
- Implement proper error handling and validation on both the API and front-end.
- Utilize appropriate HTTP methods and status codes for the API endpoints.
- Use a database of your choice (SQLite, MySQL, etc.) to store movie and genre data.
- Follow best practices for naming conventions, code readability, and commenting.
- Apply proper design patterns and development principles like SOLID, DRY, YAGNI etc.
- NO NEED to implement authentication mechanism.
- Include clear instructions on how to run your project locally.
- Focus on demonstrating your coding approach, architectural thinking, and attention to detail.

**Submission:**

Provide the source code and any necessary instructions to run your project. Share your code repository (GitHub, GitLab, etc.)

**Evaluation Criteria:**

You are not expected to complete the entire project but to showcase your skills and approach.
