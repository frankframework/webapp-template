// frontend/cypress/support/e2e.ts
import './commands'

Cypress.on('window:before:load', (win: any) => {
  const origFetch = win.fetch.bind(win)
  const baseUrl = (Cypress.env('backendUrl') as string | undefined)?.replace(/\/$/, '')

  win.fetch = (input: any, init?: any) => {
    if (baseUrl && typeof input === 'string' && input.startsWith('http://localhost:8080')) {
      input = input.replace('http://localhost:8080', baseUrl)
    }
    return origFetch(input, init)
  }
})
