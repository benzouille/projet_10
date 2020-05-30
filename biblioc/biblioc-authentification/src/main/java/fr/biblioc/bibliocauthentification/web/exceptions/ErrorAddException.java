package fr.biblioc.bibliocauthentification.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorAddException extends RuntimeException {
    public ErrorAddException(String message) {super(message);}
}
