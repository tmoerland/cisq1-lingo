package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackTest {
    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed(){
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "woord");
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if one or more letters are incorrect")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT), "woord");
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Exception is thrown if length of marks-list doesn't correspond with the word length")
    void invalidMarkLength() {
        assertThrows(InvalidFeedbackException.class, () -> new Feedback(List.of(Mark.CORRECT), "woord"));
    }
}
