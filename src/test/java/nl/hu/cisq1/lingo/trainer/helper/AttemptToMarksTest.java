package nl.hu.cisq1.lingo.trainer.helper;

import nl.hu.cisq1.lingo.trainer.domain.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AttemptToMarksTest {
    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Give correct feedback based on input, using AttemptToMarks")
    void giveHint(String attempt, String expectedHint, List<Mark> marks) {
        Feedback feedback = new Feedback(marks, attempt);
        assertEquals(expectedHint, feedback.giveHint(".....")); //Starting with an "empty" first hint
    }

    static Stream<Arguments> provideHintExamples(){
        return Stream.of(
                Arguments.of("apple", "apple", AttemptToMarks.giveMarks("apple", "apple")),    //wordToGuess: apple
                Arguments.of("apple", "ap.l.", AttemptToMarks.giveMarks("apple", "aptly")),    //wordToGuess: aptly
                Arguments.of("apple", "a...e", AttemptToMarks.giveMarks("apple", "alive")),    //wordToGuess: alive
                Arguments.of("apple", "a....", AttemptToMarks.giveMarks("apple", "arson"))     //wordToGuess: arson
        );
    }
}