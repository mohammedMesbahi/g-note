package estm.dsic.jee.beans;

import java.io.Serializable;

public class User implements Serializable{
    
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean admin ;
    private Boolean verified ;
    public User(){
        this.admin = false;
        this.verified = false;
    }

    public Boolean isAdmin(){
        return admin;
    }
    public Boolean isVerified() {
        return verified;
    }
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    public User(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public User(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ",name='" + getName() + "' "+
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
