/**
 * Demonstrates the behavior of Integer object caching in Java.
 * 
 * <p>Java caches Integer objects within the range of -128 to 127.
 * Outside this range, new Integer instances are created even for the same value.
 * This program shows how '==' behaves differently for cached vs non-cached Integer values.
 */
public class IntegerCachingBehavior {

    public static void main(String[] args) {
        // Creating two Integer objects with value 127
        Integer a = 127;
        Integer b = 127;

        // For values within the range of -128 to 127,
        // Java uses a cache for Integer objects to optimize memory usage.
        // Hence, 'a' and 'b' point to the same object.
        if (a == b) {
            System.out.println("127 == 127");
        } else {
            System.out.println("127 != 127");
        }

        // Creating two Integer objects with value 128
        Integer c = 128;
        Integer d = 128;

        // No caching for Integer objects with values outside -128 to 127.
        // Hence, 'c' and 'd' point to different objects.
        if (c == d) {
            System.out.println("128 == 128");
        } else {
            System.out.println("128 != 128");
        }

        // Correct way to compare values of Integer objects: using equals() method
        if (c.equals(d)) {
            System.out.println("c.equals(d) -> 128 == 128");
        }

        // Alternatively, unboxing the Integer to int and then using '=='
        if (c.intValue() == d.intValue()) {
            System.out.println("c.intValue() == d.intValue() -> 128 == 128");
        }
    }
}
