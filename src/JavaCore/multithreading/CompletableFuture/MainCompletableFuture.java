package JavaCore.multithreading.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainCompletableFuture {

    private static NotifyService notifyService = new NotifyService();
    private static DeliveryService deliveryService = new DeliveryService();

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        /* Future execute in ForkJoinPool
           ForkJoinPool implements Executor service */

                                            /* CREATION */

        // It is useful when you have a value readily available data ("message") and want to convert it into a completed CompletableFuture
        CompletableFuture<String> stringFuture = CompletableFuture.completedFuture("message")
                .thenApply(String::toUpperCase);

        //when we don't want to get result and just run async
        //does not have any input and does not produce any output
        CompletableFuture<Void> runnableVoid = CompletableFuture.runAsync(()->
            notifyService.notifyVoid()
        );

        //we get result of operation
        //does not have any input but produces an output
        CompletableFuture<String> supplyVoid = CompletableFuture.supplyAsync( () ->
            notifyService.notifyWithReturn() //result of notification
        );

                                         /* TRANSFORMATION */

        //thenApply execute in same thread
        CompletableFuture<String> thenApply = CompletableFuture.supplyAsync( () ->
                notifyService.notifyWithReturn() //result of notification
        ).thenApply(String::toUpperCase);


        //thenApplyAsync execute in new thread
        CompletableFuture<String> thenApplyAsync = CompletableFuture.supplyAsync( () ->
                notifyService.notifyWithReturn() //result of notification
        ).thenApplyAsync(String::toUpperCase);

                                        /* How to RUN CompletableFuture */
        CompletableFuture<Void> thenRun = CompletableFuture.runAsync( () ->
                notifyService.notifyVoid() //result of notification
        ).thenRun(System.out::println);

        CompletableFuture<Void> thenAccept = CompletableFuture.runAsync( () ->
                notifyService.notifyVoid() //result of notification
        ).thenAccept(System.out::println);

                                        /* COMBINE 1 thenCombine*/
        CompletableFuture<String> first = CompletableFuture.supplyAsync( () ->
                notifyService.notifyWithReturn()
        ).thenApplyAsync(String::toUpperCase);

        CompletableFuture<String> second = CompletableFuture.supplyAsync( () ->
                notifyService.notifyWithReturn() //result of notification
        ).thenApplyAsync(String::toUpperCase);

        CompletableFuture<Boolean> combine = first.thenCombine(second, (firstResult, secondResult) ->
                firstResult.contains(secondResult));

                                        /* COMBINE 2 thenCompose */
        //thenCompose we can use result of first future in second future
        //FYI thenComposeAsync - just make call in different thread
        CompletableFuture<String> first1 = CompletableFuture.supplyAsync( () ->
                notifyService.notifyWithReturn()
        ).thenApplyAsync(String::toUpperCase);

        CompletableFuture<String> combine2 = first1.thenCompose(message ->
                CompletableFuture.supplyAsync(()-> deliveryService.callDeliveryServiceWithReturn(message)));


                                        /* COMBINE 2 thenAcceptBoth */
        CompletableFuture<Void> both = first.thenAcceptBoth(second, (firstResult, secondResult) ->
                notifyService.notifyVoid(firstResult, secondResult));


                                                /* Exceptions */

        combine2.exceptionally(Throwable::getMessage);
        combine2.handle((result, ex) -> {
            if (ex instanceof RuntimeException) {

            }
            return ex.getMessage();
        });

                                            /* How to get results */

        combine2.get(100, TimeUnit.SECONDS); //we need time out because thread could not be finished
        combine2.join();


    }
}


class NotifyService {

    public void notifyVoid(){
        //sent notification
    }

    public void notifyVoid(String fst, String sec){
        //sent notification
    }

    public String notifyWithReturn(){
       return "message was received";
    }
}

class DeliveryService {

    public String callDeliveryServiceWithReturn(String message){
        return "Accepted";
    }


}