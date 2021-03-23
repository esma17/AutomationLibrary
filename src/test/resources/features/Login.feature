@regression
Feature: Login LogOut

  @smoke
  Scenario Outline: verify both Students and librarians login

    Given the user login as a "<role>"
    Then the user on  "<page>"
    Examples:
      | role      | page      |
      | student   | books     |
      | librarian | dashboard |

  @hello @smoke
  Scenario Outline: As a user, I should be able to logout from the library app.
    Given the user is on the "<page>"
    When user clicks to logout
    Then user will land on login page and title will be "Login - Library"

    Examples:
      | page      |
      | books     |
      | dashboard |




