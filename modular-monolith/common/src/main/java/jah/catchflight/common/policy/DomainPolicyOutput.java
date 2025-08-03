package jah.catchflight.common.policy;

public sealed interface DomainPolicyOutput {
    record Allowance() implements DomainPolicyOutput {}
    record Rejection(String reason) implements DomainPolicyOutput {}
}
