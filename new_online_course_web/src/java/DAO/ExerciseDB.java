/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author A556U
 */
public class ExerciseDB {
    public static boolean insertExercise(Exercise exercise) {

        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(exercise);
            tran.commit();
        } catch (Exception e) {
            System.out.println(e);
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
    public static boolean updateExercise(Exercise exercise) {
        boolean result;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.merge(exercise);
            tran.commit();
            result = true;
        } catch (Exception ex) {
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
     public static Exercise getExerciseByExecise(int courseid, int chapid, int partid, int exerciseid) {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from Exercise e where e.CourseId = :courseid and e.ChapId = :chapid and e.PartId = :partid and e.ExerciseId= :exerciseid";

        TypedQuery<Exercise> query = entityManager.createQuery(queryS, Exercise.class);
        query.setParameter("courseid", courseid);
        query.setParameter("chapid", chapid);
        query.setParameter("partid", partid);
        query.setParameter("exerciseid", exerciseid);

        Exercise exercise;
        try {
            exercise = query.getSingleResult();
        } catch (Exception ex) {
            exercise = null;
        } finally {
            entityManager.close();
        }
        return exercise;
    }
     
     public static boolean exericseExists(int courseid, int chapid, int partid,int exerciseid) {
        Exercise e = getExerciseByExecise(courseid, chapid, partid, exerciseid);
        return e != null;
    }

    public static boolean deleteExercise(int courseid, int chapid, int partid, int exerciseid) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();
        try {
            Query query = entityManager.createNativeQuery("DELETE FROM Exercise where CourseId= " + courseid + " and ChapId= " + chapid
                    + " and PartId =" + partid + " and Exerciseid = "+exerciseid+";");
            query.executeUpdate();
            tran.commit();
        } catch (Exception ex) {
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
    //get all exercise of a part 
    public static List<Exercise> getAllExercisePartOfPart( int courseid, int chapid, int partid)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from Exercise e where e.CourseId = :courseid and e.ChapId = :chapid and e.PartId = :partid";
        
        TypedQuery<Exercise> q = entityManager.createQuery(queryS, Exercise.class);
        q.setParameter("courseid", courseid);
        q.setParameter("chapid", chapid);
        q.setParameter("partid", partid);
        
        List<Exercise> exerciseList ;
        
        try
        {
            exerciseList = q.getResultList();
            if( exerciseList==null || exerciseList.isEmpty())
                exerciseList=null;
        }
        finally
        {
            entityManager.close();
        }
        return exerciseList;
    }

}
