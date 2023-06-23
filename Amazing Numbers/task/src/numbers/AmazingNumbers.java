package numbers;

import java.util.*;

import static numbers.CheckProperty.*;
import static numbers.Printing.*;

public class AmazingNumbers {
	public static void begin() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Amazing Numbers!\n");
		printRequests();

		boolean isDone = false;
		while (!isDone) {
			System.out.println("\nEnter a request:");
			String text = "";
			try {
				text = scanner.nextLine();
				String[] arguments = text.split(" ");

				final long number = Long.parseLong(text.split(" ")[0]);
				int argumentLength = arguments.length;
				int repeatNumberOfTimes = 0;
				setNumber(number);

				if (argumentLength > 1) {
					repeatNumberOfTimes = Integer.parseInt(text.split(" ")[1]);
				}

				ArrayList<String> filters = addFilters(text, argumentLength);
				switch (argumentLength) {
					case 1 -> {
						if (number >= 1) {
							printProperties(callProperties(number));
						} else if (number == 0) {
							isDone = true;
						} else {
							System.out.println("The first parameter should be a natural number or zero.");
						}
					}

					case 2 -> {
						if (repeatNumberOfTimes >= 1) {
							printProperties(repeatNumberOfTimes);
						} else {
							System.out.println("The second parameter should be a natural number");
						}
					}

					default -> {
						if (propertyChecker(filters) && checkExclusivity(filters)) {
							printProperties(filters, repeatNumberOfTimes);
						}
					}
				}
			} catch (NumberFormatException nfe) {
				if (text.equals("")) {
					printRequests();
				} else {
					System.out.println("Invalid input");
				}
			}
		}
		System.out.println("\nGoodbye!");
	}

	private static ArrayList<String> addFilters(String text, int length) {
		ArrayList<String> filters = new ArrayList<>();

		for (int i = length; i > 2; i--) {
			filters.add(0, text.split(" ")[i - 1].toLowerCase());
		}
		return filters;
	}

	static HashMap<String, Boolean> assignProperties(long number) {
		ArrayList<Boolean> propertyCondition = callProperties(number);
		HashMap<String, Boolean> properties = new HashMap<>();

		for (Properties property: Properties.values()) {
			properties.put(property.getName(), propertyCondition.get(property.getIndex()));
		}

		return properties;
	}
}
