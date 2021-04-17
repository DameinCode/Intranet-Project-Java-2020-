import java.util.Comparator;

public class ComparatorByNumFT implements Comparator<Teacher> {

	public int compare(Teacher t1, Teacher t2) {
		if(t1.getNumOfCourses() > t2.getNumOfCourses()) {
			return 1;
		} if(t1.getNumOfCourses() < t2.getNumOfCourses()) {
			return -1;
		} 
		return 0;
	}

}
