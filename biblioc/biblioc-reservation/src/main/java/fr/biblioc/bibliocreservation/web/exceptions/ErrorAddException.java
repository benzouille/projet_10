package fr.biblioc.bibliocreservation.web.exceptions;

    /*
Si on en arrive à cette exception, c'est qu'il y a eu une erreur interne
Si la requête était mal formée, elle aurait déclenché 400 Bad Request automatiquement
**/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorAddException extends RuntimeException {
    public ErrorAddException(String message) {super(message);}
}