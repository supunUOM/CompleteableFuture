package async;

import java.util.concurrent.CompletableFuture;

public class AllOfWithDifferentReturnTypes {
    public static void main(String[] args) {

        CompletableFuture<Integer> future1 = future1();
        CompletableFuture<Float> future2 = future2();
        CompletableFuture<String> future3 = future3();

        /**
         * 1st approach
         * .thenAccept() method will called all future1, future2, future3 are resolved.
         */
//        System.out.println("======== 1st approach");
//        CompletableFuture.allOf(future1, future2, future3)
//                .thenAccept(t -> {
//                    future1.join();
//                    future2.join();
//                    future3.join();
//                })
//                .join();


        /**
         * 2nd approach using DataPayload class - 1
         * We can use thenApply() method also
         */
//        System.out.println("======== 2st approach - 1");
//        CompletableFuture.allOf(future1, future2, future3)
//                .thenApply(t ->
//                        new DataClass(
//                                future1.join(),
//                                future2.join(),
//                                future3.join()
//                        )
//                )
//                .thenAccept(System.out::println)
//                .join();

        /**
         * 2nd approach using DataPayload class - 2
         * We can use thenApply() method also
         */
        System.out.println("======== 2st approach - 2");
        CompletableFuture<DataPayload> futureRes = CompletableFuture.allOf(future1, future2, future3)
                .thenApply(t ->
                        new DataPayload(
                                future1.join(),
                                future2.join(),
                                future3.join()
                        )
                );

        DataPayload data = futureRes.join();
        System.out.println(data);
    }

    public static CompletableFuture<Integer> future1() {
        return CompletableFuture.supplyAsync(() -> {
            BasicComFuture.delay(3);
            System.out.println("Future 1:" + Thread.currentThread().getName());
            return 2;
        }).exceptionally(throwable -> {
            System.out.println("exception occurred." + throwable.getMessage());
            return Integer.MIN_VALUE;
        });
    }

    public static CompletableFuture<Float> future2() {
        return CompletableFuture.supplyAsync(() -> {
            BasicComFuture.delay(3);
            System.out.println("Future 2:" + Thread.currentThread().getName());
            return 1f;
        }).exceptionally(throwable -> {
            System.out.println("exception occurred." + throwable.getMessage());
            return 0f;
        });
    }

    public static CompletableFuture<String> future3() {
        return CompletableFuture.supplyAsync(() -> {
            BasicComFuture.delay(3);
            System.out.println("Future 3:" + Thread.currentThread().getName());
            return "Supun";
        }).exceptionally(throwable -> {
            System.out.println("exception occurred." + throwable.getMessage());
            return "Error";
        });
    }

}

class DataPayload {
    Integer id;
    Float price;
    String name;

    public DataPayload(Integer id, Float price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DataClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
