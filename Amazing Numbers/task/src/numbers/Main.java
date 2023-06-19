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
            buzzNumbers(number);
        } catch (InputMismatchException ime) {
            System.out.println("This number is not natural!");
        }
    }

    private static void isEven(int number) {
        if (number % 2 == 0) {
            System.out.println("This number is Even.");
        } else {
            System.out.println("This number is Odd.");
        }
    }

    private static void buzzNumbers(Integer number) {
        if (number < 1) {
            System.out.println("This number is not natural!");
        } else {
            isEven(number);

            String stringNumber = number.toString();
            int length = stringNumber.length();
            char lastNumber = stringNumber.charAt(length - 1);

            int counter = 0;
            if (number % 7 == 0) {
                counter+=2;
            }

            if (lastNumber == '7') {
                counter++;
            }

            if (counter == 0) {
                System.out.println("It is not a Buzz number.");
            } else {
                System.out.println("It is a Buzz number.");
            }

            System.out.println("Explanation:");
            String explanation = switch (counter) {
                case 3: yield number + " is divisible by 7 and ends with 7.";
                case 2: yield number + " is divisible by 7.";
                case 1: yield number + " ends with 7.";
                default: yield number + " is neither divisible by 7 nor does it end with 7.";
            };

            System.out.println(explanation);
        }
    }
}
