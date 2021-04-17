import java.io.Serializable;
import java.util.TreeMap;

public class email implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Email;
//	private String text; // to check the email(changing password)
	TreeMap<email, Message> messages = new TreeMap<email, Message>();
	
	public email() {}
	
	public email(String email) {
		this.Email = email;
	}

	public void recieveEmail(Message text, email adress) {
		messages.put(adress, text);
	}
	
	public TreeMap<email, Message> getmessages() {
		return messages;
	}
	
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		} if (o == null) {
			return false;
		} if (this.getClass() != o.getClass()) {
			return false;
		} email e = (email) o; 
		return this.Email.equals(e.Email);
	}
	
}
