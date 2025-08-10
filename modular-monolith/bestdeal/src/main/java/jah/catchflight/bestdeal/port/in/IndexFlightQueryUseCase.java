package jah.catchflight.bestdeal.port.in;

public interface IndexFlightQueryUseCase {
    IndexFlightQueryResult indexFlightQuery(IndexFlightQueryCommand indexFlightQueryCommand);

    record IndexFlightQueryCommand() {}
    interface IndexFlightQueryResult {}
}
