import java.util.Comparator;

public class ComparatorByNumFS implements Comparator<Student> {


	public int compare(Student s1, Student s2) {
		if(s1.getNumOfCourse() > s2.getNumOfCourse()) {
			return 1;
		} if(s1.getNumOfCourse() < s2.getNumOfCourse()) {
			return -1;
		} 
		return 0;
	}

}
