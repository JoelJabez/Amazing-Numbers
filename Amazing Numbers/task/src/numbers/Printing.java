package numbers;

import java.util.ArrayList;
import java.util.HashMap;

import static numbers.CheckProperty.callProperties;

public class Printing {
	private static ArrayList<String> propertyList;
	private static long number;

	static void setNumber(long number) {
		Printing.number = number;
	}

	static void setPropertyList(ArrayList<String> propertyList) {
		Printing.propertyList = propertyList;
	}

	static void printRequests() {
		System.out.println("Supported requests:");
		System.out.println("- enter a natural number to know its properties;");
		System.out.println("- enter two natural numbers to obtain the properties of the list:");
		System.out.println("  * the first parameter represents a starting number;");
		System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
		System.out.println("- two natural numbers and a property to search for;");
		System.out.println("- two natural numbers and two properties to search for;");
		System.out.println("- separate the parameters with one space;");
		System.out.println("- enter 0 to exit.");
	}

	private static HashMap<String, Boolean> assignProperties() {
		ArrayList<Boolean> propertyCondition = callProperties(number);

		HashMap<String, Boolean> properties = new HashMap<>();
		for (int i = 0; i < propertyCondition.size(); i++) {
			properties.put(propertyList.get(i).strip(), propertyCondition.get(i));
		}

		return properties;
	}

	static void printProperties(ArrayList<String> filters, int count) {

		while(count != 0) {
			HashMap<String, Boolean> properties = assignProperties();
			ArrayList<Boolean> condition = new ArrayList<>();
			for (String filter: filters) {
				condition.add(properties.get(filter));
			}
			if (!condition.contains(false)) {
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

	static void printProperties(int times) {
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

	static void printProperties(ArrayList<Boolean> propertyConditions) {
		System.out.printf("Properties of %,d\n", number);

		for (int i = 0; i < propertyConditions.size(); i++) {
			System.out.printf("%s: %b\n", propertyList.get(i), propertyConditions.get(i));
		}
	}
}
