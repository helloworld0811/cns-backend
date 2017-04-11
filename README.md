Backend for Connecsi
=============================

This project serves the purpose of providing the backend REST services for the connecsi
###  Features
There arethe following features provided in the current revision:

* **Meta Model**: The basic meta model for the application
* **Persistence**:The DB persistence is working for MySQL DB, the schema is generated on the fly if not existing
* **Springboot Service Deployment** The service is deployable as springboot by simply build and run ./gradle bootRun
* **REST calls** The basic Youtube data fetch and persisting is functional by REST calls

### Prerequisites
* Gradle 2.14 or greater
* Java 1.8 or greater
* MySql 5.6 or greater (Optional, only need if you want to connect to locally)
* Docker 1.11.X or greater (Optional, only need if deploying with Docker)

### Local Setup for Development

1. First and foremost, clone the project to your development machine and go into the project directory.
2. Assuming you have the prerequisites, run `gradle build` to install dependencies the application will use.
3. Set up the Database as mentioned in the section below.
4. To start up the server, run `gradle bootRun`. The application will be listening on port 8080.

### Database Usage
To set up the DB, install MySQL server and set up user access as provided in application.properties. Then create an empty schema named as "development".

### Dockerization
Make sure Docker is installed!
To take advantage of running this service in a Docker container, we must first build the Docker image accordingly. Follow the steps below:

```bash
$ gradle # this will run clean, build, test tasks
$ gradle docker # docker task will move all necessary artifacts to build/docker directory
$ docker build -t <tag for the image> build/docker # build the docker image and make it available with a tag
$ docker run -p 8080:8080 <tag for the image> # run said docker image with tag we just created
```

