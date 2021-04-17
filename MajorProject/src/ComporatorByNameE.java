import java.util.Comparator;

public class ComporatorByNameE implements Comparator<Employee>{
	public int compare(Employee o1, Employee o2) {
		return o1.getLastName().compareTo(o2.getLastName());
	}
}
