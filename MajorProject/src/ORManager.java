import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;
import java.util.Date;
import java.util.TreeMap;

public class ORManager extends Employee implements viewInfoByOrder, Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addCourse(Course course) {
    	DataBase.courses.add(course);
    }
    
    public ORManager() { };
    
    public ORManager(Date hireDate, String firstName, String lastName, int id) {
    	super(hireDate, lastName, firstName, id, ".");
    }

    public void sendMessage(Message message, email adress) {
    	adress.recieveEmail(message, this.getEmail());
    }

    public void viewStudents() {
   		try {
    		PrintWriter pwFile = new PrintWriter(new FileWriter("toOR.txt"));
    		for (Faculty f : DataBase.facultiess) {
    			pwFile.println(f.students);	
    		}
   			pwFile.close();
   		} 
    	catch(IOException ioe) {
    		System.out.println("Can’t read!");
   		}
   	}

    public void viewTeachers() {
    	
		try {
			PrintWriter pwFile = new PrintWriter(new FileWriter("toOR.txt")); // try it without try/catch
			pwFile.flush();
			for (Faculty f : DataBase.facultiess) {	
				pwFile.println(f.fteachers);	
			}
			pwFile.close();
		} catch(IOException ioe) {
			System.out.println("Can’t read!");
		}
    }
    

    public int compareTo(ORManager o) {
        return getFirstName().compareTo(o.getFirstName());
    }


    public boolean equals(Object o) {
        return super.equals(o);
    }


//    public void getSalary() {
//    	
//    }
    
    

    public void viewBySortedOrder(String byWhat, String whom) {
    	if(byWhat.equals("Name")) {
    		if(whom.equals("teacher")) {
    			viewBySortedNameS();
    		} else if (whom.equals("student")){
    			viewBySortedNameT();
    		}
    	}  else if (byWhat.equals("numcourse")){
    		if(whom.equals("student")) {
    			sortedByNumOfCoursesS();
    		} else if (whom.equals("teacher")){
    			sortedByNumOfCoursesT();
    		}
    	}
    }


    public void viewBySortedNameS() {
    	Vector<Student> st = new Vector<Student>();
    	for (Faculty f : DataBase.facultiess) {
    		for(Student s: f.students) {
    			st.add(s);
    		}
    	} 
    	Collections.sort(st);
		try {
			PrintWriter pwFile = new PrintWriter(new FileWriter("toOR.txt"));
			pwFile.println(st);	
			pwFile.close();
		} 
		catch(IOException ioe) {
			System.out.println("Can’t read!");
		}

    }
    
    public String toString() {
    	return "Full name: " + getFirstName() + " " + getLastName() + getId(); 
    }
    
    public TreeMap<email, Message> messages() {
    	return super.emailAdress.messages;
    }
    
    public void viewBySortedNameT() {
    	Vector<Teacher> teachers = new Vector<Teacher>();
    	for (Faculty f : DataBase.facultiess) {
    		for(Teacher t: f.fteachers) {
    			teachers.add(t);
    		}
    	} 
    	Collections.sort(teachers);
		try {
			PrintWriter pwFile = new PrintWriter(new FileWriter("toOR.txt"));
			pwFile.println(teachers);	
			pwFile.close();
		} 
		catch(IOException ioe) {
			System.out.println("Can’t read!");
		}
    }


    
    public void sortedByNumOfCoursesT() {
    	Vector<Teacher> teachers = new Vector<Teacher>();
    	for (Faculty f : DataBase.facultiess) {
    		for(Teacher t: f.fteachers) {
    			teachers.add(t);
    		}
    	} 
    	Collections.sort(teachers, new ComparatorByNumFT());
    	try {
			PrintWriter pwFile = new PrintWriter(new FileWriter("toOR.txt"));
			pwFile.println(teachers);	
			pwFile.close();
		} 
		catch(IOException ioe) {
			System.out.println("Can’t read!");
		}
    }

	@Override
	public void sortedByNumOfCoursesS() {
		Vector<Student> st = new Vector<Student>();
    	for (Faculty f : DataBase.facultiess) {
    		for(Student s: f.students) {
    			st.add(s);
    		}
    	} 
    	Collections.sort(st, new ComparatorByNumFS());
    	try {
			PrintWriter pwFile = new PrintWriter(new FileWriter("toOR.txt"));
			pwFile.println(st);	
			pwFile.close();
		} 
		catch(IOException ioe) {
			System.out.println("Can’t read!");
		}
	}

}
