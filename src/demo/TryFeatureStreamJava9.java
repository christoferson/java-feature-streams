package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TryFeatureStreamJava9 {

	public static void main(String[] args) {
		List<Runnable> list = new ArrayList<Runnable>();
		list.add(TryFeatureStreamJava9::tryCollectorsFiltering);
		list.add(TryFeatureStreamJava9::tryCollectorsFlatMapping);

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
	
	
	public static void tryCollectorsFlatMapping() {
	    Blog blog1 = new Blog("1", "Nice", "Very Nice");
	    Blog blog2 = new Blog("2", "Disappointing", "Ok", "Could be better");
	    List<Blog> blogs = List.of(blog1, blog2);
	        
	    Map<String,  List<List<String>>> authorComments1 = blogs.stream()
	     .collect(Collectors.groupingBy(Blog::getAuthorName, 
	       Collectors.mapping(Blog::getComments, Collectors.toList())));
	       
	    System.out.println(authorComments1);
	    
//	    assertEquals(2, authorComments1.size());
//	    assertEquals(2, authorComments1.get("1").get(0).size());
//	    assertEquals(3, authorComments1.get("2").get(0).size());

	    Map<String, List<String>> authorComments2 = blogs.stream()
	      .collect(Collectors.groupingBy(Blog::getAuthorName, 
	        Collectors.flatMapping(blog -> blog.getComments().stream(), 
	        Collectors.toList())));

//	    assertEquals(2, authorComments2.size());
//	    assertEquals(2, authorComments2.get("1").size());
//	    assertEquals(3, authorComments2.get("2").size());
	    
	    System.out.println(authorComments2);
	}
	
	static class Blog {
	    private String authorName;
	    private List<String> comments;
		
	    
	    
	    public Blog(String authorName, List<String> comments) {
			super();
			this.authorName = authorName;
			this.comments = comments;
		}
	    
	    public Blog(String authorName, String ... comments) {
			super();
			this.authorName = authorName;
			this.comments = Arrays.asList(comments);
		}

	    
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		public List<String> getComments() {
			return comments;
		}
		public void setComments(List<String> comments) {
			this.comments = comments;
		}
	      
	   
	}

}
