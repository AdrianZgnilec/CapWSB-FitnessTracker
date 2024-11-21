package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * REST Controller for managing {@link Training} entities.
 * Provides endpoints for creating, retrieving, and updating training records.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Creates a new training record.
     *
     * @param trainingDto The {@link TrainingDto} containing the details of the training to be created.
     * @return The created training as a {@link TrainingDto}.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingDto trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(savedTraining);
    }

    /**
     * Retrieves all training records.
     *
     * @return A list of all training records as {@link TrainingDto}.
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all trainings that ended after a specific date.
     *
     * @param afterTime The cutoff date (format: yyyy-MM-dd) for filtering trainings.
     * @return A list of trainings ending after the specified date as {@link TrainingDto}.
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getTrainingsFinishedAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return trainingService.getTrainingsEndedAfter(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all trainings for a specific activity type.
     *
     * @param activityType The activity type to filter trainings.
     * @return A list of trainings for the specified activity type as {@link TrainingDto}.
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all trainings associated with a specific user.
     *
     * @param userId The ID of the user whose trainings are to be retrieved.
     * @return A list of trainings for the specified user as {@link TrainingDto}.
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Updates an existing training record.
     *
     * @param id The ID of the training to update.
     * @param trainingDto The {@link TrainingDto} containing the updated details.
     * @return The updated training record as a {@link TrainingDto}.
     */
    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {
        Training updatedTraining = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingService.updateTraining(id, updatedTraining);
        return trainingMapper.toDto(savedTraining);
    }
}
