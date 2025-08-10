package jah.catchflight.bestdeal.port.in;

public interface IndexBestDealsUseCase {
    IndexBestDealsResult indexBestDeals(IndexBestDealsCommand indexBestDealsCommand);

    record IndexBestDealsCommand() {}
    interface IndexBestDealsResult {}
}
