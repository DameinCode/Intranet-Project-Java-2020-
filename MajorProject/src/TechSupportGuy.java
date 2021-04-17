import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class TechSupportGuy extends Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<Order> orders = new Vector<Order>();
	
	public TechSupportGuy() { }
	public TechSupportGuy(Date hireDate, String firstName, String lastName, int id) {
		super(hireDate, lastName, firstName, id, "~");
	}
	
	public void recieveOrder(Order o) {
		orders.add(o);
	}
	
	public Vector<Order> viewOrders() {
		return orders;
	}
	
	public void acceptOrder(Order o) {
		o.status = true;
	}
	
	public void rejectOrder(Order o) {
		orders.remove(o);
	}


}
