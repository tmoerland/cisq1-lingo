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

  Scenario Outline: Start a new round
    Given I am playing a game
    And the previous round was won
    And the last word had "<previous length>" letters
    When I start a new round
    Then the word to guess has "<next length>" letters
    And I should see the first letter

    Examples:
      | previous length | next length |
      | 5               | 6           |
      | 6               | 7           |
      | 7               | 5           |

  Scenario Outline: Check guessed word
    Given the word to guess this round is "<word>"
    When I take my "<guess>"
    Then I will get "<feedback>" per character

    Examples:
      | word | guess  | feedback                                      |
      |BAARD | BERGEN | INVALID, INVALID, INVALID, INVALID, INVALID   |
      |BAARD | BONJE  | CORRECT, ABSENT, ABSENT, ABSENT, ABSENT       |
      |BAARD | BARST  | CORRECT, CORRECT, PRESENT, ABSENT, ABSENT     |
      |BAARD | DRAAD  | ABSENT, PRESENT, CORRECT, PRESENT, CORRECT    |
      |BAARD | BAARD  | CORRECT, CORRECT, CORRECT, CORRECT, CORRECT   |