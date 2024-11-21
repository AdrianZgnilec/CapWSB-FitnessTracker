package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for managing {@link Training} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and custom query methods.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds all trainings associated with a specific user ID.
     *
     * @param userId the ID of the user whose trainings are to be retrieved
     * @return a list of trainings associated with the given user ID
     */
    List<Training> findByUserId(Long userId);

    /**
     * Finds all trainings that ended after the specified date.
     *
     * @param date the cutoff date for filtering trainings
     * @return a list of trainings that ended after the given date
     */
    List<Training> findByEndTimeAfter(Date date);

    /**
     * Finds all trainings with the specified activity type.
     *
     * @param activityType the activity type to filter trainings by
     * @return a list of trainings with the given activity type
     */
    List<Training> findByActivityType(ActivityType activityType);
}
