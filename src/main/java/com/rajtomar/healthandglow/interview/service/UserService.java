package com.rajtomar.healthandglow.interview.service;

import com.rajtomar.healthandglow.interview.error.AccountAlreadyExistsException;
import com.rajtomar.healthandglow.interview.error.DataDoesNotExistsException;
import com.rajtomar.healthandglow.interview.http.response.user.UserDto;
import com.rajtomar.healthandglow.interview.model.User;
import com.rajtomar.healthandglow.interview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.rajtomar.healthandglow.interview.constants.GenericMessage.CONTENT_FOUND_LOG_MESSAGE;
import static com.rajtomar.healthandglow.interview.constants.GenericMessage.NO_CONTENT_LOG_MESSAGE;
import static com.rajtomar.healthandglow.interview.constants.SupportedRequestType.PRODUCT;
import static com.rajtomar.healthandglow.interview.constants.SupportedRequestType.USER;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.getRequestType;
import static com.rajtomar.healthandglow.interview.util.GenericUtil.noContentForTypeAndIdMessage;
import static lombok.AccessLevel.PRIVATE;

/**
 * Service class which deals in the operations related to Users.
 *
 * @author Raj Tomar
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserService {

    final UserRepository userRepository;

    public UserDto registerUser(@NotNull com.rajtomar.healthandglow.interview.http.request.user.UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        try {
            User savedUser = userRepository.save(user);
            savedUser.setPassword(null);
            return convertToUserDto(savedUser);
        } catch (DataIntegrityViolationException ex) {
            throw new AccountAlreadyExistsException(
                    "An account already exists with email::" + user.getEmail() +
                    ". Please try login instead.");
        }
    }

    public User retrieveUser(Long userId) throws DataDoesNotExistsException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            log.debug(CONTENT_FOUND_LOG_MESSAGE, getRequestType(USER), userId);
            return user.get();
        }
        log.error(NO_CONTENT_LOG_MESSAGE, getRequestType(USER), userId);
        throw new DataDoesNotExistsException(noContentForTypeAndIdMessage(PRODUCT, userId));
    }

    public UserDto retrieveUserDto(Long userId) throws DataDoesNotExistsException {
        return convertToUserDto(retrieveUser(userId));
    }

    private UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        return userDto;
    }

}
