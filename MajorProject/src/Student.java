import java.io.BufferedReader; 

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Vector;
public class Student extends User implements Comparable<Student>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	*/
	// private  Vector<Course> courses = new Vector<Course>();
	public int yearOfStudy;
	Faculty faculty;
	private Transcript transcript = new Transcript();
	private int numOfCourse;
	{
		numOfCourse = 0;
	}
	
	public Student() { }
	
	public Student(String firstName, String lastName, int id, int yearOfStudy, Faculty faculty) {
		super(firstName, lastName, id, "_");
		this.yearOfStudy = yearOfStudy;
		setFaculty(faculty);
	}
		
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
		faculty.students.add(this);
	}
	
//	
//	public Vector<CourseFile> viewFileCourses(Course course) {
//		for(Course cours: ) {
//			if(cours.equals(course)) {
//				return course.getCourseFiles();
//			}
//		}
//		return null;
//	}	
	
	public Vector<Teacher> getTeachers(Course course) {
		for(Course cours: DataBase.courses) { // If student have this course, student can get the list of teachers of this course
			if(cours.equals(course)) {
				return course.getTeachers();
			}
		} return null;
	}
	
	
	public Transcript getTranscript() {
	    return transcript;
	}
	
	public double getGpa() {
		return transcript.getGPA();
	}

	
	
//  ¬ транскрипте нужен метод гет урсы, дл€ этого нужно список курсыов вз€ть у факультета. 
	
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		} if(this == o) {
			return true;
		} if(this.getClass() != o.getClass()) {
			return false;
		} Student s = (Student) o;
		return s.getId() == this.getId(); // enough to compare with id, they are unique 
	}

	public int compareTo(Student s) {
	    return super.getFirstName().compareTo(s.getFirstName());
	}

	public void dropCourse(Course course) {
		for(Course c : DataBase.courses) {
			if(c.equals(course)) {
				if(c.getStudentGrade().containsKey(this)) {
					c.deleteStudentG(this);
				}
			}
		}
	}
	
	public void registerCourse(String course) {
		for(Course courses : DataBase.courses) {
			if(courses.name.equals(course)) {
				if(!courses.getStudentGrade().containsKey(this)) {
					courses.setStudentG(this, new Grade(0));
				}
			}
		}
	}
	
	public Grade getGrade(String nameCourse) {
		for(Course courses: DataBase.courses) {
			if(courses.name.equals(nameCourse)) {
				if(courses.getStudentGrade().containsKey(this)) {
					return courses.getGrade(this);
				}
			}
		} return null;
	}
	
	// need a file in this project like rups
	public void registerCourse(Course course, Teacher t, String semester) {
		boolean ok = true;
		boolean just = true;
		String justForMin; 
		if(! faculty.getRups().containsKey(this.yearOfStudy)) {
			ok = false;
		}
		for(Map.Entry e : faculty.getRups().entrySet()) {
			if((int)e.getKey() == this.yearOfStudy) {
				justForMin = (String) e.getValue(); // file name
			}
		}
		
		if(ok) {
			try {
				BufferedReader br = new BufferedReader(new FileReader("rups.txt")); // file rups needed to be created in every facukty
				String line = br.readLine();
				
				while (! line.equals("\n\n")) {
					if(line.equals(semester)) {
						just = false;
					}
					if (just == false) {
						if (line.equals("\n")) {
							just = true;
						} 
						if(course.name.equals(line)) {
							course.viewTeachers();
							Vector<Teacher> teachers = new Vector<Teacher>();
							teachers = course.getTeachers();
							if(teachers.contains(t)) {
								Grade g = new Grade();
								course.setStudentG(this, g);
								this.numOfCourse++;
							}
						}
					}
					
					line = br.readLine();
					}
				
				br.close();
				} 
			
			catch(IOException ioe) {
				System.out.println("CanТt read!");
				}
	
			}
		}
	
	public String toString() {
		return super.getFirstName() + " " + super.getLastName();
	}
	
	
	public void viewTranscript() {
		transcript.viewTranscript(); 
	}
	
	public int getNumOfCourse() {
		return this.numOfCourse;
	}
	
	public void setTranscript(int week, double grade) {
		if(week >= 8 && week <= 10) {
			transcript.setFirstAttestation(grade);
		} else if(week >= 13 && week <= 15) {
			transcript.secondAttestation(grade);
		} else if(week > 15 ) {
			transcript.setFinalExam(grade);
		}
	}
}
