package cognyte.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class CompletableFuturesInteracting {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> getAnInt())
                .thenApply(i -> i * 2);

        //Thread.sleep(50);
        Thread.sleep(500); //sleeping half a second will make the main thread run, not the worker, as compared to sleeping just 50 ms

        integerCompletableFuture
                .thenAccept(i -> getPrintln(i));


        System.out.println();
        /// another use case, not running the pipeline immediately


        CompletableFuture<Integer>
                waitingPipeline = new CompletableFuture<Integer>()
                .thenApply(getIntegerIntegerFunction());

        waitingPipeline
                .thenAccept(data -> System.out.println("1. we got the data: " + data + " in " + Thread.currentThread()))
                .thenRun(() -> System.out.println("2. we are done with the data in " + Thread.currentThread()));

        waitingPipeline.thenApply(d -> {
            System.out.println("1. sneaky run, returning: " + d * 3);
            return d * 3;
        });

        System.out.println("calling the pipeline");
        waitingPipeline.complete(2); // this will trigger ALL three pipelines SEPARATELY!
        System.out.println("finished calling the pipeline");
    }

    private static void getPrintln(Integer i) {
        System.out.println("printing the variable i " + i + " in " + Thread.currentThread());
    }

    private static int getAnInt() {
        System.out.println("getting an int in: " + Thread.currentThread());
        return 10;
    }

    private static Function<Integer, Integer> getIntegerIntegerFunction() {
        System.out.println("1. the multiplication with * 2 got called in: " + Thread.currentThread());
        return i -> i * 2;
    }
}
