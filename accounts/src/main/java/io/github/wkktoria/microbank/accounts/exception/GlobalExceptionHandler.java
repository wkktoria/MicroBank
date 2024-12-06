package io.github.wkktoria.microbank.accounts.exception;

import io.github.wkktoria.microbank.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest) {
        final ErrorResponseDto errorResponseDto =
                createErrorResponseDto(webRequest, HttpStatus.INTERNAL_SERVER_ERROR, exception);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
                                                                                 WebRequest webRequest) {
        final ErrorResponseDto errorResponseDto =
                createErrorResponseDto(webRequest, HttpStatus.BAD_REQUEST, exception);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest webRequest) {
        final ErrorResponseDto errorResponseDto =
                createErrorResponseDto(webRequest, HttpStatus.NOT_FOUND, exception);

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    private ErrorResponseDto createErrorResponseDto(WebRequest webRequest, final HttpStatus httpStatus, Exception exception) {
        return new ErrorResponseDto(
                webRequest.getDescription(false),
                httpStatus,
                exception.getMessage(),
                LocalDateTime.now()
        );
    }
}
