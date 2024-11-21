package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Entity class representing a training session in the FitnessTracker application.
 * This class is mapped to the `trainings` table in the database.
 * It includes details about the user, start and end times, activity type, distance, and average speed.
 */
@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Setter
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Enumerated(EnumType.ORDINAL)
    @Setter
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Setter
    @Column(name = "distance")
    private double distance;

    @Setter
    @Column(name = "average_speed")
    private double averageSpeed;

    /**
     * Constructor for creating a new training session.
     *
     * @param user          The user associated with the training session.
     * @param startTime     The start time of the training session.
     * @param endTime       The end time of the training session.
     * @param activityType  The type of activity performed.
     * @param distance      The distance covered during the training session.
     * @param averageSpeed  The average speed during the training session.
     */
    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
