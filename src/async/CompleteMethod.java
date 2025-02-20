package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompleteMethod {
    public static void main(String[] args) {
        CompletableFuture<String> future = new CompletableFuture<>();

        // Simulate a remote service call in a separate thread
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Thread.sleep(5000); // Simulating a long-running task (e.g., API call)
                future.complete("Remote Service Response");
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });

        // Fallback mechanism: Complete manually if the task takes too long
        Executors.newScheduledThreadPool(1).schedule(() -> {
            if (!future.isDone()) {
                System.out.println("task is not done...");
                future.complete("Fallback Value: Service Timed Out");
            }
        }, 3, TimeUnit.SECONDS); // Timeout after 3 seconds

        // Get the result (either from the remote service or the fallback)
        try {
            String result = future.get();
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
