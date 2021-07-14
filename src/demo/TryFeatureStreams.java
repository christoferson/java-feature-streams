package demo;

import java.util.Arrays;

public class TryFeatureStreams {

	public static void main(String[] args) {

		tryBasic();
		
		tryFilter();
		
		tryMap();
		
		tryPeak();
	}
	
	private static void tryBasic() {
		Arrays.asList("029", "52", "913", "472").stream()
			.filter(e -> Integer.parseInt(e) < 500 )
			.map(e -> String.format("(%s)", e))
			.forEach(System.out::print);
		System.out.println();
	}
	
	private static void tryFilter() {
		
		Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid").stream()
			.filter(e -> e.startsWith("W"))
			.forEach(System.out::print);
		System.out.println();
	}
	
	private static void tryMap() {
		
		Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid").stream()
			.map(String::toUpperCase)
			.forEach(System.out::print);
		System.out.println();
	}
	
	private static void tryPeak() {
		
		Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid").stream()
			.peek(System.out::print)
			.forEach(System.out::print);
		System.out.println();
	}

}
