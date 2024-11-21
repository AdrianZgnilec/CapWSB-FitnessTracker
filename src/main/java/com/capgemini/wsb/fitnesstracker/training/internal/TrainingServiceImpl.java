package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementation of the {@link TrainingService} interface.
 * Provides methods for managing {@link Training} entities in the system.
 */
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    /**
     * Creates a new training entity and saves it in the database.
     *
     * @param training the training entity to create
     * @return the saved training entity
     */
    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    /**
     * Retrieves all training entities from the database.
     *
     * @return a list of all training entities
     */
    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Retrieves all training entities associated with a specific user ID.
     *
     * @param userId the ID of the user whose trainings are to be retrieved
     * @return a list of trainings associated with the given user ID
     */
    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     * Retrieves all training entities that ended after a specific date.
     *
     * @param date the cutoff date for filtering trainings
     * @return a list of trainings that ended after the given date
     */
    @Override
    public List<Training> getTrainingsEndedAfter(Date date) {
        return trainingRepository.findByEndTimeAfter(date);
    }

    /**
     * Retrieves all training entities of a specific activity type.
     *
     * @param activityType the activity type for filtering trainings
     * @return a list of trainings with the given activity type
     */
    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    /**
     * Updates an existing training entity with new data.
     *
     * @param trainingId      the ID of the training to update
     * @param updatedTraining the training data to update with
     * @return the updated training entity
     * @throws TrainingNotFoundException if no training is found with the given ID
     */
    @Override
    public Training updateTraining(Long trainingId, Training updatedTraining) {
        return trainingRepository.findById(trainingId)
                .map(existingTraining -> {
                    existingTraining.setStartTime(updatedTraining.getStartTime());
                    existingTraining.setEndTime(updatedTraining.getEndTime());
                    existingTraining.setActivityType(updatedTraining.getActivityType());
                    existingTraining.setDistance(updatedTraining.getDistance());
                    existingTraining.setAverageSpeed(updatedTraining.getAverageSpeed());
                    return trainingRepository.save(existingTraining);
                })
                .orElseThrow(() -> new TrainingNotFoundException(trainingId));
    }
}
