package io.github.wkktoria.microbank.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(final String resourceName, final String fieldName, final String fieldValue) {
        super(String.format("Resource %s not found for field %s and value %s", resourceName, fieldName, fieldValue));
    }
}
