import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Controller {
	public Controller() {
		DataBase.getInstance();
		// DataBase.getData(); 
	}
	
	
	public void go() throws ParseException {
		System.out.println("----------".repeat(18));
		System.out.println("Welcome   ".repeat(18));
		System.out.println("----------".repeat(18));
		System.out.println("Who are you? \n1. User\n2. Admin");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			
			String line = reader.readLine();
			
			if(line.equals("1")) {
				UserTest ut = new UserTest();
				ut.start();
			} 
			else {
				AdminTest at = new AdminTest();
				at.start();
			}
		
		}
        
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
