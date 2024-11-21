package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

/**
 * Unified Data Transfer Object (DTO) for representing training details.
 * Handles both user ID for input requests and full user details for output responses.
 *
 * @param id            The unique identifier of the training.
 * @param userId        The unique identifier of the user associated with the training (used for input).
 * @param user          Full user details (used for output).
 * @param startTime     The start time of the training session.
 * @param endTime       The end time of the training session.
 * @param activityType  The type of activity performed during the training session.
 * @param distance      The distance covered during the training session.
 * @param averageSpeed  The average speed during the training session.
 */
public record TrainingDto(
        Long id,
        Long userId,
        User user,
        @JsonDeserialize(using = CustomDateDeserializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
        Date startTime,
        @JsonDeserialize(using = CustomDateDeserializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
}
