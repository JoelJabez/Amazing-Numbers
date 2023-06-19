package numbers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number:");
        try {
            int number = scanner.nextInt();
            buzzChecker(number);
        } catch (InputMismatchException ime) {
            System.out.println("This number is not natural!");
        }
    }

    private static void evenChecker(int number) {
        if (number % 2 == 0) {
            System.out.println("This number is Even.");
        } else {
            System.out.println("This number is Odd.");
        }
    }

    private static void buzzChecker(Integer number) {
        if (number < 1) {
            System.out.println("This number is not natural!");
        } else {
            evenChecker(number);

            int counter = 0;
            if (number % 7 == 0) {
                counter+=2;
            }

            if (number % 10 == 7) {
                counter++;
            }

            if (counter == 0) {
                System.out.println("It is not a Buzz number.");
            } else {
                System.out.println("It is a Buzz number.");
            }

            System.out.println("Explanation:");
            String explanation = number + switch(counter) {
                case 3: yield " is divisible by 7 and ends with 7.";
                case 2: yield " is divisible by 7.";
                case 1: yield " ends with 7.";
                default: yield " is neither divisible by 7 nor does it end with 7.";
            };

            System.out.println(explanation);
        }
    }
}
