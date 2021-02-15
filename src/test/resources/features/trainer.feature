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

    #Failure path
    Scenario: Cannot start a round if previous round was lost
      Given I am playing a game
      And I have lost the round
      Then I cannot start a new round

    #Failure path
    Scenario: Cannot start a round while current round is ongoing
      Given I am playing a game
      And the round is ongoing
      Then I cannot start a new round

    #Failure path
    Scenario: Cannot start a round if there is no game ongoing
      Given there is no game ongoing
      When I start a new round
      Then I cannot start a new round


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

    #Failure path
    Scenario: Cannot guess the same word
      Given that the word has already been guessed before
      When I try to guess that same word
      Then I will receive feedback that said word has already been guessed
      And my guess will not go through

    #Failure path
    Scenario: Cannot guess word if round not started
      Given I am playing a game
      And the round was won
      Then I cannot guess the word

    #Success path
    Scenario Outline: The guessed word was correct
      Given the word to guess this round was the word I had guessed
      And my score is "<current score>"
      When I guess the word in "<attempts>" attempts
      Then my score is "<new score>"

      Examples:
      | current score | attempts | new score |
      | 0             | 1        | 25        |
      | 5             | 1        | 30        |
      | 0             | 2        | 20        |
      | 5             | 2        | 25        |
      | 0             | 3        | 15        |
      | 5             | 3        | 20        |
      | 0             | 4        | 10        |
      | 5             | 4        | 15        |
      | 0             | 5        | 5         |
      | 5             | 5        | 10        |

    #Failure path
    Scenario: Player loses after 5 incorrect guesses
      Given I am playing a game
      And I have already made four wrong guesses
      When I guess wrong again
      Then the game is over
      And I have lost the game