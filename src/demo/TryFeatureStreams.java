package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import demo.model.Point;

public class TryFeatureStreams {

	public static void main(String[] args) {

		List<Runnable> list = new ArrayList<Runnable>();
		list.add(TryFeatureStreams::tryBasic);
		list.add(TryFeatureStreams::tryFilter);
		list.add(TryFeatureStreams::tryMap);
		list.add(TryFeatureStreams::tryPeak);
		list.add(TryFeatureStreams::tryDistinct);
		list.add(TryFeatureStreams::trySorted);
		list.add(TryFeatureStreams::tryCollect);
		list.add(TryFeatureStreams::tryMin);		
		list.add(TryFeatureStreams::tryMax);
		list.add(TryFeatureStreams::tryFindFirst);		
		list.add(TryFeatureStreams::tryFindAny);	
		list.add(TryFeatureStreams::tryCount);	
		list.add(TryFeatureStreams::tryStreamOf);	
		
		for (var r : list) {
			r.run();
		}
		
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
	
	private static void tryCollect() {
		
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
	
	
	private static void tryMin() {
		
		int min = Arrays.asList("3", "45", "223", "54", "22", "33").stream()
			.mapToInt(Integer::parseInt)
			.min()
			.orElseThrow();
		System.out.println("Min:" + min);
	}

	private static void tryMax() {
		
		int max = Arrays.asList("3", "45", "223", "54", "22", "33").stream()
			.mapToInt(Integer::parseInt)
			.max()
			.orElseThrow();
		System.out.println("Max:" + max);
	}
	
	private static void tryFindFirst() {
		
		String first = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
			.findFirst().orElse(null);
		System.out.println("First : " + first);
	}
	
	private static void tryFindAny() {
		
		String any = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
			.findAny().orElse(null);
		System.out.println("Any : " + any);
	}
	
	private static void tryCount() {
		
		long count = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream().count();
		System.out.println("Count : " + count);
	}
	
	
	private static void tryStreamOf() {
		
		Supplier<Stream<Point>> supplier = () -> Stream.of(new Point(24, 76), new Point(57, -81), new Point(28, 11));

		Stream<Integer> xValueStream = supplier.get().map(Point::x);
		Stream<Integer> yValueStream = supplier.get().map(Point::y);

		System.out.println("X Values : " + xValueStream.collect(Collectors.toList()));
		System.out.println("Y Values : " + yValueStream.collect(Collectors.toList()));
	}

}
