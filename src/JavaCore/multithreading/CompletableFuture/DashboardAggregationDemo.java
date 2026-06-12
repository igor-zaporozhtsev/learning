package JavaCore.multithreading.CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class DashboardAggregationDemo {

	public static void main(String[] args) {
		DashboardAggregationService dataAggregator = new DashboardAggregationService(
			() -> "30",
			() -> "100",
			() -> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				return List.of("bal-bla", "bla-bla");
			}
		);

		System.out.println(dataAggregator.getAggregatedDashboard());
	}
}

class DashboardAggregationService {

	private final PriceServiceClient priceServiceClient;
	private final BalanceServiceClient balanceServiceClient;
	private final NewsServiceClient newsServiceClient;

	public DashboardAggregationService(
		PriceServiceClient priceServiceClient,
		BalanceServiceClient balanceServiceClient,
		NewsServiceClient newsServiceClient
	) {
		this.priceServiceClient = priceServiceClient;
		this.balanceServiceClient = balanceServiceClient;
		this.newsServiceClient = newsServiceClient;
	}

	public Dashboard getAggregatedDashboard() {
		CompletableFuture<String> priceFuture = CompletableFuture.supplyAsync(priceServiceClient::getPrice)
			.orTimeout(1, TimeUnit.SECONDS);
		CompletableFuture<String> balanceFuture = CompletableFuture.supplyAsync(balanceServiceClient::getBalance)
			.orTimeout(1, TimeUnit.SECONDS);
		CompletableFuture<List<String>> newsFuture = CompletableFuture.supplyAsync(newsServiceClient::getNews)
			.orTimeout(1, TimeUnit.SECONDS)
			.exceptionally(ex -> List.of());

		// allOf user case - виключно для того коли ми хочемо знати що всі future виконання тоді ми повині зробити якусь
		// дію формування report, логування, кешування, метрики
		CompletableFuture
			.allOf(priceFuture, balanceFuture, newsFuture)
			.thenRun(() -> System.out.println("all data collected"))
			.join();

		return new Dashboard(
			priceFuture.join(),
			balanceFuture.join(),
			newsFuture.join()
		);

/*
		future.get(); кидає checked exception (InterruptedException, ExecutionException) змушує писати try/catch
		future.join(); идає unchecked exception (RuntimeException) не потрібно try/catch
		join() — норм але тільки в кінці pipeline
*/

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
}


