package numbers;

import java.util.*;

public class AmazingNumbers {
	private static long number;
	private static boolean isBuzzNumber;
	private static boolean isDuckNumber;
	private static boolean isPalindromicNumber;
	private static boolean isGapfulNumber;
	private static boolean isEven;
	private static boolean isOdd;

	public static void start() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Amazing Numbers!\n");
		printRequests();
		boolean isDone = false;
		while (!isDone) {
			System.out.println("\nEnter a request:");
			try {
				String text = scanner.nextLine();
				if (Objects.equals(text, "")) {
					printRequests();
				} else {
					number = Long.parseLong(text.split(" ")[0]);
					int times = Integer.parseInt(text.split(" ")[1]);
					if (times >= 1) {
						printProperties(times);
					} else {
						System.out.println("The second parameter should be a natural number");
					}
				}
			} catch (ArrayIndexOutOfBoundsException aioobe) {
				if (number >= 1) {
					callProperties();
					printProperties();
				} else if (number == 0) {
					isDone = true;
				} else {
					System.out.println("The first parameter should be a natural number or zero.");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid input");
			}
		}
		System.out.println("\nGoodbye!");
	}

	private static void printRequests() {
		System.out.println("Supported requests:");
		System.out.println("- enter a natural number to know its properties;");
		System.out.println("- enter two natural numbers to obtain the properties of the list:");
		System.out.println("  * the first parameter represents a starting number;");
		System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
		System.out.println("- separate the parameters with one space;");
		System.out.println("- enter 0 to exit.");
	}

	private static void callProperties() {
		isBuzzNumber = isBuzzNumber();
		isDuckNumber = isDuckNumber();
		isPalindromicNumber = isPalindromicNumber();
		isGapfulNumber = isGapfulNumber();
		isEven = isEven();
		isOdd = isOdd();
	}

	private static HashMap<String, Boolean> assignProperties() {
		callProperties();

		HashMap<String, Boolean> properties = new HashMap<>();
		properties.put("buzz", isBuzzNumber);
		properties.put("duck", isDuckNumber);
		properties.put("palindromic", isPalindromicNumber);
		properties.put("gapful", isGapfulNumber);
		properties.put("even", isEven);
		properties.put("odd", isOdd);

		return properties;
	}

	private static void printProperties(int times) {
		ArrayList<String> propertyList = new ArrayList<>(Arrays.asList("buzz", "duck", "palindromic", "gapful", "even", "odd"));
		System.out.println();

		long max = number + times;
		for (long i = number; i < max; i++) {
			System.out.printf("%d is ", i);
			number = i;
			HashMap<String, Boolean> properties = assignProperties();
			int counter = 0;
			for (String s: propertyList) {
				if (properties.get(s)) {
					if (counter == 0) {
						System.out.print(s);
					} else {
						System.out.printf(", %s", s);
					}
					counter++;
				}
			}
			System.out.println();
		}
	}

	private static void printProperties() {
		System.out.printf("Properties of %,d\n", number);

		System.out.println("\t   buzz: " + isBuzzNumber);
		System.out.println("\t   duck: " + isDuckNumber);
		System.out.println("palindromic: " + isPalindromicNumber);
		System.out.println("\t gapful: " + isGapfulNumber);
		System.out.println("\t   even: " + isEven);
		System.out.println("\t    odd: " + isOdd);
	}

	private static boolean isEven() {
		return number % 2 == 0;
	}

	private static boolean isOdd() {
		return number % 2 == 1;
	}

	private static boolean isBuzzNumber() {
		if (number % 7 == 0 || number % 10 == 7) {
			return true;
		}
		return false;
	}

	private static boolean isDuckNumber() {
		String stringNumber = String.valueOf(number);
		return stringNumber.contains("0");
	}

	private static boolean isPalindromicNumber() {
		String normal = String.valueOf(number);
		StringBuilder reverse = new StringBuilder(normal).reverse();

		for (int i = 0; i < normal.length(); i++) {
			if (!Objects.equals(normal.charAt(i), reverse.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private static boolean isGapfulNumber() {
		int size = (int) Math.log10(number);

		if (size > 1) {
			int firstNumber = (int) (number / Math.pow(10, size));
			int lastNumber = (int) (number % 10);

			int sum = (firstNumber * 10) + lastNumber;

			return number % sum == 0;
		}

		return false;
	}
}
