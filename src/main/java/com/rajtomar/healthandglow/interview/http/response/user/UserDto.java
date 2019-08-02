package com.rajtomar.healthandglow.interview.http.response.user;

import com.rajtomar.healthandglow.interview.http.common.AbstractUserDto;
import com.rajtomar.healthandglow.interview.http.response.AbstractResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * Basic response contract for the user controller.
 *
 * @author Raj Tomar
 */
@Data
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AbstractUserDto implements AbstractResponseDto {

    Long id;

    String phone;

}
