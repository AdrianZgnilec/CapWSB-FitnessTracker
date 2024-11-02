package com.capgemini.wsb.fitnesstracker.user.internal;
import jakarta.annotation.Nullable;

/**
 * A Data Transfer Object (DTO) representing basic information about a user.
 * This record is used for transferring minimal user data, specifically the user's ID, first name, and last name.
 * It is suitable for scenarios where full user details are not required, such as lists or summary views.
 *
 * @param id        the unique identifier of the user, can be null for new users
 * @param firstName the first name of the user
 * @param lastName  the last name of the user
 */
record UserSimpleDto(@Nullable Long Id, String firstName, String lastName) {
}
