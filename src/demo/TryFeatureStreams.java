package demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TryFeatureStreams {

	public static void main(String[] args) {

		tryBasic();
		
		tryFilter();
		
		tryMap();
		
		tryPeak();
		
		tryDistinct();
		
		trySorted();
		
		tryCollectList();
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
	
	private static void tryDistinct() {
		
		Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
			.distinct()
			.forEach(System.out::print);
		System.out.println();
	}
	
	private static void trySorted() {
		
		Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
			.sorted()
			.forEach(System.out::print);
		System.out.println();
	}
	
	private static void tryCollectList() {
		
		List<String> list = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
			.collect(Collectors.toList());
		System.out.println(list);
		
		Set<String> set = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
				.collect(Collectors.toSet());
		System.out.println(set);
		
		Map<String, Integer> map = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
				.collect(Collectors.toMap(Function.identity(), e -> e.length(), (val1, val2) -> val1 + val2));
		System.out.println(map);
	}
	

}
