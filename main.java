import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
	static Inventory I = null;

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		System.out.println("reading the file now");

		String f = args[0];
		File file = new File(f);
		I = new Inventory(file);

		System.out.println("Press 1 to ADD an item");
		System.out.println("Press 2 to REMOVE an item");
		System.out.println("Press 3 to ADD a quantity");
		System.out.println("Press 4 to REMOVE a quantity");
		System.out.println("Press q to quit the program");

		Scanner in = new Scanner(System.in);
		while (true) {
System.out.println(I.kahiniStore.size());
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

			} else {
				System.out.println("The input you entered is not valid. Try again.");
				continue;
			}

		}// end of while loop

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
