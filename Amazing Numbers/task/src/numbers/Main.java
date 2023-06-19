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
        System.out.println("\todd: " + !isEven(number));
        System.out.println("\tbuzz: " + buzzNumbers(number));
        System.out.println("\tduck: " + duckNumbers(number));
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static boolean buzzNumbers(int number) {
        String stringNumber = Integer.toString(number);
        int length = stringNumber.length();
        char lastNumber = stringNumber.charAt(length - 1);

        if (number % 7 == 0) {
            return true;
        } else return lastNumber == '7';
    }

    private static boolean duckNumbers(int number) {
        String stringNumber = String.valueOf(number);
        return stringNumber.contains("0");
    }
}
