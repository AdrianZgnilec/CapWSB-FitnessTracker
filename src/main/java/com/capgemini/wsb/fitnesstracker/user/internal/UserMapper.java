package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;
/**
 * Mapper class for converting between User entities and their corresponding DTO representations.
 */
@Component
class UserMapper {

    /**
     * Converts a User entity to a UserDto.
     *
     * @param user the User entity to be converted, must not be null
     * @return a UserDto representing the given User entity
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }
    /**
     * Converts a User entity to a UserSimpleDto containing only basic information.
     *
     * @param user the User entity to be converted, must not be null
     * @return a UserSimpleDto with basic information from the given User entity
     */
    UserSimpleDto toSimpleDto(User user){
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }
    /**
     * Converts a UserDto to a User entity.
     *
     * @param userDto the UserDto to be converted, must not be null
     * @return a User entity created from the given UserDto
     */
    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

}
