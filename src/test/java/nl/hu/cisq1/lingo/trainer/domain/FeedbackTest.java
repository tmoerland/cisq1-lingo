package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackTest {
    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed(){
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "apple");
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if one or more letters are incorrect")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT), "apple");
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Exception is thrown if length of marks-list doesn't correspond with the word length")
    void invalidMarkLength() {
        assertThrows(InvalidFeedbackException.class, () -> new Feedback(List.of(Mark.CORRECT), "apple"));
    }

    @Test
    @DisplayName("Word is valid if none of the marks are 'INVALID'")
    void wordIsValid() {
        Feedback feedbackValid = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "apple");
        Feedback feedbackInvalid = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.INVALID), "apples");
        assertTrue(feedbackValid.isWordValid());
        assertFalse(feedbackInvalid.isWordValid());
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Give correct feedback based on input")
    void giveHint(String attempt, String expectedHint, List<Mark> marks) {
        Feedback feedback = new Feedback(marks, attempt);
        assertEquals(expectedHint, feedback.giveHint("a....")); //Starting with an "empty" first hint (first letter given)
    }

    static Stream<Arguments> provideHintExamples(){
        return Stream.of(
                Arguments.of("apple", "apple", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)),  //wordToGuess: apple
                Arguments.of("apple", "ap.l.", List.of(Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.ABSENT)),    //wordToGuess: aptly
                Arguments.of("apple", "a...e", List.of(Mark.CORRECT, Mark.PRESENT, Mark.ABSENT, Mark.ABSENT, Mark.CORRECT)),    //wordToGuess: alive
                Arguments.of("apple", "a....", List.of(Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT)),      //wordToGuess: arson
                Arguments.of("apple", "a....", List.of(Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT))        //wordToGuess: thorn
        );
    }
}
