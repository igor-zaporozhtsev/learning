package interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class TradeDispatcherService {

	public static void main(String[] args) {
		try(TradeDispatcher tradeDispatcher = new TradeDispatcher(3,3)){
			tradeDispatcher.start().get();
		} catch (Exception e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}
}


class TradeDispatcher implements AutoCloseable{

	BlockingQueue<MarketEvent> eventQueue;
	ExecutorService executorService;

	public TradeDispatcher(
		int queueCapacity,
		int threadAmount
	) {
		eventQueue  = new ArrayBlockingQueue<>(queueCapacity); //чому правильний вибір для буфера?,
		this.executorService = Executors.newFixedThreadPool(threadAmount);;
	}

	Future<?> start(){
		Future<?> producer = executorService.submit(() -> {
			new MarketDataFeed(this).produce();
		});

		for (int i = 0; i < 2; i++) {
			executorService.submit(() -> {
				while (true) {
					try {
						new OrderProcessor().consume(eventQueue.take()); //черга порожня → чекає поки з'явиться елемент
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						break;
					}
				}
			});
		}
		return producer;
	}

	public void publish(MarketEvent marketEvent) {
		try {
			eventQueue.put(marketEvent); // черга повна → чекає поки з'явиться місце
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() throws Exception {
		executorService.shutdownNow(); // правильно, він надішле interrupt consumer threads.
	}
}

class OrderProcessor{
	void consume(MarketEvent event){
		System.out.printf("Processing: event %s consumed, price %s%n", event.symbol(), event.price());
	}
}

class MarketDataFeed{
	private final TradeDispatcher dispatcher;

	MarketDataFeed(TradeDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	public void produce(){
		IntStream.rangeClosed(0, 10)
			.forEach( i -> {
				MarketEvent event = new MarketEvent("AAPL" + i, "0" + i);
				dispatcher.publish(event);
			});
	}
}

record MarketEvent(
	String symbol,
	String price
) {
	@Override
	public String toString() {
		return "MarketEvent{" +
			"symbol='" + symbol + '\'' +
			", price='" + price + '\'' +
			'}';
	}
}
