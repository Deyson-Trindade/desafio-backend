# VUTTR API

VUTTR API (very useful tools to remember) is a simple application in which the user is able to Create, Read and Delete a
data, almost a complete CRUD. We have the following routes:

## 1: Route to list all the registered tools.

`GET /tools`
Response:

    [
        {
            id: "14a9bc79-1373-4e7f-9119-5115530a4081"
            title: "Notion",
            link: "https://notion.so",
            description: "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized. ",
            tags: [
                "organization",
                "planning",
                "collaboration",
                "writing",
                "calendar"
            ]
        },
        {
            id: "790a825d-7fc0-4ce2-887a-6591f90d7616",
            title: "json-server",
            link: "https://github.com/typicode/json-server",
            description: "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.",
            tags: [
                "api",
                "json",
                "schema",
                "node",
                "github",
                "rest"
            ]
        },
        {
            id: "388e65c7-cb31-47ff-baab-adf2eb7060d3",
            title: "fastify",
            link: "https://www.fastify.io/",
            description: "Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.",
            tags: [
                "web",
                "framework",
                "node",
                "http2",
                "https",
                "localhost"
            ]
        }
    ]

## 2: Route to list all the registered tools with matching tag parameter.

`GET /tools?tag=node`   (*node* is the parameter passed through the Request)

Response:

    [
        {
            id: 2, // ou qualquer outro identificador
            title: "json-server",
            link: "https://github.com/typicode/json-server",
            description: "Fake REST API based on a json schema. Useful for mocking and creating APIs for front-end devs to consume in coding challenges.",
            tags: [
                "api",
                "json",
                "schema",
                "node",
                "github",
                "rest"
            ]
        },
        {
            id: 3,
            title: "fastify",
            link: "https://www.fastify.io/",
            description: "Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.",
            tags: [
                "web",
                "framework",
                "node",
                "http2",
                "https",
                "localhost"
            ]
        }
    ]

## 3: Route to create a new tool.

The requisition body must contain the information about the tool to be registered, wihtout the id (automatically
generated by the server).The response, in case of success, should be the same object, with the new generate id.

`POST /tools Content-Type: application/json`

    {
        "title": "hotel",
        "link": "https://github.com/typicode/hotel",
        "description": "Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.",
        "tags":["node", "organizing", "webapps", "domain", "developer", "https", "proxy"]
    }

Response:

`Status: 201 Created`

`Content-Type: application/json`

    {
        "title": "hotel",
        "link": "https://github.com/typicode/hotel",
        "description": "Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.",
        "tags":["node", "organizing", "webapps", "domain", "developer", "https", "proxy"],
        "id":5 // ou qualquer outro identificador
    }

## 4: Route to delete a tool.

`DELETE /tools/:id`

Response:

`Status: 204 No Content`

---

## Log

Logging information is very useful, a really good log can save time when debugging the code, so I've used a technic
called AOP (aspect oriented programing) to instantiate log to the whole API only in the class LoggerAspect.

## Testing API

For testing purpose the whole API is documented and the access is through the following link: `https://startaideia-challenge.herokuapp.com/api-docs-ui`
Obs: In case running locally the url is `locallhost:3000/api-docs-ui`


## Walk-through

### 1:  Minimum requirement
* Docker
* docker-compose

### 2: Ways to run the API

#### Option 1:
Run the command `.\buildAndUp.cmd` to build and start the API through Docker.
#### Option 2:
Run the command `docker build -t vuttr .` to build an image from application. Then
run the command `docker-compose up -d` to up the application.

