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