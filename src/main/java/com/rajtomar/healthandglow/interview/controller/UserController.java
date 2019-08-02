package com.rajtomar.healthandglow.interview.controller;

import com.rajtomar.healthandglow.interview.http.response.GenericResponse;
import com.rajtomar.healthandglow.interview.http.response.user.UserDto;
import com.rajtomar.healthandglow.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.rajtomar.healthandglow.interview.constants.GenericMessage.GET_RESPONSE_MESSAGE;
import static com.rajtomar.healthandglow.interview.constants.SupportedRequestType.USER;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.formatMessage;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.generateResponse;
import static java.lang.String.valueOf;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * User Request controller. This was not an ask, but needed to add data in order to serve other
 * requests.
 *
 * @author Raj Tomar
 */
@Slf4j
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    /**
     * Registers an user with the system.
     *
     * @return response with the user details.
     */
    @PostMapping
    public GenericResponse register(@RequestBody @Validated
                                            com.rajtomar.healthandglow.interview.http.request.user.UserDto userDto) {
        UserDto registerUser = userService.registerUser(userDto);
        return generateResponse(CREATED, formatMessage(GET_RESPONSE_MESSAGE, "user",
                                                       valueOf(registerUser.getId())),
                                registerUser);
    }

    /**
     * Retrieval of the user registered with the provided user.
     *
     * @return response with the user details.
     */
    @GetMapping("/{userId}")
    public GenericResponse find(@PathVariable Long userId) {
        UserDto userDto = userService.retrieveUserDto(userId);
        return generateResponse(OK, formatMessage(GET_RESPONSE_MESSAGE, "user",
                                                  valueOf(userDto.getId())), userDto);
    }

}
