/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author A556U
 */
@Entity
public class Part {
    @Id
    private int CourseId;
    @Id
    private int ChapId;
    @Id
    private int PartId;
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    public Part()
    {
        
    }

    public Part(int CourseId, int ChapId, int PartId, String Name) {
        this.CourseId = CourseId;
        this.ChapId = ChapId;
        this.PartId = PartId;
        this.Name = Name;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    public int getChapId() {
        return ChapId;
    }

    public void setChapId(int ChapId) {
        this.ChapId = ChapId;
    }

    public int getPartId() {
        return PartId;
    }

    public void setPartId(int PartId) {
        this.PartId = PartId;
    }
    
   
}
