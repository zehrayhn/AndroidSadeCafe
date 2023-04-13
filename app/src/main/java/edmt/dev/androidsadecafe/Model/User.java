package edmt.dev.androidsadecafe.Model;

public class User {
    private String Name;
    private  String Password;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public User() {
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User(String name, String password){
        Name=name;
        Password=password;

    }
}
