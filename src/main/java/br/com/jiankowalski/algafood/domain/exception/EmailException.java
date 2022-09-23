package br.com.jiankowalski.algafood.domain.exception;

public class EmailException extends RuntimeException {
    public EmailException(Exception e) {
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
