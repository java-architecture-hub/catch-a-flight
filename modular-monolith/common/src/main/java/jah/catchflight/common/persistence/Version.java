package jah.catchflight.common.persistence;

/**
 * A record representing a version number.
 * This record encapsulates an integer value used to denote the version of an entity,
 * typically for purposes such as optimistic locking or versioning in a system.
 *
 * @param value the integer value representing the version
 */
public record Version(int value) {
    /**
     * Creates a new {@link Version} instance initialized to zero.
     * This is typically used as the initial version for a newly created entity.
     *
     * @return a {@link Version} instance with a value of 0
     */
    public static Version zero() {
        return new Version(0);
    }
}
