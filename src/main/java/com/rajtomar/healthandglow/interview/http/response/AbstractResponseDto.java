package com.rajtomar.healthandglow.interview.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * A marker interface for all the response contract.
 *
 * @author Raj Tomar
 */
@JsonInclude(NON_NULL)
public interface AbstractResponseDto {
}
