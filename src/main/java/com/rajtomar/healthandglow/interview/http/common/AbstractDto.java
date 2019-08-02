package com.rajtomar.healthandglow.interview.http.common;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * Basic contract fields required for required request/response.
 *
 * @author Raj Tomar
 */
@Data
@FieldDefaults(level = PRIVATE)
public abstract class AbstractDto {

    Long id;

}
