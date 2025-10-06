# WebApp Template

This project is a setup for a web application, specifically for a SpringBoot application with an Angular frontend. 

## Build from source

Building the project requires Java, Maven, NodeJS, PNPM and Docker installed on your system.

To build the project from source, run this command:

```bash
mvn clean install
```

## Development

To run the application in development mode, you can use the following commands:

1. Start the backend server:
   ```bash
   mvn spring-boot:run
   ```
2. Start the frontend server:
   ```bash
   cd src/main/frontend
   pnpm install
   pnpm start
   ```
   
### E2e Testing

For e2e testing, you can use Cypress. To run the e2e tests, follow these steps:

1. Navigate to the frontend directory:
   ```bash
   cd src/main/frontend
   ```
2. Run the e2e tests:
   ```bash
   pnpm e2e
   ```
   
# Naming convention
## Packages
We use a context based java package structure. That means a package for each context. This is spelled singular, eg: 'user', not 'users'. Some goes for the
classes in that package. We try to group classes that belong to the same context. For instance: the keycloak admin service is placed in the user package since
it's used there. This can always change if the project grows and it's used in other places as well.\
This can of course apply to sub-packages as well. If the 'pod' package grows too big, we can create sub-packages like 'management', 'deployment', etc.

In the frontend this can be applied as well.

## DTO / data transfer objects
We try to use DTO's as much as possible. This means that we don't expose our entities or internal classes directly. This implies that we create classes
(or records whenever possible). There are two types of DTO's:
* a 'command' - to create or update data - typically these are named like `CreateUserCommand`
* a 'response' - to return data to the client - typically these are named like `UserDto`