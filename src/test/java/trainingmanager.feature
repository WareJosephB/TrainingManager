Feature: Training manager features
  As a training manager
  I want to be able to see all CVs
  So that I keep an eye on present and past trainees

  Scenario: Searching all CVs
    Given the website
    When I am logged in as the training manager
    And I search for all CVs
    Then I am shown all the CVs ever