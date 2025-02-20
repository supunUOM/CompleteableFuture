package async;

import java.util.concurrent.CompletableFuture;

public class CombineForDependentFuture {

    public static void main(String[] args) {
        /**
         * Composing with dependent future.
         */
        long start = System.currentTimeMillis();
        System.out.println("Start main(): " + System.currentTimeMillis() / 1000);

        CompletableFuture<String> futureString = getUserDetails().thenCompose(CombineForDependentFuture::getWishList);

        System.out.println("Doing something by " + Thread.currentThread().getName());

        String result = futureString.join();

        System.out.println("result is:" + result);

        long end = System.currentTimeMillis();
        System.out.println("Consumed time:" + (end - start));

    }

    public static CompletableFuture<String> getUserDetails() {

        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Calling getUserDetails() by Thread:"
                    + Thread.currentThread().getName() + " Time:" + System.currentTimeMillis() / 1000);
            BasicComFuture.delay(2);
            return "Supun";
        });
    }

    public static CompletableFuture<String> getWishList(String userName) {

        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Calling getWishList() User:" + userName + " by Thread:"
                    + Thread.currentThread().getName() + " Time:" + System.currentTimeMillis() / 1000);
            BasicComFuture.delay(3);
            return userName + " Wish List";
        });
    }
}
