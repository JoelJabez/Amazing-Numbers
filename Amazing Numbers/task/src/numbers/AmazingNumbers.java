package numbers;

import java.util.*;

public class AmazingNumbers {
	private static long number;
	private static final ArrayList<String> propertyList = new ArrayList<>();

	public static void start() {
		Scanner scanner = new Scanner(System.in);

		addToPropertyList();
		System.out.println("Welcome to Amazing Numbers!\n");
		printRequests();
		boolean isDone = false;
		while (!isDone) {
			System.out.println("\nEnter a request:");
			try {
				String text = scanner.nextLine();
				String[] arguments = text.split(" ");
				number = Long.parseLong(text.split(" ")[0]);
				int length = arguments.length;
				int times = 0;

				if (length > 1) {
					times = Integer.parseInt(text.split(" ")[1]);
				}

				switch (length) {
					case 1 -> {
						if (text.equals("")) {
							printRequests();
						} else {
							if (number >= 1) {
								printProperties(callProperties());
							} else if (number == 0) {
								isDone = true;
							} else {
								System.out.println("The first parameter should be a natural number or zero.");
							}
						}
					}

					case 2 -> {
						if (times >= 1) {
							printProperties(times);
						} else {
							System.out.println("The second parameter should be a natural number");
						}
					}

					case 3 -> {
						String property = text.split(" ")[2].toLowerCase();
						if (!propertyChecker(property)) {
							System.out.printf("The property [%S] is wrong.\n", property);

							System.out.print("Available properties: [");
							for (int i = 0; i < propertyList.size(); i++) {
								System.out.printf("%S", propertyList.get(i).strip());
								if (i != propertyList.size() - 1) {
									System.out.print(", ");
								}
							}
							System.out.println("]");
						} else {
							printProperties(property, times);
						}
					}
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid input");
			}
		}
		System.out.println("\nGoodbye!");
	}

	private static void addToPropertyList() {
		String[] properties = {"\t   buzz", "\t   duck", "palindromic", "\t gapful", "\t\tspy", "\t   even", "\t    " +
				"odd"};
		for (String property: properties) {
			propertyList.add(property);
		}
	}

	private static void printRequests() {
		System.out.println("Supported requests:");
		System.out.println("- enter a natural number to know its properties;");
		System.out.println("- enter two natural numbers to obtain the properties of the list:");
		System.out.println("  * the first parameter represents a starting number;");
		System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
		System.out.println("- two natural numbers and a property to search for;");
		System.out.println("- separate the parameters with one space;");
		System.out.println("- enter 0 to exit.");
	}

	private static ArrayList<Boolean> callProperties() {
		ArrayList<Boolean> propertyCondition = new ArrayList<>();

		propertyCondition.add(isBuzzNumber());
		propertyCondition.add(isDuckNumber());
		propertyCondition.add(isPalindromicNumber());
		propertyCondition.add(isGapfulNumber());
		propertyCondition.add(isSpyNumber());
		propertyCondition.add(isEven());
		propertyCondition.add(isOdd());

		return propertyCondition;
	}

	private static HashMap<String, Boolean> assignProperties() {
		ArrayList<Boolean> propertyCondition = callProperties();

		HashMap<String, Boolean> properties = new HashMap<>();
		for (int i = 0; i < propertyCondition.size(); i++) {
			properties.put(propertyList.get(i).strip(), propertyCondition.get(i));
		}

		return properties;
	}

	private static boolean propertyChecker(String property) {
		for (String s : propertyList) {
			if (property.equals(s.strip())) {
				return true;
			}
		}
		return false;
	}

	private static void printProperties(String property, int count) {

		while(count != 0) {
			HashMap<String, Boolean> properties = assignProperties();
			if (properties.get(property)) {
				System.out.printf("%d is ", number);
				int counter = 0;
				for (String s: propertyList) {
					s = s.strip();
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

				count--;
			}
			number++;
		}
	}

	private static void printProperties(int times) {
		System.out.println();

		long max = number + times;
		for (long i = number; i < max; i++) {
			System.out.printf("%d is ", i);
			number = i;
			HashMap<String, Boolean> properties = assignProperties();
			int counter = 0;
			for (String s: propertyList) {
				s = s.strip();
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

	private static void printProperties(ArrayList<Boolean> propertyConditions) {
		System.out.printf("Properties of %,d\n", number);

		for (int i = 0; i < propertyConditions.size(); i++) {
			System.out.printf("%s: %b\n", propertyList.get(i), propertyConditions.get(i));
		}
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
}
