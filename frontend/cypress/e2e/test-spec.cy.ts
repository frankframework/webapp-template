describe('Simple e2e test for template repository', () => {
  it('should display Hello World from the backend', () => {
    cy.log('baseUrl = ' + Cypress.config('baseUrl'))
    cy.log('backendUrl = ' + Cypress.env('backendUrl'))
    cy.visit('')
    cy.get('[data-test="backend-response"]').should('have.text', 'Hello World!')
  })
})
