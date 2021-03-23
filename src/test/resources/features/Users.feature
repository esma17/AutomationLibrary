@regression
Feature: user features

  @smoke
  Scenario: Editing User
    Given the librarian  is on users page
    When user clicks to edit button randomly from the list
    And user enters valid info and clicks add user
    Then user will be able to see in user page the added user


  @smoke
  Scenario: Adding users with valid info
    Given the librarian  is on users page
    When user clicks to add new user
    And user enters valid info and clicks add user
    Then user will be able to see in user page the added user


  Scenario: Close the add_edit Form
    Given the librarian  is on users page
    When user clicks to add new user
    Then user clicks to close, the form is closed

#  @edit @wip
#  Scenario: Editing User
#    Given the librarian  is on users page
#    When user clicks to edit button for desired "Dr. Edison Hettinge"
#    And user enters valid info and clicks add user
#    Then user will be able to see in user page the added user



