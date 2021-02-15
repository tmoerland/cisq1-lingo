package nl.hu.cisq1.lingo.words.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity(name = "words")
public class Word {
    @Id
    @Column(name = "word")
    private String value;
    private Integer length;

    public Word() {}
    public Word(String word) {
        this.value = word;
        this.length = word.length();
    }
}
