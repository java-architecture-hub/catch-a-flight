package jah.catchflight.common.policy;

/**
 * Represents the output of a domain policy evaluation in the CatchFlight application.
 * This sealed interface defines the possible outcomes of a policy check, which can either
 * be an allowance or a rejection with a specified reason.
 */
public sealed interface DomainPolicyOutput {

    /**
     * Indicates that the policy evaluation resulted in an allowance.
     * This record represents a successful policy check with no additional details.
     */
    record Allowance() implements DomainPolicyOutput {}

    /**
     * Indicates that the policy evaluation resulted in a rejection.
     * This record includes a reason explaining why the policy check failed.
     *
     * @param reason the explanation for the policy rejection
     */
    record Rejection(String reason) implements DomainPolicyOutput {}
}
