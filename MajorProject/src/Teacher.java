import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Teacher extends Employee implements Comparable<Teacher>, Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// private TreeMap<Course, Vector<Student>> courseStudents = new TreeMap<Course, Vector<Student>>();
    public teacherStatus statusTeacher;
    Faculty faculty;
    private int numOfCourses;
    Account a = new Account();

    public Teacher() { }
	
	public Teacher(Date hireDate, String firstName, String lastName, int id, Faculty faculty, teacherStatus status) {
		super(hireDate, lastName, firstName, id, "-");
		setFaculty(faculty);
		statusTeacher = status;
	}
	
	public void setFaculty(Faculty faculty) {
		faculty.fteachers.add(this);
	}
    
    public boolean AddCoursesFile(CourseFile newFile, String course) {
    	for(Course courses: DataBase.courses) {
    		if(courses.name.equals(course)) {
    			if (courses.getTeachers().contains(this)) {
    				courses.setCourseFiles(newFile);
    				return true;
    			}
    		}
    	} return false;
    	
    }

    public boolean deleteCourseFile(CourseFile courseFile, String course) {
    	for(Course courses: DataBase.courses) {
    		if(courses.name.equals(course)) {
    			if (courses.getTeachers().contains(this)) {
    				courses.deleteFile(courseFile);
    				return true;
    			}
    		}
    	} return false;
    }

    
    public void putMarks(String nameCourse, int week, String loginSt, Double grade) {
    	if(week >= 8 && week <= 10 || week >= 13) {
    		Student st = null;
    		boolean ok = true;
    		for(Faculty f: DataBase.facultiess) {
    			for(Student s: f.students) {
    				if(s.getLogin().equals(loginSt)) {
    					st = s;
    					ok = false;
    					break;
    				}
    			}
    		}
    		if(ok) {
    			System.out.println("There is no such student!");
    		}
    		for(Course course: DataBase.courses) {
	    		if(course.name.equals(nameCourse)) {
	   				if(course.getTeachers().contains(this) && course.getStudentGrade().containsKey(st)) {
	   					course.putGrades(st, grade);
	   					ok = true;
	   				}
	   			}
	   		} 
    		if(! ok) {
    			System.out.println("You cant put mark to this student. There are 2 possible reasons: \nYou are not taecher of this course\nThis student is not in this course");
    		} else {
    			st.setTranscript(week, grade);
    		}
    	}
    }
    
    public boolean viewInfoStudent(String course) {
    	for(Course courses: DataBase.courses) {
    		if(courses.name.equals(course)) {
    			if(courses.getTeachers().contains(this)) {
    				for(Map.Entry e : courses.getStudentGrade().entrySet()) {
    	    			System.out.println((Student)e.getKey());
    	    		} return true;
    			}
    		} 
    	} return false;
    }
    
    
    
    public TreeMap<email, Message> getmessage() {
    	return this.getEmail().getmessages(); 
    }
    
    
    public boolean sendMessageToOR(Message m, String email) {
    	for(ORManager or: DataBase.ors) {
    		if(or.emailAdress.Email.equals(email)) {
    			emailAdress.recieveEmail(m, this.emailAdress);
    			return true;
    		}
    	} return false;
    }
    
    public boolean registerToCourse(String nameCourse) {
    	for(Course course: DataBase.courses) {
    		if(course.name.equals(nameCourse)) {
    			course.addTeacher(this);
    			this.numOfCourses++;
    			return true;
    		}
    	} return false;
    }
    
    public void ViewCourses() {
    	try {
			PrintWriter pwFile = new PrintWriter(new FileWriter("viewCourses.txt"));
    		for(Course course : DataBase.courses) {
    			pwFile.println(course.name + course.getNumOfCredits());
    		}
    	} catch(IOException ioe) {
			System.out.println("Can’t read!");
		}
    }
    
    public void sendOrderToIT(Order order) {
    	
    }
    
    
    public Vector<Student> getStudents(Course course) {
    	Vector<Student> st = new Vector<Student>();
    	if(course.getTeachers().contains(this)) {
    		for(Map.Entry e : course.getStudentGrade().entrySet()) {
    			st.add((Student) e.getKey());
    		}
    	} return st;
    }

    

    public boolean equals(Object o) {
    	if (! super.equals(o)) {
			return false;
    	} Teacher t = (Teacher) o;
    	return (super.equals(t)) && this.statusTeacher == t.statusTeacher;
    }


    public void getSalary() {
    	a.setBalance(300000.0);
    }
    
    public Vector<CourseFile> getCourseFiles(Course course) {
    	if(course.getTeachers().contains(this)) {
    		return course.getCourseFiles();
    	} return null;
    }

    public teacherStatus getStatusOfTeacher() {
        return this.statusTeacher;
    }

    public int getNumOfCourses() {
    	return this.numOfCourses;
    }
    
    
    public void setStatus(teacherStatus status) {
    	this.statusTeacher = status;
    }
    
	public int compareTo(Teacher t) {
		return super.getFirstName().compareTo(t.getFirstName());
	}
	
	
	public String toString() {
		return "Teacher's name: " + super.getLastName() +  " " + super.getFirstName() + "Status: " + this.statusTeacher + "ID: " + getId() + "\n";
	}
	
	public String getFullName() {
		return super.getFirstName() + " " + super.getLastName();
	}

}
