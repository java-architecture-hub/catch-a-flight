package jah.catchflight.common.validation;

public sealed interface InputValidationResult {
    record Valid() implements InputValidationResult {}
    record NotValid(String message) implements InputValidationResult {}
}
