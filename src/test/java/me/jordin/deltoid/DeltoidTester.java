package me.jordin.deltoid;

/**
 * Created by Jordin on 6/19/2017.
 * Jordin is still best hacker.
 */
public class DeltoidTester {
    private static long startTime;

    public static void main(String[] arguments) throws Exception {
        startTime = System.nanoTime();

    }

    public static void logWithTime(String test) {
        System.out.println(String.format("%s: %fs", test, (System.nanoTime() - startTime) / 1000000000D));
        startTime = System.nanoTime();
    }
}
