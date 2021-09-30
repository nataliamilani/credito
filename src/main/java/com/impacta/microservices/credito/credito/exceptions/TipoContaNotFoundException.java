package com.impacta.microservices.credito.credito.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TipoContaNotFoundException extends RuntimeException {

    public TipoContaNotFoundException(String msg) {
        super(msg);
    }
}
