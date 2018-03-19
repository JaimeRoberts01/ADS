public class WordProcessor2 {

	public static void main(String[] args) {

		BSTBag<String> bag = new BSTBag<String>();
		BSTBag<String> bag2 = new BSTBag<String>();
		BSTBag<String> bag3 = new BSTBag<String>();
			
		bag.add("Jemma");
		bag.add("Alison");
		bag.add("Tanya");
		bag.add("Lyndsay");
		bag.add("Hayley");
		
		bag2.add("Steven");
		bag2.add("Barry");
		bag2.add("David");
		bag2.add("Matthew");
		bag2.add("Christopher");
		//bag2.add("Steven");
		
		bag3.add("Jemma");
		bag3.add("Alison");
		bag3.add("Tanya");
		bag3.add("Lyndsay");
		bag3.add("Hayley");
		
		
		System.out.println("Test " + bag2.contains("Steven"));
		
		bag2.remove("Steven");
		
		System.out.println("Size " + bag.size());
		System.out.println("Matthew " + bag2.contains("Matthew"));
		System.err.println("Jemma " + bag2.contains("Jemma"));
		System.err.println("EqualsF " + bag.equals(bag2));
		System.out.println("EqualsT " + bag.equals(bag3));
		System.err.println("Contains " + bag2.contains("Steven"));

	}
}







