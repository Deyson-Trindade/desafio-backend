package br.com.deyson.vuttr.exceptions;

public class ToolNotFoundException extends RuntimeException {

    public ToolNotFoundException(final String message) {
        super(message);
    }
}
