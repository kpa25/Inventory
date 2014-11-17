import java.io.File;
import java.io.FileNotFoundException;


public class main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println("reading the file now");
		
		String f = args[0];
		File file = new File(f);
		Inventory I= new Inventory(file);
	}

}
