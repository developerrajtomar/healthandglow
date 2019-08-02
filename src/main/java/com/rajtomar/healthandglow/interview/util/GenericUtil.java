package com.rajtomar.healthandglow.interview.util;

import com.rajtomar.healthandglow.interview.http.response.AbstractResponseDto;
import com.rajtomar.healthandglow.interview.http.response.GenericResponse;
import com.rajtomar.healthandglow.interview.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.rajtomar.healthandglow.interview.constants.GenericMessage.NO_CONTENT_EXCEPTION_MESSAGE;
import static java.util.stream.Collectors.toList;

/**
 * Utility class which contains some ready to use methods to performs some generic and common
 * operation throughout application.
 *
 * @author Raj Tomar
 */
public class GenericUtil {

    public static String getRequestType(String requestUrl) {
        if (ObjectUtils.isEmpty(requestUrl)) {
            throw new IllegalArgumentException("`requestUrl` can not be empty!");
        }
        return requestUrl.substring(1);
    }

    public static String noContentForTypeAndIdMessage(String requestUrl, Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new IllegalArgumentException("`id` can not be empty!");
        }
        return String.format(NO_CONTENT_EXCEPTION_MESSAGE, getRequestType(requestUrl), id);
    }

    public static String formatMessage(String message, String... id) {
        if (ObjectUtils.isEmpty(message) || ObjectUtils.isEmpty(id)) {
            throw new IllegalArgumentException("message or id can not be empty, actual :: " +
                                               "message: " + message + " and id: " + id);
        }
        return String.format(message, id);
    }

    public static double getSellingPrice(Product product) {
        return (product.getMrp() - product.getDiscount());
    }

    /**
     * Generate Response to every request. There are many overloaded methods available for this
     * method for ready to use in the application. All methods at the end fall backs to this
     * method.
     *
     * @param httpStatus {@link HttpStatus httpStatus} as the response.
     * @param message    String message for the response.
     * @param dto        Data need to send with the response.
     * @param dtos       {@link Collection collection} of data which need to send.
     * @param errors     {@link Map<String, Set<String> errors} is the map of errors need to send as
     *                   a response.
     * @return {@link GenericResponse genericResponse} object which will return to the user as
     * response to his request.
     */
    public static GenericResponse generateResponse(final HttpStatus httpStatus,
                                                   final String message,
                                                   final AbstractResponseDto dto,
                                                   final Collection<? extends AbstractResponseDto> dtos,
                                                   final Map<String, Set<String>> errors) {
        GenericResponse response = new GenericResponse();
        response.setStatus(httpStatus);
        response.setStatusCode(httpStatus.value());
        response.setMessage(message);
        generateResponse(response, dto, dtos);
        if (errors != null) {
            response.setErrors(errors.values()
                                     .parallelStream()
                                     .flatMap(Collection::stream)
                                     .collect(toList()));
        }
        return response;
    }

    public static GenericResponse generateResponse(final HttpStatus httpStatus,
                                                   final String message,
                                                   final AbstractResponseDto dto,
                                                   final Map<String, Set<String>> errors) {
        return generateResponse(httpStatus, message, dto, null, errors);
    }

    public static GenericResponse generateResponse(final HttpStatus httpStatus,
                                                   final String message,
                                                   final Collection<? extends AbstractResponseDto> dtos,
                                                   final Map<String, Set<String>> errors) {
        return generateResponse(httpStatus, message, null, dtos, errors);
    }

    public static GenericResponse generateResponse(final GenericResponse genericResponse,
                                                   final AbstractResponseDto abstractResponseDto,
                                                   final Collection<? extends AbstractResponseDto> abstractResponseDtos) {
        if (!ObjectUtils.isEmpty(abstractResponseDto)) {
            genericResponse.setData(abstractResponseDto);
        }
        if (!ObjectUtils.isEmpty(abstractResponseDtos)) {
            genericResponse.setDataList(abstractResponseDtos);
        }
        return genericResponse;
    }

    public static GenericResponse generateResponse(final HttpStatus httpStatus,
                                                   final String message) {
        return generateResponse(httpStatus, message, null, null, null);
    }

    public static GenericResponse generateResponse(final HttpStatus httpStatus,
                                                   final String message,
                                                   final Map<String, Set<String>> errors) {
        return generateResponse(httpStatus, message, null, null, errors);
    }

    public static GenericResponse generateResponse(final HttpStatus httpStatus,
                                                   final String message,
                                                   final AbstractResponseDto dto) {
        return generateResponse(httpStatus, message, dto, null);
    }

    public static GenericResponse generateResponse(final HttpStatus httpStatus,
                                                   final String message,
                                                   final Collection<? extends AbstractResponseDto> dtos) {
        return generateResponse(httpStatus, message, dtos, null);
    }

}
