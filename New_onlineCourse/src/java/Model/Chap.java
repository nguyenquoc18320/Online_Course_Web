/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 *
 * @author A556U
 */
@Entity
public class Chap implements Serializable  {

    @Id
    private int CourseId;
    @Id
    private int ChapId ;
    private String Name;

     public Chap() {
    }

    public Chap(int CourseId, int ChapId, String Name) {
        this.CourseId = CourseId;
        this.ChapId = ChapId;
        this.Name = Name;
    }
       

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    public int getChapid() {
        return ChapId;
    }

    public void setChapid(int ChapId) {
        this.ChapId = ChapId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
