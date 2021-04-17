import java.io.Serializable;
import java.util.Date;


public abstract class Employee extends User implements GetSalary, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Employee() {}

    private Date hireDate;

    
    public Employee(Date hireDate, String lastName, String firstName, int id, String c) {
    	super(firstName, lastName, id, c);
    	this.hireDate = hireDate;
    }

    public boolean equals(Object o) {
		if(this == o) {
			return true;
		} if (o == null) {
			return false;
		} if (this.getClass() != o.getClass()) {
			return false;
		} Employee e = (Employee) o;
		return this.getLastName().equals(e.getLastName()) && this.hireDate.equals(e.hireDate) && this.getId() == e.getId();
    }

    public Date getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(Date newHireDate) {
		this.hireDate = newHireDate;
	}

    public int hashCode() {
    	int result = 31;
    	result = 31*result + hireDate.hashCode();
		return result;
    }


    public String toString() {
        return "This is employee; \n" + "Employee's name: " + this.getLastName() + " " + getFirstName() + "\n" + "Employee's hire date: " + this.hireDate + "\n";
    }


    public int compareTo(Employee e) {
		return getFirstName().compareTo(e.getFirstName());
    }

    public void getSalary() {
    	// нужно создать класс банк и там сделать моменты с зарплатой 
    }
    
    public void passMedExamination() {
    	// 
    }
}
