package main.place.exception;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException(){
        super("senha inválida");
    }
}
