package JavaCore.multithreading.CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ResilientDashboardAggregationDemo {

	public static void main(String[] args) {
		PriceServiceClient priceClient = () -> "100"; //якщо падає → кинути exception (fail fast)
		BalanceServiceClient balanceClient = () -> "500"; // якщо падає або timeout → повернути "0"
		NewsServiceClient newsClient =
			() -> List.of("news1", "new2"); //якщо падає → empty list // якщо timeout → empty list

		ResilientDashboardAggregator dashboardAggregator =
			new ResilientDashboardAggregator(priceClient, balanceClient, newsClient);
		Dashboard dashboard = dashboardAggregator.getAggregatedDashboard();
		System.out.println(dashboard);

	}

}

class ResilientDashboardAggregator {

	private final PriceServiceClient priceServiceClient;
	private final BalanceServiceClient balanceServiceClient;
	private final NewsServiceClient newsServiceClient;

	ResilientDashboardAggregator(
		PriceServiceClient priceServiceClient,
		BalanceServiceClient balanceServiceClient,
		NewsServiceClient newsServiceClient
	) {
		this.priceServiceClient = priceServiceClient;
		this.balanceServiceClient = balanceServiceClient;
		this.newsServiceClient = newsServiceClient;
	}

	public Dashboard getAggregatedDashboard() {

		CompletableFuture<String> futurePrice = CompletableFuture.supplyAsync(priceServiceClient::getPrice)
			.orTimeout(1, TimeUnit.SECONDS);
		CompletableFuture<String> futureBalance = CompletableFuture.supplyAsync(balanceServiceClient::getBalance)
			.orTimeout(1, TimeUnit.SECONDS)
			.exceptionally(ex -> "0");
		CompletableFuture<List<String>> futureNewsList = CompletableFuture.supplyAsync(newsServiceClient::getNews)
			.orTimeout(1, TimeUnit.SECONDS)
			.exceptionally(ex -> List.of());

		return new Dashboard(
			futurePrice.join(),
			futureBalance.join(),
			futureNewsList.join()
		);
	}
}

interface PriceServiceClient {

	String getPrice();
}

interface BalanceServiceClient {

	String getBalance();
}

interface NewsServiceClient {

	List<String> getNews();
}

record Dashboard(
	String price,
	String balance,
	List<String> news
) {

}