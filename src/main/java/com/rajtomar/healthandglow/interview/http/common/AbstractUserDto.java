package com.rajtomar.healthandglow.interview.http.common;

import com.rajtomar.healthandglow.interview.validation.constraint.FieldMatch;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Basic contract fields required for request/response related to user.
 *
 * @author Raj Tomar
 */
@Data
@Slf4j
@FieldMatch(first = "password", second = "confirmPassword", message = "{notmatch.password}")
public abstract class AbstractUserDto {

    @Valid
    @NotEmpty(message = "{nonempty.name}")
    @Size(min = 1, max = 100, message = "name {length}")
    String name;

    @Valid
    @NotEmpty(message = "email {nonempty}")
    @Size(min = 1, max = 100)
    String email;

    @Valid
    @NotEmpty(message = "password {nonempty.password}")
    @Size(min = 8, max = 16, message = "password {length}")
    String password;

    @Valid
    @NotEmpty(message = "confirmPassword {nonempty.password}")
    @Size(min = 8, max = 16, message = "confirmPassword {length}")
    String confirmPassword;

}
