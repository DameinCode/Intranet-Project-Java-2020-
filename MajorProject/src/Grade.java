import java.io.Serializable;

public class Grade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Grade() {}
	public double mark; 
    private String degree;
    {
    	mark = 0.0;
    }
    
    public Grade(double mark) {
    	this.mark = mark;
    }
    
    public String getDegree() {
    	return degree;
    }
    
    public void putMark(double mark) {
    	this.mark += mark;
    }
    
    public double getMark() {
    	return this.mark;
    } 

    
}
