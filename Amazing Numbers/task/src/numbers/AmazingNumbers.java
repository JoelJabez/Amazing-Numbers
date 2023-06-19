package numbers;

import java.util.Objects;
import java.util.Scanner;

public class AmazingNumbers {
	private static long number;
	private static boolean isEven;
	private static boolean isOdd;
	private static boolean isBuzzNumber;
	private static boolean isDuckNumber;
	private static boolean isPalindromicNumber;

	public static void start() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Amazing Numbers!\n");

		System.out.println("Supported requests:");
		System.out.println("- enter a natural number to know its properties;");
		System.out.println("- enter 0 to exit");

		boolean isDone = false;
		while (!isDone) {
			System.out.println("\nEnter a request:");
			try {
				number = Long.parseLong(scanner.nextLine());
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


	private static void callProperties() {
		isEven = isEven();
		isOdd = isOdd();
		isBuzzNumber = isBuzzNumber();
		isDuckNumber = isDuckNumber();
		isPalindromicNumber = isPalindromicNumber();
	}

	private static void printProperties() {
		System.out.printf("Properties of %,d\n", number);

		System.out.println("\t   even: " + isEven);
		System.out.println("\t    odd: " + isOdd);
		System.out.println("\t   buzz: " + isBuzzNumber);
		System.out.println("\t   duck: " + isDuckNumber);
		System.out.println("palindromic: " + isPalindromicNumber);
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
}
