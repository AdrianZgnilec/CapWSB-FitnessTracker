package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
/**
 * Implementation of the {@link UserService} interface.
 * This class provides the business logic for managing {@link User} entities, including creation, retrieval,
 * updating, and deletion. It interacts with the {@link UserRepository} for database operations.
 */
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new user in the database.
     *
     * @param user the user entity to be created
     * @return the created {@link User} entity
     * @throws IllegalArgumentException if the user already has a database ID
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return an {@link Optional} containing the found {@link User}, or empty if not found
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user to retrieve
     * @return an {@link Optional} containing the found {@link User}, or empty if not found
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all {@link User} entities
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Deletes a user from the database by their ID.
     *
     * @param userId the ID of the user to delete
     */
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Updates an existing user with new information.
     *
     * @param userId the ID of the user to update
     * @param user   the updated {@link User} entity
     * @return the updated {@link User} entity
     * @throws IllegalArgumentException if the user with the specified ID is not found
     */
    @Override
    public User updateUser(Long userId, User user) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setBirthdate(user.getBirthdate());
                    existingUser.setEmail(user.getEmail());
                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    /**
     * Finds users by their email address.
     *
     * @param email the email address to search for
     * @return a list of {@link User} entities matching the specified email
     */
    @Override
    public List<User> findUsersByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    /**
     * Finds users older than the specified cutoff date based on their birthdate.
     *
     * @param cutoffDate the date to compare against users' birthdates
     * @return a list of {@link User} entities whose birthdates are on or before the specified cutoff date
     */
    @Override
    public List<User> findUsersOlderThan(LocalDate cutoffDate) {
        return userRepository.findUsersOlderThan(cutoffDate);
    }


}