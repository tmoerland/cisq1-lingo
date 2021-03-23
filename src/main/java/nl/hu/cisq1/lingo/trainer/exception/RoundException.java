package nl.hu.cisq1.lingo.trainer.exception;

public class RoundException extends RuntimeException{
    public RoundException(){
        super("Round is already over!");
    }
}
