package com.capgemini.wsb.fitnesstracker.user.api;
import java.time.LocalDate;
import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new {@link User} entity in the database.
     *
     * @param user the {@link User} object to be created
     * @return the created {@link User} entity with an assigned ID
     */
    User createUser(User user);
    /**
     * Deletes an existing {@link User} entity based on the given user ID.
     *
     * @param userId the unique identifier of the {@link User} to be deleted
     */
    void deleteUser(Long userId);
    /**
     * Updates an existing {@link User} entity with new values.
     *
     * @param userId the unique identifier of the {@link User} to be updated
     * @param user   the {@link User} object containing updated values
     * @return the updated {@link User} entity
     */
    User updateUser(Long userId, User user);
    /**
     * Finds and retrieves all {@link User} entities that match the given email.
     *
     * @param email the email address to search for
     * @return a list of {@link User} entities with the specified email
     */
    List<User> findUsersByEmail(String email);
    /**
     * Finds and retrieves all {@link User} entities older than the specified cutoff date.
     *
     * @param cutoffDate the date threshold for filtering users
     * @return a list of {@link User} entities whose birthdates are before the cutoff date
     */
    List<User> findUsersOlderThan(LocalDate cutoffDate);
}
