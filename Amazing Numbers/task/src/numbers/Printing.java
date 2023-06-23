package numbers;

import java.util.ArrayList;
import java.util.HashMap;

import static numbers.AmazingNumbers.assignProperties;

public class Printing {
	private static long number;

	static void setNumber(long number) {
		Printing.number = number;
	}

	static void printRequests() {
		System.out.println("Supported requests:");
		System.out.println("- enter a natural number to know its properties;");
		System.out.println("- enter two natural numbers to obtain the properties of the list:");
		System.out.println("  * the first parameter represents a starting number;");
		System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
		System.out.println("- two natural numbers and properties to search for;");
		System.out.println("- a property preceded by minus must not be present in numbers;");
		System.out.println("- separate the parameters with one space;");
		System.out.println("- enter 0 to exit.");
	}

	static void printProperties(ArrayList<String> filters, int count) {

		while (count != 0) {
			HashMap<String, Boolean> properties = assignProperties(number);
			ArrayList<Boolean> condition = new ArrayList<>();
			for (String filter : filters) {
				for (Properties property : Properties.values()) {
					if (filter.contains(property.getNotPresent())) {
						condition.add(!properties.get(property.getName()));
					} else if (filter.contains(property.getName())) {
						condition.add(properties.get(filter));
					}
				}
			}

			if (!condition.contains(false)) {
				System.out.printf("%d is ", number);
				int counter = 0;
				for (Properties property : Properties.values()) {
					if (properties.get(property.getName())) {
						if (counter == 0) {
							System.out.print(property.getName());
						} else {
							System.out.printf(", %s", property.getName());
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

	static void printProperties(int times) {
		System.out.println();

		long max = number + times;
		for (long i = number; i < max; i++) {
			System.out.printf("%d is ", i);
			number = i;
			HashMap<String, Boolean> properties = assignProperties(number);
			int counter = 0;
			for (Properties property: Properties.values()) {
				if (properties.get(property.getName())) {
					if (counter == 0) {
						System.out.print(property.getName());
					} else {
						System.out.printf(", %s", property.getName());
					}
					counter++;
				}
			}
			System.out.println();
		}
	}

	static void printProperties(ArrayList<Boolean> propertyConditions) {
		System.out.printf("Properties of %,d\n", number);

		for (Properties properties: Properties.values()) {
			System.out.printf("%s%s: %b\n", properties.getFormat(), properties.getName(),
					propertyConditions.get(properties.getIndex()));
		}
	}
}
