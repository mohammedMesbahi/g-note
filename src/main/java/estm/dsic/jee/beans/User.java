package estm.dsic.jee.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable{
    
    @Id
    private long id;
    private String name;
    private String email;
    private String password;
    private Boolean admin = false;
    private Boolean verified = false;

    public Boolean isAdmin(){
        return admin;
    }
    public void isAdmin(Boolean admin){
        this.admin =admin;
    }
    public Boolean isVerified() {
        return verified;
    }
    public void isVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public User() {
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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
