package nl.hu.cisq1.lingo.trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;

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
}
