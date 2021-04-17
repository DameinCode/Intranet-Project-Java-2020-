import java.util.Comparator;

public class ComparatorByGPA implements Comparator<Student>{
	
	public int compare(Student s1, Student s2) {
		if(s1.getGpa() > s2.getGpa()) {
			return 1;
		} if(s2.getGpa() > s1.getGpa()) {
			return -1;
		} return 0;
	}

}
