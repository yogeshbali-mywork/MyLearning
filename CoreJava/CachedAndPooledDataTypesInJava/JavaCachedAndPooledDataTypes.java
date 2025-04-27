/**
 * Demonstrates various Java data types that are cached or pooled internally by
 * the JVM.
 * 
 * Benefits of caching: 
 * - Saves memory by reusing common immutable objects (Once
 * you create it, you cannot modify it).
 * - Improves performance, especially for frequently used values.
 *  - Avoids heap bloating for small and commonly used objects.
 * 
 * Cached Types Demonstrated: - String literals - Wrapper classes: Integer,
 * Byte, Short, Character, Long, Boolean - Enum constants
 * 
 * Note: Always use `.equals()` for value comparison instead of `==` when
 * working with boxed types.
 * 
 * @author
 */
public class JavaCachedAndPooledDataTypes {

	public static void main(String[] args) {
		demonstrateStringCaching();
		demonstrateWrapperCaching();
		demonstrateEnumCaching();
	}

	/**
	 * Demonstrates caching behavior of String literals.
	 */
	private static void demonstrateStringCaching() {
		System.out.println("=== String Caching Demo ===");
		String str1 = "hello";
		String str2 = "hello";
		
		if (str1 == str2) {
			System.out.println("String objects are pooled/cached."); // true
		}
		System.out.println();
		
		String str3 = new String("Namaste");
		String str4 = new String("Namaste");
		if (str3 != str4) {
			System.out.println("String objects str3 and str4 are not pooled/cached."); // false
		}
	
	}

	/**
	 * Demonstrates caching behavior of primitive wrapper classes like Integer,
	 * Byte, Short, Character, Long, and Boolean.
	 */
	private static void demonstrateWrapperCaching() {
		System.out.println();
		System.out.println("=== Wrapper Classes Caching Demo ==="); // true

		Integer int1 = 127;
		Integer int2 = 127;
		if (int1 == int2) {
			System.out.println("Integer objects are cached for values between -128 and 127."); // true
		}

		Byte byte1 = 127;
		Byte byte2 = 127;
		if (byte1 == byte2) {
			System.out.println("Byte objects are fully cached (-128 to 127)."); // true
		}

		Short short1 = 127;
		Short short2 = 127;
		if (short1 == short2) {
			System.out.println("Short objects are cached for values between -128 and 127."); // true
		}

		Character char1 = 127;
		Character char2 = 127;
		if (char1 == char2) {
			System.out.println("Character objects are cached for ASCII range 0 to 127."); // true
		}

		Long long1 = 127L;
		Long long2 = 127L;
		if (long1 == long2) {
			System.out.println("Long objects are cached for values between -128 and 127."); // true
		}

		Boolean bool1 = true;
		Boolean bool2 = true;
		if (bool1 == bool2) {
			System.out.println("Boolean objects are always cached (TRUE and FALSE)."); // true
		}

		System.out.println();
	}

	/**
	 * Demonstrates caching behavior of Enum constants.
	 */
	private static void demonstrateEnumCaching() {
		System.out.println("=== Enum Caching Demo ===");

		Day day1 = Day.MONDAY;
		Day day2 = Day.MONDAY;

		// Comparing references using '=='
		System.out.println("Comparing day1 and day2 with == : " + (day1 == day2)); // true

		// Comparing using .equals()
		System.out.println("Comparing day1 and day2 with equals(): " + day1.equals(day2)); // true

		// Printing hash codes to show they are same
		System.out.println("HashCode of day1: " + day1.hashCode());
		System.out.println("HashCode of day2: " + day2.hashCode());

		// Displaying all enum constants with their ordinal values
		// The ordinal() method of an enum returns the position (or index) of that enum constant in its declaration, starting from 0.
		for (Day day : Day.values()) {
			System.out.println(day + " ordinal: " + day.ordinal());
		}

		System.out.println();
	}
}

/**
 * Enum representing days of the week. Enum constants are automatically cached
 * and treated as singletons by the JVM.
 */
enum Day {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
