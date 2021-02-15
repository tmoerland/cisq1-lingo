package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.words.domain.Word;

@Getter
public class Round {
    private final Word wordToGuess;
    private int attempts;

    public Round(Word wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.attempts = 0;
    }
}
