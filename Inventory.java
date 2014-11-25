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
		double price = 0.0;
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
			//using string tokenizer to turn each element within the | into a token
			int counter = 1;
			while(t.hasMoreTokens()){
				String token = t.nextToken().trim();
				switch(counter){
				//case for the ID associated with the item 
					case 1:
						ID= token;
						counter++;
						break;
				//description associated with the item
					case 2:
						description=token;
						counter++;
						break;
						//WORK ON CASE 3
				//Special case; could be missing in a file
					case 3:
						if (token.equals("")){
							System.out.println("Case 3 token is a space");
						}else{
							qtySubAssembly = parseSubQty(token);
							subAssemblyNames = parseSubID(token);
						}
						counter++;
						break;
				//Special case; could be missing in the file
					case 4:
						if (token.equals("")){
							counter=1;
							numStock=-1;
							price=-1;
							//adding a new item 
							Item currItem = new Item( ID, description, qtySubAssembly, subAssemblyNames,numStock, price);
							kahiniStore.add(currItem);
						}else{
							counter++;
							numStock= Integer.parseInt(token);
						}
						break;
				//Special Case; Could be missing in the file
					case 5:
						price=Double.parseDouble(token);
						counter=1;
						Item currItem = new Item( ID, description, qtySubAssembly, subAssemblyNames,numStock, price);
						kahiniStore.add(currItem);
						break;	
				}//end of switch statement 
			
				//System.out.println(token);
				
			}//end of inner while loop 
		}//end of outer while loop
	}//end of parse file 
	
	//getting the qty from the list of sub assembly 
	public ArrayList<Integer> parseSubQty(String token){
		StringTokenizer t= new StringTokenizer(token, ",x");
		int counter=1; 
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		while(t.hasMoreTokens()){
		String tok= t.nextToken().trim();
			switch(counter){
				case 1:
					temp.add(Integer.parseInt(tok));
					counter++;
					break;
				case 2:
					counter=1; 
					break;
			}//end of switch
		}//end of while
	
		/*for(int i =0; i < temp.size(); i++){
			System.out.println(temp.get(i));
		}*/
		return temp;
		
	}
	//getting the ID from the list of sub assemblies
	//make an arraylist of strings, that represents easch id of the sub assemblies (makes switch, add into the temp array list (string) , print out M, S1...
	public ArrayList<String> parseSubID(String token){
		
		return null;
		
	}
}//end of Inventory class
