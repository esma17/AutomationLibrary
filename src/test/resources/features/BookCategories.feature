 @regression
Feature: Filtering the books by their categories
  As a Student, I can filter the books by their categories,
  then I can see all the books from the same category only.

  @bookCat
  Scenario: Student should be able to see all categories from Book Categories dropdown
    Given User logs in as Student
    Then Student should see below info in book categories dropdown
      | ALL                     |
      | Action and Adventure    |
      | Anthology               |
      | Classic                 |
      | Comic and Graphic Novel |
      | Crime and Detective     |
      | Drama                   |
      | Fable                   |
      | Fairy Tale              |
      | Fan-Fiction             |
      | Fantasy                 |
      | Historical Fiction      |
      | Horror                  |
      | Science Fiction         |
      | Biography/Autobiography |
      | Humor                   |
      | Romance                 |
      | Short Story             |
      | Essay                   |
      | Memoir                  |
      | Poetry                  |

  @bookCat
  Scenario Outline: Student selects particular category from the dropdown
  and should see all the books from that category
    Given User logs in as Student
    And Student selects "<category>" from category dropdown
    Then Student should see all the books from that "<category>"

    Examples: selecting the particular category
      | category                |
      | Action and Adventure    |
      | Anthology               |
      | Classic                 |
      | Comic and Graphic Novel |
      | Crime and Detective     |
      | Drama                   |
      | Fable                   |
      | Fairy Tale              |
      | Fan-Fiction             |
      | Fantasy                 |
      | Historical Fiction      |
      | Horror                  |
      | Science Fiction         |
      | Biography/Autobiography |
      | Humor                   |
      | Romance                 |
      | Short Story             |
      | Essay                   |
      | Memoir                  |
      | Poetry                  |


  @hello
  Scenario Outline: Add book with valid info
    Given librarian on books page and clicks to addBook Button
    When librarian enter valid info about the book "<BookName>","<ISBN>","<YEAR>","<Author>","<BookCategory>","<Description>"
    Then the book is displayed on the table

    Examples:
      | BookName                                  | ISBN   | YEAR | Author           | BookCategory       | Description                                                                                                                                                                                       |
      | My Sweet Orange tree                      | 111222 | 1968 | Jose Mauro       | Classic            | Truly immortal children's characters are those who transport but also transcend. Not only do they chime with young readers around the world, they continue to strike a chord with us in adulthood |
      | Uzaklarin Sarkisi                         | 3454   | 2017 | Kaan Murat Yanik | Historical Fiction | Parrots talk, we know that. But do they report? Or do they have talents from the past?                                                                                                            |
      | Ben Orada Degildim Ustelik Sizde Yoktunuz | 333444 | 2019 | burak aksak      | Humor              | Here you will feel like you are watching Istanbul from the roof of a building, you will want to sit with different characters and have a long conversation while reading                          |


