package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;

@InboundPort
public interface DeleteAccountUseCase {
    public DeleteUserResult deleteUser(DeleteUserCommand deleteUserInput);

    public interface DeleteUserResult {}
    public record DeleteUserCommand() {}
}
