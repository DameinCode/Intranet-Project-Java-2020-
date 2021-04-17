import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	boolean status;
	int OrderNumber;
	private Date date;
	{
		status = false;
	}
	public Order() { }
	
	public Order(Date date, int orderNum) {
		this.date = date;
		OrderNumber = orderNum;
	}
	
	public Date getDate() {
		return this.date;
	}
	
}
