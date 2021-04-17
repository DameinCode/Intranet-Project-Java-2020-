import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminTest {
	int id = 0;
	Admin a = new Admin();
	Faculty faculty;
	
	public AdminTest( ) { }
	
	public void start() throws ParseException {
		System.out.println("Enter login and password: ");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String login = reader.readLine();
			String password = reader.readLine();
			if(checkThis(login, password)) {
				AdminWork();
			}
		}
         catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkThis(String login, String password) {
		return a.check(login, password);
	}
	
	// working
	public void AdminWork() throws ParseException {
		System.out.println("Welcome, admin! ");
		System.out.println("What do you want to do ?");
		System.out.println("1. Add new user\n2. Delete user\n3. Show log file\n4. Update info about user\n5. See users\n6. Exit");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String letsGo = reader.readLine();
			if(letsGo.equals("1")) {
				AddUser();	
			} else if(letsGo.equals("2")) {
				DeleteUser();
			} else if(letsGo.equals("3")) {
				// ShowLogFile();
			} else if(letsGo.equals("4")) {
				UpdateUser();
			} else if(letsGo.equals("5")) {
				SeeUsers();
			} else if(letsGo.equals("6")) {
				//DataBase.serializeData();
				Controller control = new Controller();
				control.go();
			}
		}
         catch (IOException e) {
			e.printStackTrace();
		} 
		//DataBase.serializeData();
	}
	
	
	//adding
	public void AddUser() throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Who is this ?\n1. Student\n2. Teacher \n3. Tech support guy\n4. OR Manager");
			String answer = reader.readLine();
		
		
			if(answer.equals("1")) {
				System.out.println("First name:");
				String name = reader.readLine();
				System.out.println("Last name: ");
				String lastName = reader.readLine();
				System.out.println("Year of study: ");
				String year = reader.readLine();
				System.out.println("Faculty: ");
				String facult = reader.readLine();
				Faculties f = checkFaculty(facult);
				id++;
				boolean ok = true;
				for(Faculty fac: DataBase.facultiess) {
					if(fac.name.equals(f)) {
						a.addStudent(name, lastName, id, Integer.parseInt(year), fac);
						ok = false;
					}
				} if(ok) {
					System.out.println("Wrong faculty!");
				}
				
				AdminWork();
			} 
		
			else if(answer.equals("2")) {
				System.out.println("First name:");
				String name = reader.readLine();
				System.out.println("Last name: ");
				String lastName = reader.readLine();
				System.out.println("Faculty: ");
				String facult = reader.readLine();
				Faculties f = checkFaculty(facult);
				System.out.println("Status of teacher:");
				String status = reader.readLine();
				teacherStatus teachS = getStatus(status);
				id++;
				boolean ok = true;
				for(Faculty fac: DataBase.facultiess) {
					if(fac.name.equals(f)) {
						a.addTeacher(new Date(), name, lastName, id, fac, teachS);
						ok = false;
					}
				} if(ok) {
					System.out.println("Wrong faculty!");
				}
				AdminWork();
			}
		
			else if(answer.equals("3")) {
				System.out.println("First name: ");
				String name = reader.readLine();
				System.out.println("Last name: ");
				String lastName = reader.readLine();
				id++;
				a.addTechSupportGuy(new Date(), name, lastName, id);
				AdminWork();
			} 
		
			else if(answer.equals("4")) {
				System.out.println("First name: ");
				String name = reader.readLine();
				System.out.println("Last name: ");
				String lastName = reader.readLine();
				id++;
				a.addORManager(name, lastName, id);
				AdminWork();
			} 
		
			else {
				System.out.println("Something wrong!");
				AdminWork();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} DataBase.serializeData();
	}
	
	// deleting
	public void DeleteUser() throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Login: ");
			String logiin = reader.readLine(); 
			String ans = a.deleteUser(logiin);
			if(ans.equals("ERROR")) {
				System.out.println("There is no login");
				AdminWork();
			} else {
				System.out.println("SUCCESS!" + ans + "is deleted");
				AdminWork();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// updating
	public void UpdateUser() throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter login: ");
			String entLogin = reader.readLine(); // entered login
			System.out.println("What do you want to do ?");
			System.out.println("1.Change login\n2. Change password\n3. Change faculty\n4. Change hire date\n5. Change status");
			String idk = reader.readLine(); // i don't know yet
			
			if(idk.equals("1")) {
				System.out.println("New Login: ");
				String newLogin = reader.readLine();
				boolean ok = a.UpdateUser(entLogin, 1, newLogin, null, null, null, null);
				if(!ok) {
					System.out.println("Invalid option! ");
				} else {
					System.out.println("Success! ");
				} AdminWork();
 			}
			else if(idk.equals("2")) {
				System.out.println("New password: ");
				String newPassword = reader.readLine();
				boolean ok = a.UpdateUser(entLogin, 2, null, newPassword, null, null, null);
				if(!ok) {
					System.out.println("Invalid option! ");
				} else {
					System.out.println("Success! ");
				} AdminWork();
			}
			else if(idk.equals("3")) {
				System.out.println("Faculty name: ");
				String facName = reader.readLine();
				Faculties f = checkFaculty(facName);
				boolean ok = a.UpdateUser(entLogin, 3, null, null, new Faculty(f), null, null);
				if(!ok) {
					System.out.println("Invalid option! ");
				} else {
					System.out.println("Success! ");
				} AdminWork();
			}
			else if(idk.equals("4")) {
				System.out.println("Hire date: ");
				String hiredate = reader.readLine();
				DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				Date date = (Date)formatter.parse(hiredate);
				boolean ok = a.UpdateUser(entLogin, 4, null, null, null, date, null);
				if(!ok) {
					System.out.println("Invalid option! ");
				} else {
					System.out.println("Success! ");
				} AdminWork();
			} 
			else if(idk.equals("5")) {
				System.out.println("Status: ");
				String status = reader.readLine();
				teacherStatus teachS = getStatus(status);
				boolean ok = a.UpdateUser(entLogin,5, null, null, null, null, teachS);
				if(!ok) {
					System.out.println("Invalid option! ");
				} else {
					System.out.println("Success! ");
				} AdminWork();
			} else {
				System.out.println("Invalid option! "); 
				AdminWork();
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void SeeUsers() throws ParseException {
		System.out.println(DataBase.users);
		AdminWork();
	}
	
	// getting faculty enum from string
	public Faculties checkFaculty(String faculty) {
		if(faculty.toLowerCase().equals("fit")) {
			return Faculties.FIT;
		} else if(faculty.toLowerCase().equals("bs")) {
			return Faculties.BS;
		} else if(faculty.toLowerCase().equals("ise")) {
			return Faculties.ISE;
		} else if(faculty.toLowerCase().equals("feogi")) {
			return Faculties.FEOGI;
		} else if(faculty.toLowerCase().equals("gef")) {
			return Faculties.GEF;
		} else if(faculty.toLowerCase().equals("kma")) {
			return Faculties.KMA;
		} else if(faculty.toLowerCase().equals("sce")) {
			return Faculties.SCE;
		} else if(faculty.toLowerCase().equals("secaen")) {
			return Faculties.SECAEN;
		} else if(faculty.toLowerCase().equals("secmscp")) {
			return Faculties.SECMSCP;
		} 
		return Faculties.SMC;

	}
	
	// getting teacher status enum from teacher
	public teacherStatus getStatus(String status) {
		if(status.toLowerCase().equals("lector")) {
			return teacherStatus.Lector;
		} else if(status.toLowerCase().equals("tutor")) {
			return teacherStatus.Tutor;
		} else if(status.toLowerCase().equals("coach")) {
			return teacherStatus.Coach;
		} else if(status.toLowerCase().equals("instructor")) {
			return teacherStatus.Instructor;
		} else if(status.toLowerCase().equals("supervisor")) {
			return teacherStatus.Supervisor;
		} else if(status.toLowerCase().equals("senior lector")) {
			return teacherStatus.SeniorLector;
		} 
		return teacherStatus.Professor;
	}
	
}
