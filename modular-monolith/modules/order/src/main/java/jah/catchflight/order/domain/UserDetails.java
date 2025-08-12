package jah.catchflight.order.domain;

public record UserDetails(
        String firstName,
        String lastName,
        Address address) {
}
