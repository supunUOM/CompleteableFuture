package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class BasicComFuture {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(BasicComFuture::getResponse)
                .thenApply(s -> "Hello " + s)
                .exceptionally(e -> "exception fallback value by Complete...");

        if (!future.isDone()) {
            System.out.println("still executing.");
        }

        /**
         * .thenAccept() will get Consumer object.
         */
        future.thenAccept(System.out::println);

        /**
         * .thenRun() will get Runnable object.
         */
        future.thenRun(() -> System.out.println("finished by .thenRun()."));

        /**
         * isCompletedExceptionally() will detect the exception occurred or not during execution.
         */
        if (future.isCompletedExceptionally()) {
            System.out.println("exception occurred, isCompletedExceptionally() called...");
        }

        /**
         * .join() will return result
         */
        var res = future.join();

        /**
         * Using future.complete() method we can provide fallback value if our method failed.
         */
        future.complete("Complete value");

    }

    private static String getResponse() {
        delay(2);
        if (true) {
            throw new RuntimeException("Runtime Exception");
        }
        return "Supun";
    }

    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

