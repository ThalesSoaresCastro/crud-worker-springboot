# CRUD API AND WORKER

### Requirements

* [Docker](https://www.docker.com/)
* [Docker-compose](https://docs.docker.com/compose/)


## Project Execution

The project is in a container environment and can be run by running docker-compose.

The postgresql will be instantiated, which is used by the CRUD api, a mongodb that is used by the WORKER and also a RABBITMQ container used for the communication of the two environments.

* Both CRUD and WORKER were created using SpringBoot.

### Execution

* Start the project: 
```
docker-compose up -d
```
* Finalize project: 
```
docker-compose down
```

## CRUD CLIENT

Client CRUD which, when creating a new user, sends information from the created user to a queue and, after creating the events, receives information from the events created for the user created by another queue.

* The documentation can be accessed by http://localhost:7879/docs

## WORKER APP

WORKER that consumes messages from the queue, creates user events and sends creation information to another queue that will be consumed by CRUD.

* The documentation can be accessed by http://localhost:7880/docs

* The search routes( /allbydateinterval and /allbydate ) by date ( date range and by specific date ) receive as a parameter the data in the format YYY-MM-dd.

* Routes (/allorderbydate and /allorderbydate/{clientId}) return events sorted by date

