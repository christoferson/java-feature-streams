package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TryFeatureStreamJava9 {

	public static void main(String[] args) {
		List<Runnable> list = new ArrayList<Runnable>();
		list.add(TryFeatureStreamJava9::tryBasic);

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

}
