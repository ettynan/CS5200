package blog.model;


/**
 * User concrete class depicting the user for the application
 */
public class Users {
    protected String UserName;
    protected String Password;
    protected String FirstName;
    protected String LastName;
    protected String Email;
    protected String Phone;

    public Users(String userName, String password, String firstName, String lastName, String email, String phone) {
        this.UserName = userName;
        this.Password = password;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Phone = phone;
    }

    public Users(String userName) {
        this.UserName = userName;
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }


}
