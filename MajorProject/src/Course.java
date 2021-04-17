import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Course implements Comparable<Course>, Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TreeMap<Student, Grade> studentG = new TreeMap<Student, Grade>(); // Student with grade
    private int numOfC; // number of credits
    private Vector<Teacher> teachers = new Vector<Teacher>(); // Teachers of this course 
    private Vector<CourseFile> neededFiles = new Vector<CourseFile>(); // As syllabus etc.
    public String name; // name of the course

    
    public Course() { }

    public Course(String name, int numOfC) {
    	this.name = name;
    	this.numOfC = numOfC;
    }
    
    
    public Vector<CourseFile> getCourseFiles() {
        return neededFiles;
    }
    

    public Vector<Teacher> getTeachers() {
        return teachers;
    }
    
    public void viewTeachers() {
		
    	try {
			PrintWriter pwFile = new PrintWriter(new FileWriter("Teachers.out")); // Teachers file; 
			
			for(Teacher i : teachers) {
				pwFile.println(i.getFullName());
			}
			
			pwFile.close();
			} 
		
		catch(IOException ioe) {
			System.out.println("Can’t read!");
			}
    }
    
    public Grade getGrade(Student st) {
    	for(Map.Entry e : studentG.entrySet()) {
    		if (st.equals((Student)e.getKey())) {
    			return (Grade) e.getValue();
    		}
    	} return null;
    }
    
    public void setCourseFiles(CourseFile file) {
    	neededFiles.add(file); // add some needed file to the course
    }
    
    public void setStudentG(Student s, Grade g) {
    	studentG.put(s, g);
    }
    
    public void deleteStudentG(Student s) {
    	studentG.remove(s);
    }
    
    public void deleteFile(CourseFile file) {
    	neededFiles.removeElement(file);
    }

    public int getNumOfCredits() {
        return numOfC; 
    }

    public TreeMap<Student, Grade> getStudentGrade() {
        return studentG;
    }
    
    public void putGrades(Student s, double grade) {
    	for(Map.Entry e : studentG.entrySet()) {
    		if (s.equals((Student)e.getKey())) {
    			Grade g = (Grade)e.getValue();
    			studentG.remove(s);
    			g.putMark(grade);
    			studentG.put(s, g);
    		}
    	}
    }
    
    
    public void droping(Student s) {
    	this.studentG.remove(s);
    }

    public int getNumStudents() {
        return studentG.size();
    }
    
    public boolean equals(Object o) {
    	if(o == this) {
    		return true;
    	} if(o == null) {
    		return false;
    	} if (this.getClass() != o.getClass()) {
			return false;
    	} Course c = (Course) o;
    	return this.numOfC == c.getNumOfCredits() && teachers.equals(c.getTeachers()) && this.name.equals(c.name); 
    }

	public int compareTo(Course c) {
		return name.compareTo(c.name);
	}
	
	public String toString() {
		return name;
	}
	
	public void removingTeacher(Teacher t) {
		this.teachers.remove(t);
	}
    
	public void addTeacher(Teacher teacher) {
		this.teachers.add(teacher);
	}
}
