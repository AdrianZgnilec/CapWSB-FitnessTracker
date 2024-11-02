package com.capgemini.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
/**
 * A Data Transfer Object (DTO) for representing user data.
 * Used to transfer data between layers of the application without exposing the full User entity.
 *
 * @param id         the unique identifier of the user, can be null when creating a new user
 * @param firstName  the first name of the user, must not be null
 * @param lastName   the last name of the user, must not be null
 * @param birthdate  the birthdate of the user, formatted as yyyy-MM-dd, must not be null
 * @param email      the email address of the user, must be unique and not null
 */
public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {

}
