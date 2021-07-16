package demo.model;

import java.util.Comparator;

public record Name(String first, String last) implements Comparable<Name> {

	@Override
	public int compareTo(Name o) {
		return Comparator.comparing(Name::first)
	              .thenComparing(Name::last)
	              .compare(this, o);
	}

	
	
}
