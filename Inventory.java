import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Inventory {

	/**
	 * @param args
	 */
	File inventoryFile;
	//new array list for my items in the store
	ArrayList<Item> kahiniStore = new ArrayList<Item>();
	
	//f is the variable that you are taking in when you make a new inventory object
	public Inventory(File f) throws FileNotFoundException{
		this.inventoryFile= f;
		
		parseFile();
		
	}//end of constructor

	//method that reads the file and stores the variables for the  inventory
	public void parseFile() throws FileNotFoundException{
		//all the types of possible inputs in the inventory and their data types
		String ID;
		String description;
		int numStock;
		double price;
		int numSubAssembly;
		String subAssemblyID;
		//array list for the qty for sub assembly  and names of the sub assemblies
		ArrayList<Integer> qtySubAssembly = new ArrayList<Integer>();
		ArrayList<String> subAssemblyNames = new ArrayList<String>();
		
		Scanner input= new Scanner(this.inventoryFile);
		while(input.hasNextLine()){
			System.out.println(input.nextLine());
		}
	}	
}//end of Inventory class
