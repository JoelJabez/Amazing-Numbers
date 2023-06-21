package numbers;

import java.util.ArrayList;
import java.util.Objects;

public class CheckProperty {
	private static long number;

	static Boolean propertyChecker(ArrayList<String> propertyList, ArrayList<String> property) {
		ArrayList<String> invalid = new ArrayList<>();
		for (String value: property) {
			int count = 0;
			for (String s: propertyList) {
				if (value.equals(s.strip())) {
					count++;
					break;
				}
			}

			if (count == 0) {
				invalid.add(value);
			}
		}

		if (invalid.size() == 1) {
			System.out.printf("The property [%S] is wrong.\n", invalid.get(0));

		} else if (invalid.size() > 1) {
			System.out.print("The properties [");
			for (int i = 0; i < invalid.size(); i++) {
				if (i != 0) {
					System.out.print(", ");
				}
				System.out.printf("%S", invalid.get(i));
			}
			System.out.println("] are wrong.");
		} else {
			return true;
		}
		System.out.print("Available properties: [");
		for (int i = 0; i < propertyList.size(); i++) {
			System.out.printf("%S", propertyList.get(i).strip());
			if (i != propertyList.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
		return false;
	}

	static Boolean checkExclusivity(ArrayList<String> filtersTemp) {
		ArrayList<String> filters = new ArrayList<>();
		for (String s : filtersTemp) {
			filters.add(s.strip());
		}

		StringBuilder errorMessage = new StringBuilder("The request contains mutually exclusive properties: ");
		if (filters.contains("odd") && filters.contains("even")) {
			errorMessage.append("[ODD, EVEN]");
		} else if (filters.contains("duck") && filters.contains("spy")) {
			errorMessage.append("[DUCK, SPY]");
		} else if (filters.contains("sunny") && filters.contains("square")) {
			errorMessage.append("[SUNNY, SQUARE]");
		}

		if (errorMessage.length() != 52) {
			System.out.println(errorMessage);
			System.out.println("There are no numbers with these properties.");
			return false;
		}
		return true;
	}

	static ArrayList<Boolean> callProperties(long number) {
		CheckProperty.number = number;
		ArrayList<Boolean> propertyCondition = new ArrayList<>();

		propertyCondition.add(isBuzzNumber());
		propertyCondition.add(isDuckNumber());
		propertyCondition.add(isPalindromicNumber());
		propertyCondition.add(isGapfulNumber());
		propertyCondition.add(isSpyNumber());
		propertyCondition.add(isSquare(number));
		propertyCondition.add(isSunny());
		propertyCondition.add(isEven());
		propertyCondition.add(isOdd());

		return propertyCondition;
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
		return String.valueOf(number).contains("0");
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

	private static boolean isSpyNumber() {
		ArrayList<Integer> splitNumbers = new ArrayList<>();
		long tempNumber = number;

		while (tempNumber != 0) {
			splitNumbers.add(0, (int) (tempNumber % 10));
			tempNumber = tempNumber / 10;
		}

		long sum = 0;
		long product = 1;
		for (Integer splitNumber : splitNumbers) {
			sum += splitNumber;
			product *= splitNumber;
		}

		return sum == product;
	}

	private static boolean isSquare(long number) {
		double squareDouble = Math.sqrt(number);
		int squareInt = (int) squareDouble;

		return squareInt == squareDouble;
	}

	private static boolean isSunny() {
		return isSquare(number + 1);
	}
}
