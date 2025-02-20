package async;

import java.util.concurrent.CompletableFuture;

public class AllOfAndAnyOfMethods {
    public static void main(String[] args) {
        System.out.println("All Thread count:"+Thread.activeCount());
//        CompletableFuture.allOf(future1(), future2(), future3()).join();
        CompletableFuture.anyOf(future1(), future2(), future3()).join();
    }

    public static CompletableFuture<String> future1() {
        return CompletableFuture.supplyAsync(() -> {
            BasicComFuture.delay(1);
            System.out.println("Future 1:" + Thread.currentThread().getName());
            return "1";
        });
    }

    public static CompletableFuture<String> future2() {
        return CompletableFuture.supplyAsync(() -> {
            BasicComFuture.delay(2);
            System.out.println("Future 2:" + Thread.currentThread().getName());
            return "2";
        });
    }

    public static CompletableFuture<String> future3() {
        return CompletableFuture.supplyAsync(() -> {
            BasicComFuture.delay(3);
            System.out.println("Future 3:" + Thread.currentThread().getName());
            return "3";
        });
    }
}
