package com.rajtomar.healthandglow.interview.constants;

/**
 * Generic Message placeholder interface for application related messages.
 * 
 * @author Raj Tomar
 */
public interface GenericMessage {
    String UPDATE_RESPONSE_MESSAGE = "%s update request accepted.";
    String GET_RESPONSE_MESSAGE = "Successfully retrieved %s associated with %s id.";
    String LIST_RESPONSE_MESSAGE = "Successfully retrieved all the %s.";
    String CREATED_RESPONSE_MESSAGE = "%s created with %s id.";
    String CONTENT_FOUND_LOG_MESSAGE = "{} found with id: {}";
    String NO_CONTENT_LOG_MESSAGE = "No {} found for id: {}";
    String NO_CONTENT_EXCEPTION_MESSAGE = "No %s Data found for %s id.";
}
