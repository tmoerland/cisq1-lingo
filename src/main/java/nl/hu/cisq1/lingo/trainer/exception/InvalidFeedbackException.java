package nl.hu.cisq1.lingo.trainer.exception;

public class InvalidFeedbackException extends RuntimeException{
    public InvalidFeedbackException(){
        super("Length of Marks-list did not correspond with word length!");
    }
}
