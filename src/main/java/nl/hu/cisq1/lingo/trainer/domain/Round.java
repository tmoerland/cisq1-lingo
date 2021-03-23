package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.exception.RoundException;
import nl.hu.cisq1.lingo.trainer.helper.AttemptToMarks;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Round {
    private final Word wordToGuess;
    private int attempts;

    public Round(Word wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.attempts = 0;
    }
}
