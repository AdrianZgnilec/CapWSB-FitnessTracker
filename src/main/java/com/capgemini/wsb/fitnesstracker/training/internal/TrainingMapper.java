package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between {@link Training} entities and {@link TrainingDto} data transfer objects (DTOs).
 * This class facilitates the transformation of data between the persistence layer and the application layer.
 */
@Component
@RequiredArgsConstructor
class TrainingMapper {

    private final UserProvider userProvider;

    /**
     * Converts a {@link Training} entity to a {@link TrainingDto}.
     *
     * @param training the {@link Training} entity to be converted
     * @return a {@link TrainingDto} containing the corresponding data from the given {@link Training} entity
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getUser().getId(),
                training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /**
     * Converts a {@link TrainingDto} to a {@link Training} entity.
     * Resolves the {@link User} associated with the given user ID in the DTO.
     *
     * @param trainingDto the {@link TrainingDto} to be converted
     * @return a {@link Training} entity populated with the data from the given {@link TrainingDto}
     * @throws IllegalArgumentException if the user ID in the DTO does not correspond to an existing {@link User}
     */
    Training toEntity(TrainingDto trainingDto) {
        User user = userProvider.getUser(trainingDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + trainingDto.userId()));
        return new Training(
                user,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()
        );
    }
}
