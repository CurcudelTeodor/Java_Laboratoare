package org.example;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(Exception ex) {
        super("Comanda invalida!", ex);
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
