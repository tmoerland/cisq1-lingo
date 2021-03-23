package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.exception.InvalidAttemptException;
import nl.hu.cisq1.lingo.trainer.exception.RoundException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Round extends LingoRules{

    private final String wordToGuess;
    private String previousHint;
    private LingoStatus status = LingoStatus.PLAYING;
    List<Feedback> feedbackList = new ArrayList<>();

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.previousHint = this.giveFirstHint(wordToGuess);
    }

    public Feedback attempt(String attempt) {
        this.checkRoundValidity(attempt);

        Feedback feedback = new Feedback(Feedback.giveMarks(wordToGuess, attempt), attempt);
        feedbackList.add(feedback);

        if (attempt.equals(wordToGuess)) {
            status = LingoStatus.ROUND_OVER;
        }
        if (feedbackList.size() == 5 && status != LingoStatus.ROUND_OVER){
            status = LingoStatus.PLAYER_LOST;
        }

        this.previousHint = feedback.giveHint(this.previousHint);
        return feedback;
    }

    public int checkNumberOfAttempts(){
        return feedbackList.size();
    }

    private String giveFirstHint(String wordToGuess){
        return wordToGuess.charAt(0) + ".".repeat(wordToGuess.length());
    }

    private void checkRoundValidity(String attempt){    //checks if the round isn't already over
        if(!this.getStatus().equals(LingoStatus.PLAYING)){
            throw new RoundException(); //players shouldn't get here in the first place!
        }
        if (attempt.length() != wordToGuess.length()){  //checks if the attempt has the same nr. of chars as the word to guess
            throw new InvalidAttemptException();
        }
    }
}
