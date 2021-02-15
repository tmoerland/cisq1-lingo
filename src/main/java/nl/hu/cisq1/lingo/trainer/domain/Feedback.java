package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Feedback {
    private final List<Mark> marks;

    public Feedback(List<Mark> marks) {
        this.marks = marks;
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
