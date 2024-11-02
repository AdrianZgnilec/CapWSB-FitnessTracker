package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * A Data Transfer Object representing a User with basic information.
 *
 * @param id        the unique identifier of the user, nullable if the user is not yet persisted
 * @param firstName the first name of the user, must not be null
 * @param lastName  the last name of the user, must not be null
 * @param birthdate the birthdate of the user in "yyyy-MM-dd" format
 * @param email     the email address of the user, must not be null
 */
record UserDto(@Nullable Long id, String firstName, String lastName,
               @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
               String email) {

}
