package jah.catchflight.engagement.domain;

/**
 * An enumeration of possible user activities in the system.
 * <p>
 * This enum defines the types of user interactions that can be captured and processed
 * by the system, such as signing in, querying flight offers, or booking flights.
 * It is typically used in conjunction with {@link CaptureUserActivityService} to
 * record user actions in an event-driven architecture.
 * </p>
 *
 * @see CaptureUserActivityService
 * @see CaptureUserActivityCommand
 */
public enum UserActivity {
    /**
     * Represents a user signing into the system.
     */
    SIGN_IN,

    /**
     * Represents a user signing out of the system.
     */
    SIGN_OUT,

    /**
     * Represents a user submitting a query, such as a search for flight offers.
     */
    QUERY_SENT,

    /**
     * Represents a user canceling a submitted query.
     */
    QUERY_CANCELED,

    /**
     * Represents a user viewing a specific deal or offer.
     */
    DEAL_VIEWED,

    /**
     * Represents a user successfully booking a flight.
     */
    FLIGHT_BOOKED,

    /**
     * Represents a user canceling a previously booked flight.
     */
    FLIGHT_CANCELED
}

