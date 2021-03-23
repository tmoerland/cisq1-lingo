package nl.hu.cisq1.lingo.trainer.helper;

import nl.hu.cisq1.lingo.trainer.domain.Mark;
import nl.hu.cisq1.lingo.trainer.exception.InvalidAttemptException;

import java.util.ArrayList;
import java.util.List;

public class AttemptToMarks {
    public static List<Mark> giveMarks(String wordToGuess, String attempt){
        List<Mark> marks = new ArrayList<>();

        if (wordToGuess.length() != attempt.length()){
            throw new InvalidAttemptException();
        }

        for(int i = 0; i < wordToGuess.length(); i++){
            if(wordToGuess.charAt(i) == attempt.charAt(i)) {
                marks.add(Mark.CORRECT);
            }
            else if (wordToGuess.indexOf(attempt.charAt(i)) != -1) {  //if the attempt char does not occur, -1 is returned
                marks.add(Mark.PRESENT);                         //so everything but -1 means the char is present
            } else {
                marks.add(Mark.ABSENT);
            }
        }
        return marks;
    }
}
