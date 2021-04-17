import java.io.Serializable;

public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double balance = 0.0;
	
	public Account() { }
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double salary) {
		balance += salary;
	}
}
