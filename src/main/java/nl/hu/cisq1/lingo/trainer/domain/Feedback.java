package nl.hu.cisq1.lingo.trainer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Feedback {
    private final List<Mark> marks;
    private String attempt; //TODO: update domainmodel

    public Feedback(List<Mark> marks, String attempt) {
        this.marks = marks;
        this.attempt = attempt;
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
