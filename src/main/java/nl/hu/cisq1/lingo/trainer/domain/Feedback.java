package nl.hu.cisq1.lingo.trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Feedback {
    private final List<Mark> marks;
    private String attempt; //TODO: update domainmodel

    public Feedback(List<Mark> marks, String attempt) throws InvalidFeedbackException {
        this.marks = marks;
        this.attempt = attempt;
        if (this.marks.size() != this.attempt.length()){
            throw new InvalidFeedbackException();
        }
    }

    public boolean isWordGuessed(){
        return this.marks.stream()
                .allMatch(mark -> mark.equals(Mark.CORRECT));
    }

    public boolean isWordValid(){
        return this.marks.stream()
                .noneMatch(mark -> mark.equals(Mark.INVALID));
    }

    public String giveHint(String previousHint){
        StringBuilder hint = new StringBuilder();

        for(int i = 0; i < this.attempt.length(); i++){
            if(this.marks.get(i).equals(Mark.CORRECT)) {
                hint.append(this.attempt.charAt(i));
            } else if(previousHint.charAt(i) != '.'){
                hint.append(previousHint.charAt(i));
            } else {
                hint.append(".");
            }
        }
        return hint.toString();
    }
}
