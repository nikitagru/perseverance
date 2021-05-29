package exceptions;

public class NoStartPositionException extends Exception{
    public NoStartPositionException() {
        super("There is no start position for robot in map");
    }
}
