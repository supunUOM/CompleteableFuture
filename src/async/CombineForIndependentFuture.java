package async;

import java.util.concurrent.CompletableFuture;

public class CombineForIndependentFuture {
    public static void main(String[] args) {

        /**
         * thenCombine() method for execute independent tasks.
         */
        CompletableFuture<String> future = getUserEmail()
                .thenCombine(getWhether(), (email, whether) -> {
//                    BasicComFuture.delay(1);
                    System.out.println("result time: " + System.currentTimeMillis() / 1000);
                    return whether + " for " + email;
                });
        System.out.println("Do something");
//        BasicComFuture.delay(3);
        System.out.println(future.join());
    }

    private static CompletableFuture<String> getUserEmail() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getUserEmail start: " + System.currentTimeMillis() / 1000);
//            System.out.println("getUserEmail Thread:" + Thread.currentThread().getName());
            BasicComFuture.delay(3);
            System.out.println("getUserEmail end: " + System.currentTimeMillis() / 1000);
            return "supun@gmail.com";
        });
    }

    private static CompletableFuture<String> getWhether() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getWhether start: " + System.currentTimeMillis() / 1000);
//            System.out.println("getWhether Thread:" + Thread.currentThread().getName());
            BasicComFuture.delay(2);
            System.out.println("getWhether end: " + System.currentTimeMillis() / 1000);
            return "supun@gmail.com";
        });
    }
}


