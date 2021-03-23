package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LingoGame {
    private Long id;
    private int score;
    private int hints;
    private int numberOfRounds;
    private Round round;
    private LingoStatus status;

    public LingoGame(Round round, LingoStatus status) {
        this.score = 0;
        this.hints = 0;
        this.numberOfRounds = 0;
        this.round = round;
        this.status = status;
    }

}
