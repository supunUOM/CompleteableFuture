package async;

import java.util.concurrent.CompletableFuture;

public class ErrorHandling {
    public static void main(String[] args) {
        /**
         * .handle() method
         */
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            if (false) {
                throw new RuntimeException("Error@Future");
            }
            return "success";
        }).handle((result, error) -> {
            /**
             * result - will return prev result
             * error - throwing error
             */
            System.out.println("result:"+result);
            if (error != null) {
                System.out.println(error.getMessage());
                return "error";
            }
            return "null";
        });

        /**
         * .exceptionally() method
         */
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Error@Future");
            }
            return "success";
        }).exceptionally((e) -> {
            if (e != null) {
                System.out.println(e.getMessage());
                return "error";
            }
            return "null";
        });

        System.out.println(future1.join());
//        System.out.println(future2.join());
    }
}
