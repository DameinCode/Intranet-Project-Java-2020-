import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class DataBase implements Serializable{
	
	private static DataBase INSTANCE;
	private static final long serialVersionUID = 1L;
	static Vector<Faculty> facultiess = new Vector<Faculty>();
	static Vector<Course> courses = new Vector<Course>();
	static Vector<User> users = new Vector<User>();
	static Vector<ORManager> ors = new Vector<ORManager>();
	static Vector<TechSupportGuy> tsg = new Vector<TechSupportGuy>();
	
	public static DataBase getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataBase();
		}
		return INSTANCE;
	}
	
	private DataBase() { 
		createFaculty();
		// decserialization
	}
	
	public void createFaculty() {
		facultiess.add(new Faculty(Faculties.FIT));
		facultiess.add(new Faculty(Faculties.BS));
		facultiess.add(new Faculty(Faculties.FEOGI));
		facultiess.add(new Faculty(Faculties.GEF));
		facultiess.add(new Faculty(Faculties.KMA));
		facultiess.add(new Faculty(Faculties.SCE));
		facultiess.add(new Faculty(Faculties.SECAEN));
		facultiess.add(new Faculty(Faculties.SECMSCP));
		facultiess.add(new Faculty(Faculties.SMC));
	}
		
	
	public static void serializeData() {
		
		try {
			ObjectOutputStream oot = new ObjectOutputStream(new FileOutputStream("manager.txt"));
			oot.writeObject(ors);
			oot.flush();
			oot.close();
			
			oot = new ObjectOutputStream(new FileOutputStream("techguy.txt"));
			oot.writeObject(tsg);
			oot.flush();
			oot.close();
			
			oot = new ObjectOutputStream(new FileOutputStream("faculties.txt"));
			oot.writeObject(facultiess);
			oot.flush();
			oot.close();
			
			oot = new ObjectOutputStream(new FileOutputStream("courses.txt"));
			oot.writeObject(courses);
			oot.flush();
			oot.close();
			
			oot = new ObjectOutputStream(new FileOutputStream("users.txt"));
			oot.writeObject(users);
			oot.flush();
			oot.close();
		}
		
		
		catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
		catch (IOException e) {
			System.out.println("IO exeption");
		}
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public static void getData() {
		try {
			
			// decserialize courses
			
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("courses.txt"));
            courses = (Vector<Course>) ois.readObject();
            ois.close();
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		catch (IOException e) {
			System.out.println("IO exeption");
		}
            // Decserialize faculties
        try {
        	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("faculties.txt"));
            facultiess = (Vector<Faculty>) ois.readObject();
            ois.close();
        }
		catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("IO exeption + faculty");
        }

        
        try {
        	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.txt"));
            users = (Vector<User>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("IO exeption + user");
        }
        
            // decserialize OR
         try {   
        	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("manager.txt"));
            ors = (Vector<ORManager>) ois.readObject();
            ois.close(); 
         } catch (ClassNotFoundException e) {
                System.out.println("Class not found");
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
            catch (IOException e) {
                System.out.println("IO exeption");
            }

            // decserialize tech guys
         try {   
        	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("techguy.txt"));
			tsg = (Vector<TechSupportGuy>) ois.readObject();
			ois.close();
		
		}
		catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("IO exeption");
        }
	}
	
   
	

	

public static void updateUser(User user, User newUser) {
		for(User u: users) {
			if (u.equals(user)) {
				u = newUser;
			}
		}
	}
	
//	public static void sendCoursesToTeachers(Course course) {
//		for(Faculty f: facultiess) {
//			for(Teacher )
//		}
//		for(Teacher t: teachers) {
//			boolean ok = t.recievelistCourse(course);
//			if(ok) {
//				course.addTeacher(t);
//			}
//		}
//	}
	
//	
//	public void addLog(String str) {
//		
//    }
	
	
}
