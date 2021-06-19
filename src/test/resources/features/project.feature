Feature: Requests for projects endpoint
  description 2

  @CreateProject
  Scenario: Get a Project
    Given I build "GET" request
    When I execute "projects/{projectId}" request
    Then the response status code should be "OK"