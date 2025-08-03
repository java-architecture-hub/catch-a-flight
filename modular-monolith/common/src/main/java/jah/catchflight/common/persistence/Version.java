package jah.catchflight.common.persistence;

public record Version(int value) {
    public static Version zero() {
        return new Version(0);
    }
}
