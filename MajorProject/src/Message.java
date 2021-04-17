import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String text;
	String date;
	
	public Message() { }
	
	public Message(String text) {
		this.text = text;
		DateTimeFormatter dd = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
		date = dd.format(LocalDateTime.now());
	}
	
	public String toString() {
		return text + "\n                                    " + date;  
	}
}
