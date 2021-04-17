import java.io.Serializable;
import java.util.Date;
import java.util.Vector;


/**
* The Student class that works similarly to real student
* 
* @author  Gulbina, Diyoura, Alisher
* 
* @since   2020-11-30
* 
*/


public class Admin extends Employee implements Serializable{
	
	/**
	 * just for serializable object
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * *login of admin
	 */
	private String login;
	/**
	 * password of admin
	 */
	private int password;
	
	/**
     * This method is used to create an instance of admin with no arguments
     * uses a super constructor - argument '_' is used to generate login "firstName_LastName"
     
     * @return created ne wADmin
     */
	public Admin() { }
	
	/**
	 * vectorg of logins
	 */
	private Vector<String> logs = new Vector<String>();
	
	{
		login = "admin"; 
		password = hash_("password");
	}
	/**
	 * 
	 * @param s Student 
	 * @param n to check what to change
	 * @param newlogin new l
	 * @param newpassword rdnew pass
	 * @param faculty  faculty
	 */
	public void UpdateStudent(Student s, int n, String newlogin, String newpassword, Faculty faculty) {
		switch (n) {
        	case 1:
        		s.setLogin(newlogin);
        		break;
        	case 2:
        		s.setPasswordToAdmin(newpassword, this);
        		break;
        	case 3:
        		s.setFaculty(faculty);
        		break;
        	default:
        		System.out.println("Invalid option! ");
		}
	}
	/**
	 * 
	 * @param t
	 * @param n
	 * @param newlogin
	 * @param newpassword
	 * @param faculty
	 * @param hireDate
	 * @param status
	 */
	public void UpdateTeacher(Teacher t, int n, String newlogin, String newpassword, Faculty faculty, Date hireDate, teacherStatus status) {
		switch (n) {
    		case 1:
    			t.setLogin(newlogin);
    			break;
    		case 2:
    			t.setPasswordToAdmin(newpassword, this);
    			break;
    		case 3:
    			t.setFaculty(faculty);
    			break;
    		case 4:
    			t.setHireDate(hireDate);
    			break;
    		case 5:
    			t.setStatus(status);
    		default:
    			System.out.println("Invalid option! ");
		}
	}
	/**
	 * 
	 * @param or
	 * @param n
	 * @param newlogin
	 * @param newpassword
	 * @param hireDate
	 */
	public void UpdateOR(ORManager or, int n, String newlogin, String newpassword, Date hireDate) {
		switch (n) {
			case 1:
				or.setLogin(newlogin);
				break;
			case 2:
				or.setPasswordToAdmin(newpassword, this);
				break;
			case 4:
				or.setHireDate(hireDate);
				break;
			default:
				System.out.println("Invalid option! ");
		}	
	}
	/**
	 * 
	 * @param ts
	 * @param n
	 * @param newlogin
	 * @param newpassword
	 * @param hireDate
	 */
	public void UpdateTech(TechSupportGuy ts, int n, String newlogin, String newpassword, Date hireDate) {
		switch (n) {
			case 1:
				ts.setLogin(newlogin);
				break;
			case 2:
				ts.setPasswordToAdmin(newpassword, this);
				break;
			case 4:
				ts.setHireDate(hireDate);
				break;
			default:
				System.out.println("Invalid option! ");
		}
	}
	
	
    public boolean UpdateUser(String login, int n, String newlogin, String newpassword, Faculty faculty, Date hireDate, teacherStatus status){
    	
    	for(Faculty f : DataBase.facultiess) {
    		for(Student s: f.students) {
    			if(s.getLogin().equals(login)) {
    				UpdateStudent(s, n, newlogin, newpassword, faculty);
    				return true;
    			}
    		}
    	}  
    	
    	for(Faculty f : DataBase.facultiess) {
    		for(Teacher t: f.fteachers) {
    			if(t.getLogin().equals(login)) {
    				UpdateTeacher(t, n, newlogin, newpassword, faculty, hireDate, status);
    				return true;
    			}
    		}
    	}
    
    	for(ORManager or: DataBase.ors) {
			if(or.getLogin().equals(login)) {
				UpdateOR(or, n, newlogin, newpassword, hireDate);
				return true;
			}
		} 
    	
    	for(TechSupportGuy ts: DataBase.tsg) {
    		if(ts.getLogin().equals(login)) {
    			UpdateTech(ts, n, newlogin, newpassword, hireDate);
    			return true;
    		}
    	} 
    	return false;
    }

    
    // deleting user
    public String deleteUser(String login){
    	for(Faculty f : DataBase.facultiess) {
    		for(Student s: f.students) {
    			if(s.getLogin().equals(login)) {
    				for(Course course: DataBase.courses) {
    					if(course.getStudentGrade().containsKey(s)) {
    						course.deleteStudentG(s);
    					}
    				}
    				f.students.remove(s);
    				return("Student");
				}
			}
		}
    	
    	for(Faculty f : DataBase.facultiess) {
    		for(Teacher t: f.fteachers) {
    			if(t.getLogin().equals(login)) {
    				for(Course course: DataBase.courses) {
    					if(course.getTeachers().contains(t)) {
    						course.removingTeacher(t);
    					}
    				}
    				f.fteachers.remove(t);
    				return("Teacher");
    			}
			}
		}
    	
    	for(ORManager or: DataBase.ors) {
			if(or.getLogin().equals(login)) {
				DataBase.ors.add(or);
				return "OR Manager";
			}
		} 
    	for(TechSupportGuy ts: DataBase.tsg) {
    		if(ts.getLogin().equals(login)) {
    			DataBase.tsg.remove(ts);
    			return "Tech support guy";
    		}
    	}
    	return "ERROR";
    }
    
//    public void calc_pow() {
//    	int[] p_pow = new int[20];
//        p_pow[0] = 1;
//        for (int i = 1; i < 20; i++)
//            p_pow[i] = p_pow[i - 1] * 31;
//    }
//    
//    public int hash_(String pass) {
//    	calc_pow();
//    	int sum = 0;
//        for (int i = 0; i < pass.length(); i++) {
//            sum += pass.charAt(i) * p_pow[i];
//        }
//        return sum;
//    }
    
