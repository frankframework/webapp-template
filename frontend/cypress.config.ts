// frontend/cypress.config.ts
import { defineConfig } from 'cypress'

export default defineConfig({
  video: true,
  reporter: 'cypress-multi-reporters',
  reporterOptions: { configFile: 'reporter-config.json' },
  e2e: {
    baseUrl: 'http://localhost:4200',
    specPattern: 'cypress/e2e/**/*.cy.{js,jsx,ts,tsx}',
    supportFile: 'cypress/support/e2e.ts',
  },
  env: {
    backendUrl: 'http://localhost:8080',
  }
})
