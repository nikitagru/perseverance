package exceptions;

public class IllegalSymbolException extends Exception{
    public IllegalSymbolException(char symbol) {
        super("Illegal symbol encountered -> " + symbol);
    }

}
