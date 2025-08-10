package jah.catchflight.bestdeal.port.in;

public interface IndexBestDealsUseCase {
    IndexBestDealsResult indexBestDeals(IndexBestDealsCommand indexBestDealsCommand);

    interface IndexBestDealsResult {}
    record IndexBestDealsCommand() {}
}
