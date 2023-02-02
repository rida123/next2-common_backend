package net.claims.express.next2.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateResultException extends BaseException{

    public DuplicateResultException(String message) {
        super("Duplicate result: ", message);
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
