package numbers;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckProperty {

	private static long number;

	static Boolean propertyChecker(ArrayList<String> property) {
		ArrayList<String> invalid = new ArrayList<>();
		for (String value: property) {
			int present = 0;
			for (Properties properties: Properties.values()) {
				if (value.equals(properties.getName()) || value.equals(properties.getNotPresent())) {
					present++;
					break;
				}
			}

			if (present == 0) {
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
		System.out.print("Available properties: ");
		System.out.println(Arrays.toString(Properties.values()));
		return false;
	}

	static Boolean checkExclusivity(ArrayList<String> filters) {
		if (filters.size() != 1) {
			StringBuilder errorMessage = new StringBuilder("The request contains mutually exclusive properties: ");
			for (Properties properties: Properties.values()) {
				String property = properties.getName();
				String mutualProperty = "";

				try {
					mutualProperty = properties.getMutuallyExclusiveTo();
				} catch (NullPointerException npe) {
					System.out.println(mutualProperty);
					continue;
				}

				if (filters.contains(property) && filters.contains(mutualProperty)) {
					errorMessage.append(String.format("[%S, %S]", property, mutualProperty));
					break;
				} else if (filters.contains("-" + property) && filters.contains("-" + mutualProperty)) {
					errorMessage.append(String.format("[-%S, -%S]", property, mutualProperty));
					break;
				} else if (filters.contains(property) && filters.contains(properties.getNotPresent())) {
					errorMessage.append(String.format("[%S, -%S]", property, property));
					break;
				}
			}

			if (errorMessage.length() != 52) {
				System.out.println(errorMessage);
				System.out.println("There are no numbers with these properties.");
				return false;
			}
			return true;
		}
		return true;
	}

	static ArrayList<Boolean> callProperties(long number) {
		CheckProperty.number = number;
		ArrayList<Boolean> propertyCondition = new ArrayList<>();

		for (Properties properties: Properties.values()) {
			propertyCondition.add(
					switch (properties) {
						case EVEN -> number % 2 == 0;
						case ODD -> number % 2 == 1;
						case BUZZ -> number % 7 == 0 || number % 10 == 7;
						case DUCK -> String.valueOf(number).contains("0");

						case PALINDROMIC -> {
							String normal = String.valueOf(number);
							StringBuilder reverse = new StringBuilder(normal).reverse();

							yield normal.contentEquals(reverse);
						}

						case GAPFUL -> {
							int size = (int) Math.log10(number);

							if (size > 1) {
								int firstNumber = (int) (number / Math.pow(10, size));
								int lastNumber = (int) (number % 10);

								int sum = (firstNumber * 10) + lastNumber;

								yield number % sum == 0;
							}

							yield false;
						}

						case SPY -> {
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

							yield sum == product;
						}

						case SQUARE, SUNNY -> {
							if (properties.equals(Properties.SUNNY)) {
								number += 1;
							}

							double squareDouble = Math.sqrt(number);
							int squareInt = (int) squareDouble;

							number = CheckProperty.number;
							yield squareInt == squareDouble;
						}

						case JUMPING -> {
							int size = (int) Math.log10(number);

							if (size != 0) {
								ArrayList<Integer> numbers = new ArrayList<>();
								long tempNumber = number;

								while (tempNumber != 0) {
									numbers.add(0, (int) (tempNumber % 10));
									tempNumber = tempNumber / 10;
								}

								for (int i = 0; i < numbers.size() - 1; i++) {
									if (Math.abs(numbers.get(i) - numbers.get(i + 1)) != 1) {
										yield false;
									}
								}
								yield true;
							}
							yield true;
						}

						case HAPPY, SAD -> {
							long splitNumber = number;
							ArrayList<Long> duplicates = new ArrayList<>();

							while (true) {
								long tempNumber = 0;
								ArrayList<Integer> numbers = new ArrayList<>();
								while (splitNumber != 0) {
									numbers.add(0, (int) (splitNumber % 10));
									splitNumber = splitNumber / 10;
								}

								for (Integer n: numbers){
									tempNumber += Math.pow(n, 2);
								}

								if (tempNumber == 1) {
									yield properties.equals(Properties.HAPPY);
								} else if (tempNumber == number || duplicates.contains(tempNumber)) {
									yield properties.equals(Properties.SAD);
								}

								splitNumber = tempNumber;
								duplicates.add(tempNumber);
							}
						}
					}
			);
		}

		return propertyCondition;
	}
}
