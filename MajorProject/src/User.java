import java.io.Serializable;

public abstract class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lastName;
    private String firstName;
    private int id;
    private String Login;
    private int password;
    email emailAdress;
    private String defaultPassword = "password";
    int[] p_pow = new int[20];
    
    public User() { } 
    
    public User(String firstName, String lastName, int id, String c) { 
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.id = id;
    	createEmailAdress(firstName, lastName);
    	this.Login = this.firstName.toLowerCase().charAt(0) + c + this.lastName.toLowerCase();
    	DataBase.users.add(this);
    }
    
    public void setPasswordToAdmin(String newPassword, Admin a) {
    	this.password = hash_(newPassword);
    }
    
    public String getLogin() {
    	return this.Login;
    }

    public boolean setPassword(String newpassword, String oldPassword) {
    	if(CheckPassword(oldPassword)) {
    		this.password =  hash_(newpassword);
    		return true;
    	} return false;
    }
    
    public void notChange() {
    	this.password = hash_(this.defaultPassword);
    }
    	
    	
    public int changePassword() { // with help of email
    	int randomText = (int) Math.random() * 100;
    	emailAdress.recieveEmail(new Message(Integer.toString(randomText)), new email("kbtu.kz"));
    	return randomText;
    }
    
    
    public void changeIt(String newPassword) { // change password via email or phone num
    	this.password = hash_(newPassword);
    }
    		
    public int getId() {
        return this.id;
    }


    public String getLastName() {
        return this.lastName;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public email getEmail() {
        return this.emailAdress;
    }

    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + "\n" + "ID: " + this.id + "\n" + "Email: " + this.emailAdress + "\n";
    }
    
    public void calc_pow() {
        this.p_pow[0] = 1;
        for (int i = 1; i < 20; i++)
            this.p_pow[i] = this.p_pow[i - 1] * 31;
    }
    
    public int hash_(String pass) {
    	calc_pow();
    	int sum = 0;
        for (int i = 0; i < pass.length(); i++) {
            sum += pass.charAt(i) * this.p_pow[i];
        }
        return sum;
    }

    public boolean CheckPassword(String oldPassword) { // we need a hash method in this class to check the password
    	if(hash_(oldPassword) == this.password || oldPassword.equals(defaultPassword)) {
    		return true;
    	}
        return false;
    }

    public void setLogin(String login) {
    	this.Login = login;
    }

    public boolean CheckEmailAdress(String emailAdress) {
    	if((emailAdress).equals(this.emailAdress.Email)) {
    		return true;
    	}
        return false;
    }
    
    public void createEmailAdress(String firstName, String lastName) {
    	String adress = firstName.toLowerCase().charAt(0) + '_' + lastName.toLowerCase() + "@kbtu.kz";
    	this.emailAdress = new email(adress);
    }

}
