package jah.catchflight.common.validation;

/**
 * A sealed interface representing the result of an input validation process.
 * This interface defines two possible outcomes of validation: a successful validation
 * ({@link Valid}) or an unsuccessful validation with an error message ({@link NotValid}).
 */
public sealed interface InputValidationResult {
    record Valid() implements InputValidationResult {}
    record NotValid(String message) implements InputValidationResult {}
}
