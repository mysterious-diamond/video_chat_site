package com.aarons.videochat.error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnauthorizedException extends RuntimeException {
    private final HttpStatus statusCode;

    public UnauthorizedException(String message) {
        super(message);
        this.statusCode = HttpStatus.UNAUTHORIZED;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }
}
