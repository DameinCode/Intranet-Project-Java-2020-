import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Vector;

public class Transcript implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double gpa;
	private double firstAttestation;
	private double secondAttestation;
	private double finalExam;
	Vector<Course> courses = new Vector<Course>();
	Vector<String> vec = new Vector<String>();
	
	{
		gpa = 0.0;
		firstAttestation = 0;
		secondAttestation = 0;
		finalExam = 0;
	}
	
	public Transcript() { }
	
	public double getGPA() {
		return this.gpa;
	}
	
	public void tostring() {
		for(Course c : courses) {
			System.out.println(c.name + " ".repeat(20-c.name.length()) + this.firstAttestation + "   " + secondAttestation + "  " + finalExam + roundTotal()); 
		}
 	}
	
	public int roundTotal() {
		return (int) Math.round(firstAttestation + secondAttestation + finalExam + 1);
	}

	public void setFirstAttestation(double mark) {
		this.firstAttestation = mark;
	}
	
	public void secondAttestation(double mark) {
		this.secondAttestation = mark;
	}
	
	public void setFinalExam(double mark) {
		this.finalExam = mark; 
	}
	
	public void viewTranscript() {
		try {
			PrintWriter pwFile = new PrintWriter(new FileWriter("transcript.out"));
			
			for(String s: vec) {
				pwFile.println(s);
			}
			
			for(Course c : courses) {
				pwFile.println(c.name + " ".repeat(20-c.name.length()) + this.firstAttestation + "   " + secondAttestation + "  " + finalExam + roundTotal());
				vec.add(c.name + " ".repeat(20-c.name.length()) + this.firstAttestation + "   " + secondAttestation + "  " + finalExam + roundTotal());
			} 
			vec.add("The total gpa is: " + gpa);
			vec.add("\n\n\n");
			
			pwFile.close();
		} 
		
		catch(IOException ioe) {
			System.out.println("Can’t read!");
		}

	}
	
	public void courses(Vector<Course> v) {
		this.courses = v;
	}
	
//	public void totalGPA() {
//		for(Course c: courses) {
//			
//		} 
//	}
}
	
