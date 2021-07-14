package demo;

import java.util.Arrays;

public class TryFeatureStreams {

	public static void main(String[] args) {

		tryBasic();
	}
	
	private static void tryBasic() {
		Arrays.asList("029", "52", "913", "472").stream()
			.filter(e -> { return Integer.parseInt(e) < 500; })
			.map(e -> {return String.format("(%s)", e);})
			.forEach(System.out::print);
			
	}

}
