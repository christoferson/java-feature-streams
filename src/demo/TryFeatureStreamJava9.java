package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TryFeatureStreamJava9 {

	public static void main(String[] args) {
		List<Runnable> list = new ArrayList<Runnable>();
		list.add(TryFeatureStreamJava9::tryCollectorsFiltering);

		for (var r : list) {
			r.run();
		}
	}
	
	private static void tryCollectorsFiltering() {

	    List<Integer> numbers = List.of(1, 2, 3, 5, 5, 7);

	    Map<Integer, Long> result = numbers.stream()
	      .filter(val -> val > 3)
	      .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

	    System.out.println(result);

	    result = numbers.stream()
	      .collect(Collectors.groupingBy(i -> i,
	        Collectors.filtering(val -> val > 3, Collectors.counting())));

	    System.out.println(result);
	    
	}

}
