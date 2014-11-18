import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


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
		String ID = null;
		String description = null;
		int numStock = 0;
		double price;
		int numSubAssembly;
		String subAssemblyID;
		//array list for the qty for sub assembly  and names of the sub assemblies
		ArrayList<Integer> qtySubAssembly = new ArrayList<Integer>();
		ArrayList<String> subAssemblyNames = new ArrayList<String>();
		
		Scanner input= new Scanner(this.inventoryFile);
		//looking for the spaces in the inventory file and printing it 
		while(input.hasNextLine()){
			String line = input.nextLine();
			
			//ignoring the | and printing each item on a new line from the file 
			StringTokenizer t = new StringTokenizer(line, "|");
			while(t.hasMoreTokens()){
				String token = t.nextToken().trim();
				int counter = 1;
				switch(counter){
					case 1:
						ID= token;
						break;
					case 2:
						description=token;
						break;
						//WORK ON CASE 3
					case 3:
						break;
					case 4:
						numStock= Integer.parseInt(token);
						break;
					case 5:
						price=Double.parseDouble(token);
					counter=1;
					Item currItem = new Item( ID, description, qtySubAssembly, subAssemblyNames,numStock, price);
					kahiniStore.add(currItem);
						
						
				}
				System.out.println(token);
				
			}
		}
	}	
}//end of Inventory class
