package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enumeration representing the types of activities that can be recorded during a training session.
 * Each activity type has a user-friendly display name.
 */
public enum ActivityType {

    /**
     * Running activity.
     */
    RUNNING("Running"),
    /**
     * Cycling activity.
     */
    CYCLING("Cycling"),
    /**
     * Walking activity.
     */
    WALKING("Walking"),
    /**
     * Swimming activity.
     */
    SWIMMING("Swimming"),
    /**
     * Tennis activity.
     */
    TENNIS("Tennis");

    /**
     * A user-friendly display name for the activity type.
     */
    private final String displayName;

    /**
     * Constructs an {@link ActivityType} with the specified display name.
     *
     * @param displayName A human-readable name for the activity type.
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retrieves the user-friendly display name of the activity type.
     *
     * @return The display name of the activity type.
     */
    public String getDisplayName() {
        return displayName;
    }
}
