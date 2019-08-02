package com.rajtomar.healthandglow.interview.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

/**
 * A contract structure which tell how response of the every request should be sent to the end user.
 *
 * @author Raj Tomar
 */
@Data
@JsonInclude(NON_NULL)
@FieldDefaults(level = PRIVATE)
public class GenericResponse implements AbstractResponseDto {

    int statusCode;
    HttpStatus status;
    String message;
    AbstractResponseDto data;
    Collection<? extends AbstractResponseDto> dataList;
    List<String> errors;

}
