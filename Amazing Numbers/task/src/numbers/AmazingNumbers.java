package numbers;

import java.util.*;

import static numbers.CheckProperty.*;
import static numbers.Printing.*;

public class AmazingNumbers {
	private static ArrayList<String> propertyList = new ArrayList<>();

	public static void start() {
		Scanner scanner = new Scanner(System.in);

		addToPropertyList();
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
				setNumber(number);
				int length = arguments.length;
				int times = 0;

				if (length > 1) {
					times = Integer.parseInt(text.split(" ")[1]);
				}
				ArrayList<String> filters = addFilters(text, length);
				switch (length) {
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
						if (times >= 1) {
							printProperties(times);
						} else {
							System.out.println("The second parameter should be a natural number");
						}
					}

					case 3 -> {
						if (propertyChecker(propertyList, filters)) {
							printProperties(filters, times);
						}
					}

					case 4 -> {
						if (propertyChecker(propertyList, filters) && checkExclusivity(filters)) {
							printProperties(filters, times);
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

		switch (length) {
			case 5: filters.add(0, text.split(" ")[4].toLowerCase());
			case 4: filters.add(0, text.split(" ")[3].toLowerCase());
			case 3: filters.add(0, text.split(" ")[2].toLowerCase());
		}

		return filters;
	}

	private static void addToPropertyList() {
		String[] properties = {"\t   buzz", "\t   duck", "palindromic", "\t gapful", "\t\tspy",
				"\t square", "\t  sunny", "\t   even", "\t    odd"};
		for (String property: properties) {
			propertyList.add(property);
		}

		setPropertyList(propertyList);
	}

}
