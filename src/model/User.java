package model;

public class User {
    protected int id;
    protected String name;
    protected String email;
    protected String country;

    public User(){}

    public User (int id,String name,String email,String country){
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.country = country;
    }

    public User(String name, String email, String country) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void add(User user) {
    }
}
