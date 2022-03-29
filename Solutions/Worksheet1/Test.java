// Umut Geyik - 120200145 - CMPE 261.01/0102
package worksheet01;

public class Test {

	public static void main(String[] args) {
		Company comp1 = new Company("Kleimo", "Tech", 30, 0.75074);
		Company comp2 = new Company("Maryland Special Hospital", "Health", 120, 1.11213);
		Company comp3 = new Company("Los Santos Customs #1", "Mechanic", 7, 3.9064);
		Company comp4 = new Company("Eyupsultan Bros. Co.", "Food", 45, 1.704932);
		Company comp5 = new Company("Kleimo", "Food", 11, 0.85878);
		
		System.out.println(comp3.toString());
		
		System.out.println(comp1.logistic(5000, true));
		System.out.println(comp2.logistic(125, false));
		System.out.println(comp3.CEOofComp(comp3, "Hayri", "Blackwood", 35, 12));
		System.out.println(comp4.profit(comp4, 50000.50));
		System.out.println(comp5.nameCheck(comp5, comp1));
		
		// stockHack method will run if the requirement is met
		System.out.println(comp4.CEOofComp(comp4, "Kadir", "Sultan", 22, 3));
		System.out.println(comp4.getStockValue());
		
	}
}
