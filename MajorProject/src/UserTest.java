import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;

public class UserTest {
	
	ORManager OR;
	Teacher T;
	TechSupportGuy TSG;
	Student st;
	
	public UserTest() { }
	
	public void start() throws ParseException {
		System.out.println("Please enter your login and password: ");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String login = reader.readLine();
			String password = reader.readLine();
			checkWhoIsThis(login, password);
		}
         catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void checkWhoIsThis(String login, String password) throws ParseException {
		for(ORManager or: DataBase.ors) {
			if(or.getLogin().equals(login) && or.CheckPassword(password)) {
				ORManagerWork(or, password);
				break;
			} 
		} 
		for(Faculty faculties: DataBase.facultiess) {
			for(Teacher teacher: faculties.fteachers) {
				if(teacher.getLogin().equals(login) && teacher.CheckPassword(password)) {
					TeacherWork(teacher, password);
					break;
				}
			}
		}
		for(Faculty faculties: DataBase.facultiess) {
			for(Student student: faculties.students) {
				if(student.getLogin().equals(login) && student.CheckPassword(password)) {
					StudentWork(student, password);
					break;
				}
			}
		} 
		for(TechSupportGuy TSG : DataBase.tsg) {
			if(TSG.getLogin().equals(login) && TSG.CheckPassword(password)) {
				TechSupportGuyWork(TSG, password);
				break;
			}
		} 
		System.out.println("Wrong login or password! ");
		start();
	}
	
	
	
	
	// OR MANAGER
	
	
	public void ORManagerWork(ORManager or, String password) throws ParseException {
		OR = or;
		
		if(password.equals("password")) {
			System.out.println("Your password is not safe, please be sure that you have changed default password");
			changePassword(password, OR);
		}
		
		System.out.println("Hello, OR Manager. What do you want to do ? \n1. Add some news\n2. Add courses\n3. View teachers/students\n4. Send messedge to the teacher\n5. View by order\n6. Change password\n7. View messages\n8. Exit");
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			
			String soWhat = reader.readLine();
			
			if(soWhat.equals("1")) {
				// OR.addNew();
			} 
			
			else if(soWhat.equals("2")) {
				addcourse(OR, password);
			} 
			
			else if(soWhat.equals("3")) {
				viewTorS(OR, password);
			} 
			
			else if(soWhat.equals("4")) {
				sendMessageToTeacher(OR, password);
			} 
			
			else if(soWhat.equals("5")) {
				endiViewOrder(OR, password);
			}
			
			else if(soWhat.equals("6")) {
				System.out.println("Enter your old password: ");
				String oldPassword = reader.readLine();
				changePassword(oldPassword, OR);
			}
			
			else if(soWhat.equals("7")) {
				viewMessageToOR(OR, password);
			}
			
			else if(soWhat.equals("8")) {
				Controller control = new Controller();
				control.go();
				DataBase.serializeData();
			} 
		} 
		
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void viewMessageToOR(ORManager or, String password) throws ParseException{
		System.out.println("Messages: ");
		or.messages();
		ORManagerWork(or, password);
	}
	
