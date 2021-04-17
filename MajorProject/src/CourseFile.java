import java.io.Serializable;
import java.util.Date;

public class CourseFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseFile() { }
	
	public String fileName;
    public FileTypes fileType;
    public Date dateofLayOut;
    
    public CourseFile(String filename, FileTypes fileType, Date dateofLayOut) {
    	this.fileName = filename;
    	this.fileType = fileType;
    	this.dateofLayOut = dateofLayOut;
    } 
    
    public String toString() {
    	return "The name of file: " + fileName + "File type: " + fileType + "Date of lay out: " + dateofLayOut + "\n"; 
    }
    
    public boolean equals(Object o) {
    	if(this == o) {
			return true;
		} if (o == null) {
			return false;
		} if (this.getClass() != o.getClass()) {
			return false;
		} CourseFile cf = (CourseFile) o;
		return fileName.equals(cf.fileName) && dateofLayOut.equals(cf.dateofLayOut) && fileType.equals(cf.fileType);
    }

}
