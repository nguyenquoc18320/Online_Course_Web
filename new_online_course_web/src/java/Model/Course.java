/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
/**
 *
 * @author TRAN VAN AN
 */
@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CourseId;
    private String Name;
    private String Objective;
    private int UserId;
    private Timestamp ModifiedDate;
    private boolean Approved;
    private String Document;

    public Course() {
    }

    public Course(String Name, String Objective, int user, Timestamp ModifiedDate, boolean Approved, String Document) {
        this.Name = Name;
        this.Objective = Objective;
        this.UserId = user;
        this.ModifiedDate = ModifiedDate;
        this.Approved = Approved;
        this.Document = Document;
    }

    public Course(int CourseId, String Name, String Objective, int user, Timestamp ModifiedDate, boolean Approved, String Document) {
        this.CourseId = CourseId;
        this.Name = Name;
        this.Objective = Objective;
        this.UserId = user;
        this.ModifiedDate = ModifiedDate;
        this.Approved = Approved;
        this.Document = Document;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getObjective() {
        return Objective;
    }

    public void setObjective(String Objective) {
        this.Objective = Objective;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int user) {
        this.UserId = user;
    }

    public Timestamp getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Timestamp ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public boolean isApproved() {
        return Approved;
    }

    public void setApproved(boolean Approved) {
        this.Approved = Approved;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String Document) {
        this.Document = Document;
    }

    
}
