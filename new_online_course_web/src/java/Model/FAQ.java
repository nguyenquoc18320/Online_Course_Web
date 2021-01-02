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
public class FAQ {
    @Id 
    private int CourseId;
    
    @Id
    private int FAQId;
    private String Question;
    private String Answer;
    
    public FAQ(){}

    public FAQ(int CourseId, int FAQId, String Question, String Answer) {
        this.CourseId = CourseId;
        this.FAQId = FAQId;
        this.Question = Question;
        this.Answer = Answer;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    public int getFAQId() {
        return FAQId;
    }

    public void setFAQId(int FAQId) {
        this.FAQId = FAQId;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }
      
    
            
}

