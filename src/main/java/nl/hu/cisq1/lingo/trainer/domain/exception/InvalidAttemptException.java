package nl.hu.cisq1.lingo.trainer.domain.exception;

public class InvalidAttemptException extends RuntimeException{
    public InvalidAttemptException(){
        super("Length of attempt did not correspond with the word to guess' length!");
    }
}
