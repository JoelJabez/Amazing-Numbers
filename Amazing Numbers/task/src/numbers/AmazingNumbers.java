package numbers;

import java.util.Objects;
import java.util.Scanner;

public class AmazingNumbers {
	public static void start() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Amazing Numbers!\n");

		System.out.println("Supported requests:");
		System.out.println("- enter a natural number to know its properties;");
		System.out.println("- enter 0 to exit");

		boolean isDone = false;
		while (!isDone) {
			System.out.println("\nEnter a request:");
			long number;
			try {
				number = Long.parseLong(scanner.nextLine());
				if (number >= 1) {
					printProperties(number);
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

	private static void printProperties(long number) {
		System.out.printf("Properties of %,d\n", number);

		System.out.println("\t   even: " + isEven(number));
		System.out.println("\t    odd: " + !isEven(number));
		System.out.println("\t   buzz: " + buzzNumbers(number));
		System.out.println("\t   duck: " + duckNumbers(number));
		System.out.println("palindromic: " + palindromicNumbers(number));
	}

	private static boolean isEven(long number) {
		return number % 2 == 0;
	}

	private static boolean buzzNumbers(long number) {
		String stringNumber = Long.toString(number);
		int length = stringNumber.length();
		char lastNumber = stringNumber.charAt(length - 1);

		if (number % 7 == 0) {
			return true;
		} else return lastNumber == '7';
	}

	private static boolean duckNumbers(long number) {
		String stringNumber = String.valueOf(number);
		return stringNumber.contains("0");
	}

	private static boolean palindromicNumbers(long number) {
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
