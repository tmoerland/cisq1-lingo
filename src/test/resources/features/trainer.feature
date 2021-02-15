Feature: Training for Lingo
  As a Lingo player,
  I want practice guessing 5, 6, 7 letter words,
  in order to improve my Lingo skills

  Scenario: Start a new game
    Given I am not already playing a game
    When I start a new game
    Then the word to guess has "5" letters
    And I should see the first letter
    And my score is "0"

