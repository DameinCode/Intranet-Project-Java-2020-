import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class Faculty implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Faculties name;
	public Vector<Teacher> fteachers = new Vector<Teacher>();
	public Vector<Student> students = new Vector<Student>();
	private HashMap<Integer, String> rups = new HashMap<Integer, String>();
	
	public Faculty() { }
	
	public Faculty(Faculties name) {
		this.name = name;

	}
	
	public void viewTeachers() {
		System.out.println(fteachers);
	}
	
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		} if(o == null) {
			return false;
		} Faculty f = (Faculty) o;
		return f.name == this.name;
	}
	
	public HashMap<Integer, String> getRups() {
		return this.rups;
	}
	
}
