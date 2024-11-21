package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;

/**
 * Interface for managing training operations in the FitnessTracker application.
 * Provides methods to create, retrieve, update, and filter training sessions.
 */
public interface TrainingService {

    /**
     * Creates a new training session.
     *
     * @param training The {@link Training} object containing details of the training session.
     * @return The created {@link Training} with a generated ID.
     */
    Training createTraining(Training training);

    /**
     * Retrieves all training sessions.
     *
     * @return A list of all {@link Training} sessions in the system.
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves all training sessions associated with a specific user.
     *
     * @param userId The ID of the user whose training sessions should be retrieved.
     * @return A list of {@link Training} sessions associated with the specified user.
     */
    List<Training> getTrainingsByUserId(Long userId);

    /**
     * Retrieves all training sessions that ended after a specified date.
     *
     * @param date The cutoff date for filtering training sessions.
     * @return A list of {@link Training} sessions that ended after the specified date.
     */
    List<Training> getTrainingsEndedAfter(Date date);

    /**
     * Retrieves all training sessions associated with a specific activity type.
     *
     * @param activityType The {@link ActivityType} to filter training sessions by.
     * @return A list of {@link Training} sessions matching the specified activity type.
     */
    List<Training> getTrainingsByActivityType(ActivityType activityType);

    /**
     * Updates an existing training session with new details.
     *
     * @param trainingId      The ID of the training session to update.
     * @param updatedTraining The {@link Training} object containing the updated details.
     * @return The updated {@link Training} object.
     */
    Training updateTraining(Long trainingId, Training updatedTraining);
}
