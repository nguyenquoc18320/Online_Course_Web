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
public class Exercise {
    @Id 
    private int CourseId;
    @Id 
    private int ChapId;
    @Id
    private int PartId;
    @Id
    private int ExerciseId;
    private String Question;
    private String AnswerA;
    private String AnswerB;
    private String AnswerC;
    private String AnswerD;
    private String CorrectAnswer;
    private String Explaination;
    
    public Exercise(){}

    public Exercise(int CourseId, int ChapId, int PartId, int ExeriseId, String Question, String AnswerA, String AnswerB, String AnswerC, String AnsertD, String CorrectAnswer, String Explanation) {
        this.CourseId = CourseId;
        this.ChapId = ChapId;
        this.PartId = PartId;
        this.ExerciseId = ExeriseId;
        this.Question = Question;
        this.AnswerA = AnswerA;
        this.AnswerB = AnswerB;
        this.AnswerC = AnswerC;
        this.AnswerD = AnsertD;
        this.CorrectAnswer = CorrectAnswer;
        this.Explaination = Explanation;
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

    public int getExerciseId() {
        return ExerciseId;
    }

    public void setExerciseId(int ExerciseId) {
        this.ExerciseId = ExerciseId;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String AnswerA) {
        this.AnswerA = AnswerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String AnswerB) {
        this.AnswerB = AnswerB;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public void setAnswerC(String AnswerC) {
        this.AnswerC = AnswerC;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public void setAnswerD(String AnswerD) {
        this.AnswerD = AnswerD;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
    }

    public String getExplaination() {
        return Explaination;
    }

    public void setExplaination(String Explaination) {
        this.Explaination = Explaination;
    }

    
}