    public boolean check(String login, String password) {
    	return this.login.equals(login) && this.password == hash_(password);
    }
    
    
    public Vector<String> seeLogs() {
    	return this.logs;
    }
    
    
   public void addStudent(String firstName, String lastName, int id, int yearOfStudy, Faculty faculty) {
		Student student = new Student(firstName, lastName, id, yearOfStudy, faculty);
		for(Faculty f: DataBase.facultiess) {
			if (f.equals(faculty)) {
				if(f.students.contains(student)) {
					break;
				}
				f.students.add(student);
			}
		} 
	}
	
	public void addORManager(String firstName, String lastName, int id) {
		Date date = new Date();
		
		ORManager or = new ORManager(date, firstName, lastName, id);
		if(! DataBase.ors.contains(or)) 
			DataBase.ors.add(or);
		
	}
	
	
	public void addTeacher(Date hireDate, String firstName, String lastName, int id, Faculty faculty, teacherStatus status) {
		Teacher teacher = new Teacher(hireDate, firstName,lastName, id, faculty, status);
		for(Faculty f: DataBase.facultiess) {
			if(f.equals(faculty)) {
				if(f.fteachers.contains(teacher)) {
					break;
				}  f.fteachers.add(teacher);
			}
		}
		
	}
	
	public void addTechSupportGuy(Date hireDate, String firstName, String lastName, int id) {
		TechSupportGuy ts = new TechSupportGuy(hireDate, firstName, lastName, id);
		if(! DataBase.tsg.contains(ts)) {
			DataBase.tsg.add(ts);
		}
	}

    // @Override
//    public void getSalary(User user){
//        super.getSalary(user);
//    }

//    public Vector<User> getUsers() {
//        return DataBase.getUsers();
//    }

//    public void setUsers(Vector<User> users) {
//        this.users = users;
//    }
}
