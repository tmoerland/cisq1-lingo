package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.exception.InvalidAttemptException;
import nl.hu.cisq1.lingo.trainer.exception.RoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoundTest {

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void attemptCorrectly() {
        Round round = new Round("apple");
        round.attempt("apple");
        assertSame(round.getStatus(), LingoStatus.ROUND_OVER);
    }

    @Test
    @DisplayName("Exception is thrown when attempt length does not equal word to guess length")
    void throwAttemptLengthException() {
        Round round = new Round("apple");
        assertThrows(InvalidAttemptException.class, () -> round.attempt("pear"));
    }

    @Test
    @DisplayName("Exception is thrown when attempt is made after the round is over")
    void throwRoundException() {
        Round round = new Round("apple");
        round.attempt("apple");
        assertThrows(RoundException.class, () -> round.attempt("apple"));
    }

    @Test
    @DisplayName("Hint is updated after incorrect attempt")
    void updateHintAfterAttempt(){
        Round round = new Round("apple");
        round.attempt("aptly");
        assertEquals("ap.l.", round.getPreviousHint());
    }

    @Test
    @DisplayName("Round status is PLAYER_LOST after several attempts (default = 5)")
    void goOverMaxAttempts() {
        Round round = new Round("apple");
        round.attempt("peach");
        round.attempt("peach");
        round.attempt("peach");
        round.attempt("peach");
        round.attempt("peach");

        assertEquals(round.getStatus(), LingoStatus.PLAYER_LOST);
    }
}