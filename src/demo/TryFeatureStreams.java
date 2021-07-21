package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import demo.model.Name;
import demo.model.Point;

public class TryFeatureStreams {

	public static void main(String[] args) {

		List<Runnable> list = new ArrayList<Runnable>();
		list.add(TryFeatureStreams::tryBasic);
		list.add(TryFeatureStreams::tryFilter);
		list.add(TryFeatureStreams::tryMap);
		list.add(TryFeatureStreams::tryPeek);
		list.add(TryFeatureStreams::tryDistinct);
		list.add(TryFeatureStreams::tryReduce);
		list.add(TryFeatureStreams::trySorted);
		list.add(TryFeatureStreams::trySortedList);
		list.add(TryFeatureStreams::tryCollect);
		list.add(TryFeatureStreams::tryMin);		
		list.add(TryFeatureStreams::tryMax);
		list.add(TryFeatureStreams::tryFindFirst);		
		list.add(TryFeatureStreams::tryFindAny);	
		list.add(TryFeatureStreams::tryCount);	
		list.add(TryFeatureStreams::tryStreamOf);	
		list.add(TryFeatureStreams::tryIntStream);	
		list.add(TryFeatureStreams::tryIntStreamFilter);	
		list.add(TryFeatureStreams::tryFlatMap);	
		list.add(TryFeatureStreams::tryAnyMatch);	
		list.add(TryFeatureStreams::tryAllMatch);
		list.add(TryFeatureStreams::tryNoneMatch);
		list.add(TryFeatureStreams::tryOptionalInit);
		list.add(TryFeatureStreams::tryOptional);
		list.add(TryFeatureStreams::tryOptionalStream);
		list.add(TryFeatureStreams::tryIntStreamSort);
		list.add(TryFeatureStreams::tryCollectGroupBy);
		list.add(TryFeatureStreams::tryCollectGroupByAndCounting);
		list.add(TryFeatureStreams::tryCollectJoining);
		list.add(TryFeatureStreams::tryCollectPartitionBy);
		list.add(TryFeatureStreams::tryStreamOnClose);
		list.add(TryFeatureStreams::tryStreamToArray);
		list.add(TryFeatureStreams::trySequential);
		list.add(TryFeatureStreams::tryParallel);
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
	
	private static void tryPeek() {
		
		Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid").stream()
			.peek(System.out::print)
			.forEach(System.out::print);
		System.out.println();
	}
	
	private static void tryReduce() {
		
		System.out.println("******* TryReduce *******");
		
		{
			String result = Arrays.asList("Warrior", "Rogue").stream()
				.reduce("*", (a, b) -> a + b);
			System.out.println(result);
		}
		{
			String result = Arrays.asList("Warrior", "Rogue").stream()
				.parallel()
				.reduce("*", (a, b) -> a + b);
			System.out.println(result);
		}
		{
			Optional<String> result = Arrays.asList("Warrior", "Rogue").stream()
				.reduce((a, b) -> a + b);
			System.out.println(result.orElse(null));
		}
		{
			Optional<String> result = Arrays.asList("Warrior", "Rogue").stream()
				.reduce(String::concat);
			System.out.println(result.orElse(null));
		}
		{
			Integer result = Arrays.asList(3, 5, 2).stream()
				.reduce(3, (a, b) -> a + b);
			System.out.println(result);
		}
		{
			Integer result = Arrays.asList(3, 5, 2).stream().parallel()
				.reduce(3, Integer::sum);
			System.out.println(result);
		}
		{
			Integer result = Arrays.asList(3, 5, 2).stream()
				.reduce(3, (acc, i) -> acc + i, (sub1, sub2) -> sub1 + sub2);
			System.out.println(result);
		}
	}
	
	private static void tryDistinct() {
		
		System.out.println("******* TryDistinct *******");
		
		Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
			.distinct()
			.forEach(System.out::print);
		System.out.println();
	}
	
	private static void trySorted() {
		
		System.out.println("******* TrySorted *******");
		
		{
			Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
				.sorted()
				.forEach(System.out::print);
			System.out.println();
		}
		
		{
			Supplier<Stream<Point>> supplier = () -> Stream.of(new Point(24, 76), new Point(57, -81), new Point(28, 11));
			supplier.get().sorted(Comparator.comparing(Point::x)).forEach(System.out::print);
			System.out.println();
		}
		
		{
			Supplier<Stream<Point>> supplier = () -> Stream.of(new Point(24, 76), new Point(57, -81), new Point(28, 11));
			supplier.get().sorted((p1, p2) -> Integer.compare(p1.y(), p2.y())).forEach(System.out::print);
			System.out.println();
		}
		{
			Supplier<Stream<Name>> supplier = () -> Stream.of(new Name("Noreen", "Tamra"), new Name("Gladwyn", "Rodge"), new Name("Theodora", "Drake"), new Name("Buster", "Wil"));
			supplier.get().sorted().forEach(System.out::print);
			System.out.println();
		}
		
		System.out.println();
	}
	
	private static void trySortedList() {
		
		System.out.println("******* TrySortedList *******");
		
		{
			List<String> list = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior");
			Collections.sort(list);
			System.out.println(list);
		}
		
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(28, 11));
			Collections.sort(list, Comparator.comparing(Point::x));
			System.out.println(list);
		}
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(28, 11));
			list.sort(Comparator.comparing(Point::x));
			System.out.println(list);
		}
		
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(28, 11));
			Collections.sort(list, (p1, p2) -> Integer.compare(p1.y(), p2.y()));
			System.out.println(list);
		}
		{
			List<Name> list = Arrays.asList(new Name("Noreen", "Tamra"), new Name("Gladwyn", "Rodge"), new Name("Theodora", "Drake"), new Name("Buster", "Wil"));
			Collections.sort(list);
			System.out.println(list);
		}

		
		System.out.println();
	}
	
	private static void tryCollect() {
		System.out.println("******* TryCollect ******* ");
		
		List<String> list = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
			.collect(Collectors.toList());
		System.out.println(list);
		
		Set<String> set = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
				.collect(Collectors.toSet());
		System.out.println(set);
		
		Map<String, Integer> map = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
				.collect(Collectors.toMap(Function.identity(), e -> e.length(), (val1, val2) -> val1 + val2));
		System.out.println(map);
		
		TreeSet<String> treeset = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid", "Warrior").stream()
				.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(treeset);
		
		double average = Arrays.asList(new Point(24, 76), new Point(58, -81), new Point(32, 11)).stream()
				.collect(Collectors.averagingDouble(p -> p.x()));
		System.out.println("Avg: " + average);
		
		System.out.println();
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
	
	private static void tryIntStream() {

		Supplier<IntStream> supplier = () -> IntStream.of(48, 23, 22, 2, 99, 11);
		
		System.out.println("Sum : " + supplier.get().sum());
		System.out.println("Min : " + supplier.get().min().getAsInt());
		System.out.println("Max : " + supplier.get().max().getAsInt());
		System.out.println("Avg : " + supplier.get().average().getAsDouble());
		System.out.println("Statistics : " + supplier.get().summaryStatistics());
		
	}
	
	private static void tryIntStreamSort() {
		System.out.println("TryIntStreamSort");
		Supplier<IntStream> supplier = () -> IntStream.of(48, 23, 22, 2, 99, 11, 57);
		supplier.get().sorted().forEach(i -> System.out.print(String.format("%s, ", i)));
		
	}
	
	private static void tryIntStreamFilter() {

		Supplier<IntStream> supplier = () -> IntStream.of(48, 23, 22, 2, 99, 11);
		
		IntPredicate ipredicate = (x) -> { return x < 32; };  

		System.out.println("Filtered : " + supplier.get().filter(ipredicate).boxed().collect(Collectors.toList()));
	}

	private static void tryFlatMap() {
		System.out.println("Try Flat Map");
		List<String> list1 = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid");
		List<String> list2 = Arrays.asList("Dagger", "Staff", "Javelin", "Broadsword", "Rifle");
		Stream<List<String>> combined = Stream.of(list1, list2);
		combined.flatMap(names -> names.stream()).forEach(e -> System.out.print(String.format("%s, ", e)));
		System.out.println("");
	}
	
	private static void tryAnyMatch() {
		System.out.println("Try Any Match");
		List<String> list = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid");
		boolean match = list.stream().anyMatch(e -> e.startsWith("P"));
		System.out.println("Match(Starts with P) : " + match);
		match = list.stream().anyMatch(e -> e.startsWith("E"));
		System.out.println("Match(Starts with E) : " + match);
	}
	
	private static void tryAllMatch() {
		System.out.println("Try All Match");
		List<String> list = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid");
		boolean match = list.stream().allMatch(e -> Character.isUpperCase(e.charAt(0)));
		System.out.println("All Match : " + match);
	}

	private static void tryNoneMatch() {
		System.out.println("Try None Match");
		List<String> list = Arrays.asList("Warrior", "Rogue", "Priest", "Wizard", "Druid");
		boolean match = list.stream().noneMatch(e -> Character.isUpperCase(e.charAt(0)));
		System.out.println("None Match(Starts with UpperCase) : " + match);
		match = list.stream().noneMatch(e -> Character.isLowerCase(e.charAt(0)));
		System.out.println("None Match(Starts with LowerCase) : " + match);
	}

	
	private static void tryOptionalInit() {
		{
			Optional<String> optional = Optional.empty();
			System.out.println("Optional : " + optional);
		}
		{
			Optional<String> optional = Optional.of("example");
			System.out.println("Optional : " + optional);
		}
		{
			Optional<String> optional = Optional.ofNullable(null);
			System.out.println("Optional : " + optional);
		}
	}

	private static void tryOptional() {
		
		Optional<String> optional = Optional.of("example");
		System.out.println("Optional.isEmpty : " + optional.isEmpty());
		System.out.println("Optional : " + optional.isPresent());
		System.out.println("Optional : " + optional.get());
		System.out.println("Optional : " + optional.orElse(null));
		System.out.println("Optional : " + optional.orElseGet(() -> "supplied"));
		Consumer<String> c = (e) -> System.out.println("Present->" + e);
		optional.ifPresent(c);
	}
	
	private static void tryOptionalStream() {
		
		Optional<String> optional = Optional.of("example");
		optional.stream().forEach(System.out::println);
		
		{
			Stream<Optional<String>> stream = Stream.of(Optional.of("1"), Optional.empty(), 
				    Optional.of("2"), Optional.empty(), Optional.of("3"));
			
			List<String> list = stream
				    .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
				    .collect(Collectors.toList());
			System.out.println(list);
		}
		
		{
			Stream<Optional<String>> stream = Stream.of(Optional.of("1"), Optional.empty(), 
				    Optional.of("2"), Optional.empty(), Optional.of("3"));
			
			List<String> list = stream 
				    .flatMap(Optional::stream)
				    .collect(Collectors.toList());

			System.out.println(list);
		}
		System.out.println();
	}
	
	private static void tryCollectGroupBy() {
		System.out.println("******* TryGroupBy *******");
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(24, 11), new Point(57, 3), new Point(85, 78));
			Map<Integer, List<Point>> classification = list.stream().collect(Collectors.groupingBy(Point::x));
			System.out.println(classification);
			System.out.println(classification.get(24));
		}
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(24, 11), new Point(57, 3), new Point(85, 78));
			Map<Integer, Set<Point>> classification = list.stream().collect(Collectors.groupingBy(point -> point.x(), 
							Collectors.toCollection(() -> new TreeSet<Point>(Comparator.comparing(Point::x).thenComparing(Point::y)))));
			System.out.println(classification);
			System.out.println(classification.get(24));
		}
		System.out.println();
	}
	
	private static void tryCollectGroupByAndCounting() {
		System.out.println("******* TryCollectGroupByAndCounting *******");
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(24, 11), new Point(57, 3), new Point(85, 78));
			Map<Integer, Long> classification = list.stream().collect(Collectors.groupingBy(Point::x, Collectors.counting()));
			System.out.println(classification);
			System.out.println("Point with X=24 has " + classification.get(24) + " instances.");
		}
		System.out.println();
	}
	
	private static void tryCollectJoining() {
		System.out.println("******* TryCollectJoining *******");
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(24, 11), new Point(57, 3), new Point(85, 78));
			String serial = list.stream().map(point -> String.valueOf(point.x())).collect(Collectors.joining("-"));
			System.out.println(serial);
		}
		System.out.println();
	}
	
	private static void tryCollectPartitionBy() {
		System.out.println("******* TryCollectPartitionBy *******");
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(24, 11), new Point(57, 3), new Point(85, 78));
			Map<Boolean, List<Point>> result = list.stream().collect(Collectors.partitioningBy(point -> point.x() < 58));
			System.out.println(result);
		}
		System.out.println();
	}
	
	private static void tryStreamOnClose() {
		System.out.println("******* TryStreamOnClose *******");
		
		Supplier<IntStream> supplier = () -> IntStream.of(48, 23, 22, 2, 99, 11);
		IntStream stream = supplier.get();
		stream.onClose(() -> System.out.println("Closing Stream now."));
		stream.forEach(System.out::print);
		
		System.out.println();
		stream.close();
		
		System.out.println();
	}
	
	private static void tryStreamToArray() {
		System.out.println("******* TryStreamToArray *******");
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(24, 11), new Point(57, 3), new Point(85, 78));
			Point[] result = list.stream().toArray(Point[]::new);
			System.out.println(result);
		}
		{
			List<Point> list = Arrays.asList(new Point(24, 76), new Point(57, -81), new Point(24, 11), new Point(57, 3), new Point(85, 78));
			Point[] result = list.stream().toArray(size -> new Point[size]);
			System.out.println(result);
		}
		System.out.println();
	}
	
	private static void trySequential() {
		System.out.println("******* TrySequential *******");
		Arrays.asList(1, 2, 3, 4, 5, 6, 7).stream().forEach(System.out::print);
		System.out.println();
	}
	
	private static void tryParallel() {
		System.out.println("******* TryParallel *******");
		Arrays.asList(1, 2, 3, 4, 5, 6, 7).stream().parallel().forEach(System.out::print);
		System.out.println();
		Arrays.asList(1, 2, 3, 4, 5, 6, 7).parallelStream().forEach(System.out::print);
		System.out.println();
	}
}
