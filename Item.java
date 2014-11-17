import java.util.ArrayList;


public class Item {
	//adding class variables(variables that define the Item class) because you can't access constructor parameters within a class
	 String ID;
	 String description;
	 ArrayList<Integer> qtySubAssembly;
	 ArrayList<String> subAssemblyID;
	 int numStock;
	 double price;
	 
	public Item(String ID, String description, ArrayList<Integer> qtySubAssembly, ArrayList<String> subAssemblyID, 
			int numStock, double price){
		//updating the class variables(empty) with the items that were inputed (parameter variables)
		this.ID = ID;
		this.description = description; 
		this.qtySubAssembly = qtySubAssembly;
		this.subAssemblyID= subAssemblyID;
		this.numStock= numStock;
		this.price=price;
	}//end of constructor
	
}//end of class
