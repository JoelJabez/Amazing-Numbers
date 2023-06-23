package numbers;

public enum Properties {
		EVEN("\t   ", "even", "odd", Count.counter),
		ODD("\t\t", "odd", "even", Count.counter),
		BUZZ("\t   ", "buzz", null, Count.counter),
		DUCK("\t   ", "duck", "spy", Count.counter),
		PALINDROMIC("", "palindromic", null, Count.counter),
		GAPFUL("\t ", "gapful", null, Count.counter),
		SPY("\t\t", "spy", "duck", Count.counter),
		SQUARE("\t ", "square", "sunny", Count.counter),
		SUNNY("\t  ", "sunny", "square", Count.counter),
		JUMPING("\t", "jumping", null, Count.counter),
		HAPPY("\t  ", "happy", "sad", Count.counter),
		SAD("\t\t", "sad", "happy", Count.counter);

		private final String format;
		private final String name;
		private final String notPresent;
		private final String mutuallyExclusiveTo;
		private final int index;

		Properties(String format, String name, String mutuallyExclusiveTo, int index) {
			this.format = format;
			this.name = name;
			this.notPresent = "-" + name;
			this.mutuallyExclusiveTo = mutuallyExclusiveTo;
			this.index = index;
			Count.counter++;
		}


		public String getFormat() {
			return format;
		}

		public String getName() {
			return name;
		}

		public String getNotPresent() {
			return notPresent;
		}

		public String getMutuallyExclusiveTo() {
			return mutuallyExclusiveTo;
		}

		public int getIndex() {
			return index;
		}
}

class Count {
	static int counter = 0;
}