package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Objects;
import java.util.Optional;
import java.util.List;
import java.time.LocalDate;

/**
 * Repository interface for managing {@link User} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and custom query methods for the {@link User} entity.
 * This interface is used for database access related to user data, leveraging Spring Data JPA functionalities.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }
    /**
     * Finds all users whose email addresses contain the specified string.
     *
     * @param email the string to search for within user email addresses
     * @return a list of {@link User} entities whose email addresses contain the specified string
     */
    List<User> findUserByEmail(String email);
    /**
     * Finds all users who are older than a specified cutoff date based on their birthdate.
     *
     * @param cutoffDate the date to compare against users' birthdates
     * @return a list of {@link User} entities whose birthdates are on or before the specified cutoff date
     */
    @Query("SELECT u FROM User u WHERE u.birthdate <= :cutoffDate")
    List<User> findUsersOlderThan(LocalDate cutoffDate);

}
