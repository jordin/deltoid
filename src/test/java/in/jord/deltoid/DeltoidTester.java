package in.jord.deltoid;

import in.jord.deltoid.vector.Vec3;

/**
 * Created by Jordin on 6/19/2017.
 * Jordin is still best hacker.
 */
public class DeltoidTester {
    private static long startTime;

    public static void main(String[] arguments) throws Exception {
        startTime = System.nanoTime();

        for (double i = 0.1; i < 10000000; i++) {
            new Object();
        }

        logWithTime("Object Creation");

        for (double i = 0.1; i < 10000000; i++) {
            new Vec3(i % 10, 1, 1).length();
        }

        logWithTime("Length Calculation");
    }

    public static void logWithTime(String test) {
        System.out.println(String.format("%s: %fs", test, (System.nanoTime() - startTime) / 1000000000D));
        startTime = System.nanoTime();
    }
}