	public void addcourse(ORManager or, String password) throws ParseException {
		System.out.println("The name of course: ");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String nameCourse = reader.readLine();
			System.out.println("The number of credits: ");
			String numCredit = reader.readLine();
			Course course = new Course(nameCourse, Integer.parseInt(numCredit));
			OR.addCourse(course);
			ORManagerWork(or, password);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	
	public void showMeStudent(ORManager or, String password) throws ParseException {
		System.out.println("Check the file toOR.txt");
		OR.viewStudents();
		ORManagerWork(or, password);
	}
	
	
	public void showMeTeacher(ORManager or, String password) throws ParseException {
		System.out.println("Check the file toOR.txt");
		OR.viewTeachers();
		ORManagerWork(or, password);
	}
	
	public void sendMessageToTeacher(ORManager or, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Who is the recipient ? Email: ");
			String teaName = reader.readLine();
			System.out.println("What's the text ?");
			String text = reader.readLine();
			boolean ok = false;
			for(Faculty faculties: DataBase.facultiess) {
				for(Teacher teacher: faculties.fteachers) {
					if(teacher.emailAdress.Email.equals(teaName)) {
						Message m = new Message(text);
						OR.sendMessage(m, teacher.emailAdress);
						ok = true;
						System.out.println("Success! ");
						ORManagerWork(or, password);
					}
				}
			} if (!ok) {
				System.out.println("Wrong email! ");
				ORManagerWork(or, password);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void viewTorS(ORManager or, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Teacher or Student ?");
			String whowho = reader.readLine();
			if(whowho.toLowerCase().equals("student")) {
				showMeStudent(OR, password);
			} else if(whowho.toLowerCase().equals("teacher")){
				showMeTeacher(OR, password);
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void endiViewOrder(ORManager or, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Who will be in order? ‍Student or Teacher ");
			String whoWill = reader.readLine();
			System.out.println("In what order ? \n1. Sorted by name\n2. Sorted by number of courses");
			String byWhat = reader.readLine();
			if(byWhat.equals("1")) {
				OR.viewBySortedOrder("Name", whoWill.toLowerCase());
				ORManagerWork(or, password);
			} else if (byWhat.equals("2")) {
				OR.viewBySortedOrder("numcourse", whoWill.toLowerCase());
				ORManagerWork(or, password);
			} else {
				System.out.println("Something gone wrong!");
				ORManagerWork(or, password);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// OR MANAGER
	
	public void changePassword(String password, User user) throws ParseException {
//		String xoxo = user.getClass().getName();
		System.out.println("Please enter new password: ");
		String newPassword = null;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			newPassword = reader.readLine();
			
        	if(user.setPassword(newPassword, password)) {
        			System.out.println("Success! Password changed");
        			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
        			if(user.getClass().getSimpleName().equals("ORManager"))
        				ORManagerWork(OR, newPassword);
        			else if(user.getClass().getSimpleName().equals("Teacher"))
        				TeacherWork(T, newPassword);
        			else if(user.getClass().getSimpleName().equals("Student"))
        				StudentWork(st, newPassword);
        			else if(user.getClass().getSimpleName().equals("TechSupportGuy"))
        				TechSupportGuyWork(TSG, newPassword);
        	} else {
        		System.out.println("Do you want to change password with the help of email ?\n1. GO\n2. Nope");
       			String didIt = reader.readLine();
       			if(didIt.equals("1")) {
     				System.out.println("Enter your email: ");
     				String email = reader.readLine();
     				
     				if(user.CheckEmailAdress(email)) {
     					int checc = user.changePassword();
     					System.out.println("Check your email, we send you a number. Our email : kbtu.kz");
     					System.out.println("Enter the number in your email: ");
     					String theNum = reader.readLine();
     					
     					if(checc == Integer.parseInt(theNum) ) {
     						user.changeIt(newPassword);
     						System.out.println("Success! Password changed");
     						System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
     						if(user.getClass().getSimpleName().equals("ORManager"))
     	        				ORManagerWork(OR, newPassword);
     	        			else if(user.getClass().getSimpleName().equals("Teacher"))
     	        				TeacherWork(T, newPassword);
     	        			else if(user.getClass().getSimpleName().equals("Student"))
     	        				StudentWork(st, newPassword);
     	        			else if(user.getClass().getSimpleName().equals("TechSupportGuy"))
     	        				TechSupportGuyWork(TSG, newPassword);
     					} else {
     						System.out.println("Somthing gone wrong! Number is not currect!");
     			
     						if(user.getClass().getSimpleName().equals("ORManager"))
     	        				ORManagerWork(OR, newPassword);
     	        			else if(user.getClass().getSimpleName().equals("Teacher"))
     	        				TeacherWork(T, newPassword);
     	        			else if(user.getClass().getSimpleName().equals("Student"))
     	        				StudentWork(st, newPassword);
     	        			else if(user.getClass().getSimpleName().equals("TechSupportGuy"))
     	        				TechSupportGuyWork(TSG, newPassword);
     						}
     					}
     				
     				} else {
     					System.out.println("Ok, your password is your default password! But this is not safe!");
     					user.notChange();
     					System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
     					if(user.getClass().getSimpleName().equals("ORManager"))
            				ORManagerWork(OR, newPassword);
            			else if(user.getClass().getSimpleName().equals("Teacher"))
            				TeacherWork(T, newPassword);
            			else if(user.getClass().getSimpleName().equals("Student"))
            				StudentWork(st, newPassword);
            			else if(user.getClass().getSimpleName().equals("TechSupportGuy"))
            				TechSupportGuyWork(TSG, newPassword);
     				}
       			
       			} 
        	}  
		catch (IOException e) {
        		e.printStackTrace();
        	}
	}
	
	
	
	
	
	// TEACHER 
	
	public void TeacherWork(Teacher t, String password) throws ParseException {
		T = t; 
		if(password.equals("password")) {
			// changePassword(password, T);
		}
		System.out.println("Hello, Teacher, " + T.getFullName() +"What do you want to do ? \n1. View news\n2. Work with coutrses\n3. Send messedge to the OR Manager\n4. Send order to tech support\n5. Change password\n6. Exit");
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String soWhat = reader.readLine();
			switch(soWhat) {
			case "1":
				// ViewNews(); Create it
				break;
			case "2":
				WorkWithCourse(T, password);
			case "3":
				SendMessageToOR(T, password);
			case "4":
				SendOrderToTech(T, password);
			case "5":
				changePassword(password, T);
			case "6":
				Controller control = new Controller();
				control.go();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void WorkWithCourse(Teacher t, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("What do you want to do? \n1. Put mark\n2. Register to the course\n3. View info about student\4. Add course file\n5. Delete course file\n6. Exit");
			String teachAns = reader.readLine();
			
			switch (teachAns) {
                case "1":
                	putMarks(t, password); // got it
                    break;
                case "2":
                	TeacherRegisterCourses(t, password); // got it
                    break;
                case "3":
                	InfoAboutStudent(t, password); // got it
                    break;
                case "4":
                	addCourseFile(t, password); // got it
                    break;
                case "5":
                	deleteCourseFile(t, password); // got it
                case "6":
                	TeacherWork(t, password); // ok !
                default:
                    System.out.println("Not valid option!");
                    TeacherWork(t, password);
                    break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void putMarks(Teacher t, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter the login of student: ");
			String loginSt = reader.readLine();
			System.out.println("Enter the mark: ");
			String grade = reader.readLine();
			System.out.println("Enter the name of course: ");
			String nameCourse = reader.readLine();
			System.out.println("Please enter the week: ");
			String week = reader.readLine();
			if((Integer.parseInt(week) >= 8 && Integer.parseInt(week) <= 10 )|| (Integer.parseInt(week) >= 13 && Integer.parseInt(week) <= 15) || (Integer.parseInt(week) > 15)) {
				t.putMarks(nameCourse, Integer.parseInt(week), loginSt, Double.parseDouble(grade));
				WorkWithCourse(t, password);
			} else {
				System.out.println("You cant thus the period of putting mark is closed right now! ");
				WorkWithCourse(t, password);
			}
 		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void TeacherRegisterCourses(Teacher t, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Which course do you want to register ? ");
			String nameCourse = reader.readLine();
			boolean ok = t.registerToCourse(nameCourse);
			if(!ok) {
				System.out.println("Sorry, you're not registered to this course because of some problems.\n");
//				System.out.println("----------".repeat(18));
//				TeacherWork(t, password);
			}
			System.out.println("Do you want to see a list of courses ? Maybe there you can find a suitable course");
			String ans = reader.readLine();
			if(ans.toLowerCase().equals("yes")) {
				t.ViewCourses();
				System.out.println("Please check the courses.txt\n");
				System.out.println("----------".repeat(18));
				WorkWithCourse(t, password);
			} else if(ans.toLowerCase().equals("no")){
				System.out.println("\n");
				System.out.println("----------".repeat(18));
				WorkWithCourse(t, password);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void InfoAboutStudent(Teacher t, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter the name of course: ");
			String nameCourse = reader.readLine();
			boolean ok = t.viewInfoStudent(nameCourse);
			if(!ok) {
				System.out.println("You cant see the information about students of this course");
				System.out.println("\n");
				System.out.println("----------".repeat(18));
				System.out.println("\n");
				WorkWithCourse(t, password);
			} else {
				System.out.println("\n");
				System.out.println("----------".repeat(18));
				System.out.println("\n");
				WorkWithCourse(t, password);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addCourseFile(Teacher t, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter the name of course: ");
			String nameCourse = reader.readLine();
			System.out.println("Enter the name of file and type of file: ");
			String nameFile = reader.readLine();
			String type = reader.readLine();
			FileTypes typ = getType(type);
			CourseFile cf = new CourseFile(nameFile, typ, new Date());
			boolean ok = t.AddCoursesFile(cf, nameCourse);
			if(!ok) {
				System.out.println("You cant add this file to the course that you indicated");
				System.out.println("\n");
				System.out.println("----------".repeat(18));
				System.out.println("\n");
				WorkWithCourse(t, password);
			} else {
				System.out.println("The file added to the course that you indicated");
				System.out.println("\n");
				System.out.println("----------".repeat(18));
				System.out.println("\n");
				WorkWithCourse(t, password);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteCourseFile(Teacher t, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter the name of course: ");
			String nameCourse = reader.readLine();
			System.out.println("Enter the name of file and type of file: ");
			String nameFile = reader.readLine();
			String type = reader.readLine();
			FileTypes typ = getType(type);
			CourseFile cf = new CourseFile(nameFile, typ, new Date());
			boolean ok = t.deleteCourseFile(cf, nameCourse);
			if(!ok) {
				System.out.println("You cant delete this file from the course that you indicated");
				System.out.println("\n");
				System.out.println("----------".repeat(18));
				System.out.println("\n");
				WorkWithCourse(t, password);
			} else {
				System.out.println("The file feleted from the course that you indicated");
				System.out.println("\n");
				System.out.println("----------".repeat(18));
				System.out.println("\n");
				WorkWithCourse(t, password);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void SendMessageToOR(Teacher t, String password) throws ParseException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter the email of OR Manager: ");
			String email = reader.readLine();
			System.out.println("What do you want to send? ");
			String text = reader.readLine();
			Message m = new Message(text);
			boolean ok = t.sendMessageToOR(m, email);
			if(!ok) {
				System.out.println("You cant send this message to the OR");
				System.out.println("\n");
				System.out.println("----------".repeat(18));
				System.out.println("\n");
				TeacherWork(t, password);
			} else {
				System.out.println("Success!");
				System.out.println("\n");
				System.out.println("----------".repeat(18));
				TeacherWork(t, password);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void SendOrderToTech(Teacher t, String password) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.println("Enter login of techSupport Guy: ");
			String login = reader.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Student 
	
	public void StudentWork(Student s, String password) throws ParseException {
		st = s;
		if(password.equals("password")) {
			changePassword(password, st);
		}
		System.out.println("Hello," + st.getFirstName() + " " + st.getLastName() +". What do you wnat to do ? \n1. Register to course\n2. Get grade\n3. viewTranscript\n4. exit");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String what = reader.readLine();
			if(what.equals("1")) {
				RegisterToCourse(st, password);
			} else if(what.equals("2")) {
				getGrade(st, password);
			} else if(what.equals("4")) {
				Controller control = new Controller();
				control.go();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void RegisterToCourse(Student st, String password) throws ParseException {
		System.out.println("Course name: ");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String coursen = reader.readLine();
			st.registerCourse(coursen);
			StudentWork(st, password);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getGrade(Student st, String password) throws ParseException {
		System.out.println("Enter which course: ");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String coursen = reader.readLine();
			System.out.println(st.getGrade(coursen).mark);
			StudentWork(st, password);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 	
	// Tech Support Guy
	
	public void TechSupportGuyWork(TechSupportGuy tsg, String password) throws ParseException {
		TSG = tsg;
		if(password.equals("password")) {
			changePassword(password, tsg);
		}
		System.out.println("Hello, tech support guy. What do you want to do? \n1. Get ordeers\n2. getDoneOrders\n3. Exit");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String what = reader.readLine();
			if(what.equals("1")) {
				getOrder(TSG, password);
			} else if(what.equals("2")) {
				getDoneOrders(TSG, password);
			} else if(what.equals("4")) {
				Controller control = new Controller();
				control.go();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getOrder(TechSupportGuy tsg, String password) throws ParseException {
		System.out.println(tsg.viewOrders());
		TechSupportGuyWork(tsg, password);
	}
	
	public void getDoneOrders(TechSupportGuy tsg, String password) throws ParseException {
		for(Order orders: tsg.viewOrders()) {
			if(orders.status == true) {
				System.out.println(orders);
			}
		} TechSupportGuyWork(tsg, password);
	} 
	
	
	public FileTypes getType(String type) {
		if(type.toLowerCase().equals("txt")) {
			return FileTypes.txt;
		} else if(type.toLowerCase().equals("java")) {
			return FileTypes.java;
		} else if(type.toLowerCase().equals("docs")) {
			return FileTypes.docs;
		} else if(type.toLowerCase().equals("pdf")) {
			return FileTypes.pdf;
		} else if(type.toLowerCase().equals("png")) {
			return FileTypes.png;
		} else if(type.toLowerCase().equals("ppt")) {
			return FileTypes.ppt;
		} 
		return FileTypes.jpeg;
	}
}
