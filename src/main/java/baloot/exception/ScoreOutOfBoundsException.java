package baloot.exception;

public class ScoreOutOfBoundsException extends Exception{
    public ScoreOutOfBoundsException(String message) {
        super("score range is from 1 to 10.");
    }
}
