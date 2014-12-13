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
		//updateStore();
		
	}//end of constructor
	
	//This is included only because i thought this was a part of the assignment, but once I found out it wasn't I just commented it out 
	//method to find the missing entries in the file
	/*public void updateStore() {
		//after the missing entries are found, methods in the if statements update it 
		for(int i=0; i< kahiniStore.size(); i++){
			//checking if the numStock from the file 
			if(kahiniStore.get(i).numStock==-1){
				generateStock();
				
			}
			if(kahiniStore.get(i).price==-1){
				generatePrice(i);
				
				
			}
			if(kahiniStore.get(i).qtySubAssembly.size()==0 || kahiniStore.get(i).subAssemblyID.size()==0){
				generateSubAssembly();
			}
		}//end of for
		for(int j=0; j< kahiniStore.size(); j++){
			System.out.println(kahiniStore.get(j).price);
			
		}
	}
	
	public void updateSub(ArrayList<String> sub) {
		//after the missing entries are found, methods in the if statements update it 
		for(int i=0; i< sub.size(); i++){
			//checking if the numStock from the file 
			for(int j=0; j<kahiniStore.size(); j++){
				
			}
		}
	}
	
	public void generateSubAssembly() {
		// TODO Auto-generated method stub
		
	}
	public void generatePrice(int index) {
		double price=0;
		for(int i=0; i<kahiniStore.get(index).qtySubAssembly.size(); i++){
			String ID= kahiniStore.get(index).subAssemblyID.get(i);
			for(int j=0; i< kahiniStore.size(); j++){
				if(kahiniStore.get(j).ID.equals(ID)){
				 if(kahiniStore.get(j).price==-1){
					 updateSub(kahiniStore.get(j).subAssemblyID); 
				 }else{
					 
					 price= price + (kahiniStore.get(j).price * kahiniStore.get(j).qtySubAssembly.get(i));
				 }
				}//end of outer if
			}//end of inner for
		}//end of outer for
		kahiniStore.get(index).price= price;
	}//end of generatePrice
	
	public void generateStock() {
		// TODO Auto-generated method stub
		
	}*/
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
							System.out.println("File is poorly formatted, apply new file" );
							System.exit(0);
						}else{
							qtySubAssembly = parseSubQty(token);
							subAssemblyNames = parseSubID(token);
						}
						counter++;
						break;
				//Special case; could be missing in the file
					case 4:
						if (token.equals("")){
							System.out.println("File is poorly formatted" );
							return;
							/*counter=1;
							numStock=-1;
							price=-1;
							//adding a new item 
							Item currItem = new Item( ID, description, qtySubAssembly, subAssemblyNames,numStock, price);
							kahiniStore.add(currItem);*/
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
						if(currItem.subAssemblyID.size()>=1 && currItem.subAssemblyID.size()<=5){
							int n = currItem.qtySubAssembly.size();
							double discount= (n*10)/100 * price;
							price=price-discount;	
						}
						if(currItem.subAssemblyID.size() >5){
							double discount= (.5*price);
							price=price-discount;
						}
						currItem.price = price;
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
	//make an arraylist of strings, that represents easch id of the sub assemblies (makes switch, add into the temp array list (string) , print out M, S1...)
	public ArrayList<String> parseSubID(String token){
		StringTokenizer t= new StringTokenizer(token, ",x");
		int counter=1;
		ArrayList<String> temp= new ArrayList<String>();
		
		while(t.hasMoreTokens()){
		String tok=t.nextToken().trim();
				switch(counter){
					case 1:
						counter++;						
						break;
					case 2:
						temp.add(tok);
						counter=1;
						break;
				}
		}
		/*for(int i=0; i<temp.size(); i++){
			System.out.println(temp.get(i));
		}*/
		return temp;
		
	}
}//end of Inventory class
