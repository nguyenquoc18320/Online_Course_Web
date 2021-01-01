/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
/**
 *
 * @author TRAN VAN AN
 */
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int UserId;
    private String Name;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DateOfBirth;
    private String Email;
    private boolean Gender;
    private String Phone;
    private String Role;
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp CreatedDate;

    public User() {
    }

    public User(int UserId, String Name, Date DateOfBirth, String Email, boolean Gender, String Phone, String Role, Timestamp CreatedDate) {
        this.UserId = UserId;
        this.Name = Name;
        this.DateOfBirth = DateOfBirth;
        this.Email = Email;
        this.Gender = Gender;
        this.Phone = Phone;
        this.Role = Role;
        this.CreatedDate = CreatedDate;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public Timestamp getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Timestamp CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
    
    
}
