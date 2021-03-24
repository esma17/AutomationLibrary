@regression
Feature: Default values of the table

  @table
  Scenario: Table columns names

    Given the user on the homepage
    Then the user should see the following column names:
      | Actions     |
      | ISBN        |
      | Name        |
      | Author      |
      | Category    |
      | Year        |
      | Borrowed By |

  @record
  Scenario: the show records values
    Given the user on the homepage
    Then the show records dropdown default value should be 10
    And the dropdown should have following options:

      | 5     |
      | 10    |
      | 15    |
      | 50    |
      | 100   |
      | 200   |
      | 500   |

