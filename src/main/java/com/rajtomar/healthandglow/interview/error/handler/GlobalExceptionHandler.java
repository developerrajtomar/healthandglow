package com.rajtomar.healthandglow.interview.error.handler;

import com.rajtomar.healthandglow.interview.error.AccountAlreadyExistsException;
import com.rajtomar.healthandglow.interview.error.DataDoesNotExistsException;
import com.rajtomar.healthandglow.interview.error.InterviewApplicationException;
import com.rajtomar.healthandglow.interview.http.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rajtomar.healthandglow.interview.constants.ErrorMessage.UNKNOWN_ERROR;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.generateResponse;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static org.springframework.http.HttpStatus.*;

/**
 * This class will be responsible to handle all kind of server exceptions thrown from the
 * controller while serving the user request.
 *
 * @author Raj Tomar
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(value = NO_CONTENT)
    @ExceptionHandler({DataDoesNotExistsException.class})
    public GenericResponse handleEntityDoesNotExistsException(
            final DataDoesNotExistsException ex) {
        return generateResponse(NO_CONTENT, ex.getMessage());
    }

    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler({AccountAlreadyExistsException.class})
    public GenericResponse handleUserAlreadyExistsException(
            final AccountAlreadyExistsException ex) {
        return generateResponse(BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(value = SERVICE_UNAVAILABLE)
    @ExceptionHandler({InterviewApplicationException.class})
    public GenericResponse handleInterviewApplicationException(
            final InterviewApplicationException ex) {
        log.error("========== Unknown Exception occurred ==========", ex);
        return generateResponse(SERVICE_UNAVAILABLE, UNKNOWN_ERROR);
    }

    @Override
    @ResponseStatus(BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        Map<String, Set<String>> errors = ex.getBindingResult()
                                            .getFieldErrors()
                                            .parallelStream()
                                            .collect(groupingBy(FieldError::getField,
                                                                mapping(FieldError::getDefaultMessage,
                                                                        Collectors.toSet())));
        return new ResponseEntity<>(generateResponse(BAD_REQUEST, ex.getMessage(), errors),
                                    BAD_REQUEST);
    }

}
