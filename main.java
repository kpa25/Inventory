import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class main {
	static Inventory I = null;
	static ArrayList<Item> receipt = new ArrayList<Item>();
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//System.out.println("reading the file now");

		String f = args[0];
		File file = new File(f);
		I = new Inventory(file);

		System.out.println("Options Menu");
		System.out.println("------------");
		System.out.println("Press 1 to ADD an item");
		System.out.println("Press 2 to REMOVE an item");
		System.out.println("Press 3 to ADD a quantity");
		System.out.println("Press 4 to REMOVE a quantity");
		System.out.println("Press 5 to enter POS mode");
		System.out.println("Press 6 to PRINT Recipt");
		System.out.println("Press q to QUIT the program");
		System.out.println("------------");

		Scanner in = new Scanner(System.in);
		while (true) {
		//System.out.println(I.kahiniStore.size());
			String input = in.next();

			// stops the program when the user enters q
			if (input.equals("q")) {
				return;
			} else if (input.equals("1")) {
				addItem();
			} else if (input.equals("2")) {
				removeItem();

			} else if (input.equals("3")) {
				addQty();
			} else if (input.equals("4")) {
				removeQty();
			}else if (input.equals("5")){
				posMode();
				createReceipt();
			}else if (input.equals("6")){
				System.out.println("Here is a simple receipt, the final receipt is printed to a text file named: receipt.txt");
				for(int i=0; i< receipt.size(); i++){
					System.out.println("You bought: " + receipt.get(i).numBought + " " + receipt.get(i).description);
				}
			} else {
				System.out.println("The input you entered is not valid. Try again.");
				continue;
			}

		}// end of while loop

	}
	
	public static void createReceipt() throws IOException {
		BufferedWriter writer = null;
		//creating the file receipt.txt
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Receipt.txt")));
		DateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy H:mm:ss");
		Date date = new Date();
		writer.write(dateFormat.format(date));
		writer.newLine();
		double finalTotal=0;
		for(int i=0; i< receipt.size(); i++){
			double itemTotal=receipt.get(i).numBought*receipt.get(i).price;
			finalTotal= itemTotal + finalTotal;
			writer.write(receipt.get(i).numBought + " " + receipt.get(i).description + " @ " + receipt.get(i).price + "       " + itemTotal); 
			writer.newLine();
		}
		writer.newLine();
		writer.write("-----------------------");
		writer.newLine();
		writer.write("TOTAL:      " + "$" +finalTotal);
		writer.close();
		
	}
	//method that enters the POS mode, displays the menu and asks the user what they want to purchase, while adding it to the recepit
	public static void posMode() {
		System.out.println("Here is the menu:");
		
		for(int i=0; i< I.kahiniStore.size();  i++){
			System.out.println(i+ " " + I.kahiniStore.get(i).description + " " + I.kahiniStore.get(i).price);
			
		}
		
		Scanner in =new Scanner(System.in);
		while(true) {
			//storing the items/qty bought
			System.out.println("Enter the item number you want to purchase, or q");
			String input = in.next();
			if(input.equals("q") || input.equals("")){
				System.out.println("Press 6 to print simple receipt");
				return;
			}
			int itemNum =Integer.parseInt(input);
			if(itemNum > I.kahiniStore.size()-1){
				System.out.println("The input you entered is invalid, Please refer back to the options menu and pick a new option.");
				return;
			}
			System.out.println("Enter the quantity of items you want to purchase or q.");
			input = in.next();
			if(input.equals("") || input.equals("q")){
				System.out.println("Press 6 to print simple receipt");
				return;
			}
			int qtyBought =Integer.parseInt(input);
			
			//checking to see that the qty wanted by the user is not 
			if(qtyBought>I.kahiniStore.get(itemNum).numStock){
				System.out.println("The number you ordered is greater than our stock. Please ");
				return;
			}
			
			I.kahiniStore.get(itemNum).numStock = I.kahiniStore.get(itemNum).numStock - qtyBought; 
			//removing qty in stock based on qty bought
			for(int i=0; i<I.kahiniStore.get(itemNum).qtySubAssembly.size(); i++){
				String currSubID = I.kahiniStore.get(itemNum).subAssemblyID.get(i);
				for(int j=0; j< I.kahiniStore.size(); j++){
					//System.out.println("inside inner for " + I.kahiniStore.get(j).ID);
						 if(I.kahiniStore.get(j).ID.equals(currSubID)){
							// int num = I.kahiniStore.get(j).numStock; 
							
							 I.kahiniStore.get(j).numStock = I.kahiniStore.get(j).numStock - (I.kahiniStore.get(itemNum).qtySubAssembly.get(i) * qtyBought);		
							 break;
						 }//end of if
				}//end of inner for
			}//end of outer for
			Item temp=I.kahiniStore.get(itemNum);
			
			temp.numBought= qtyBought;
			receipt.add(temp);
		}
	}
	
	
	public static void addQty() {
		System.out.println("Enter ID of the item you want to add quantity of");
		Scanner in = new Scanner(System.in);
		String ID = in.next();
		System.out.println("What is the quantity you want to add?");
		int numAdd = Integer.parseInt(in.next());
		for (int i = 0; i < I.kahiniStore.size(); i++) {
			if (I.kahiniStore.get(i).ID.equals(ID)) {
				I.kahiniStore.get(i).numStock = I.kahiniStore.get(i).numStock
						+ numAdd;
				System.out.println("Quantity was Added");
				System.out.println("The stock for the item is now "
						+ I.kahiniStore.get(i).numStock);
				return;
			}
		}// end of for loop
		System.out.println("ID was not found in the stock");

	}

	public static void removeQty() {
		System.out.println("Enter ID of the item you want to remove quantity of");
		Scanner in = new Scanner(System.in);
		String ID = in.next();
		System.out
				.println("How much quantity of this ID do you want to remove?");
		int numRemove = Integer.parseInt(in.next());
		for (int i = 0; i < I.kahiniStore.size(); i++) {
			if (I.kahiniStore.get(i).ID.equals(ID)) {
				if (I.kahiniStore.get(i).numStock < numRemove) {
					System.out.println("The quantity you entered is higher than the stock in the inventory. Please enter a smaller number");
					return;
				} else {
					System.out.println("Quantity was removed");
					I.kahiniStore.get(i).numStock = I.kahiniStore.get(i).numStock- numRemove;
					System.out.println("The stock for the item is now " + I.kahiniStore.get(i).numStock);
					return;
				}
			}
		}// end of for loop
		System.out.println("ID was not found in the inventory");
	}

	public static void removeItem() {
		System.out.println("Enter ID of item you want to remove from Inventory");
		Scanner in = new Scanner(System.in);
		String ID = in.next();
		for (int i = 0; i < I.kahiniStore.size(); i++) {
			if (I.kahiniStore.get(i).ID.equals(ID)) {
				I.kahiniStore.remove(i);
				System.out.println("Item was removed.");
				return;
			}
		}// end of for loop
		System.out.println("Item cannot be removed becasue it is not in the inventory");
	}

	public static void addItem() {
		System.out.println("What is the ID of the item you want to add?");
		Scanner in = new Scanner(System.in);
		String ID = in.next();
		System.out.println("What is the descrption of the Item you want to add?");
		String description = in.next();
		ArrayList<Integer> qty = new ArrayList<Integer>();
		ArrayList<String> subID = new ArrayList<String>();
		System.out.println("Press q when you are done entering the number of sub assembilies");
		String nextInput = "";
		while (true) {
			System.out.println("What is the quantity for subassembly?");
			nextInput=in.next();
			
			if(nextInput.equals("") || !nextInput.equals("q")){
				qty.add(Integer.parseInt(nextInput));
				System.out.println("What is the ID");
				nextInput= in.next();
				subID.add(nextInput);
				continue;
			}else{
				break;
			}
			
				
			
		}//end of while
		System.out.println("What is the quantity in the stock?");
		int stock=Integer.parseInt(in.next());
		System.out.println("What is the price of the item?");
		double price= Double.parseDouble(in.next());
		Item newItem= new Item(ID, description, qty, subID, stock, price);
		I.kahiniStore.add(newItem);
		System.out.println("Item was added");
		
	}

}
