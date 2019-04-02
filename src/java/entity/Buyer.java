package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Buyer  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ) 
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private int money;

    public Buyer() {
    }

    public Buyer(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    

    @Override
    public String toString() {
        return "Buyer{" + "email=" + email + ", name=" + name + ", surname=" + surname + ", money=" + money +'}';
    }
    
    
}
