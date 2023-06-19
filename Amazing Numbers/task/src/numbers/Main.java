package numbers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number:");
        int number;
        try {
            number = scanner.nextInt();
            if (number >= 1) {
                printProperties(number);
            } else {
                System.out.println("This is number is not natural");
            }
        } catch (InputMismatchException ime) {
            System.out.println("This number is not natural!");
        }
    }

    private static void printProperties(int number) {
        System.out.println("Properties of " + number);

        System.out.println("\teven: " + isEven(number));
        System.out.println("\todd: " + isOdd(number));
        System.out.println("\tbuzz: " + isBuzzNumber(number));
        System.out.println("\tduck: " + isDuckNumber(number));
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

	private static boolean isOdd(int number) {
		return number % 2 == 1;
	}

	private static boolean isBuzzNumber(int number) {
        if (number % 7 == 0 || number % 10 == 7) {
            return true;
        }
		return false;
    }

    private static boolean isDuckNumber(int number) {
        String stringNumber = String.valueOf(number);
        return stringNumber.contains("0");
    }
}
