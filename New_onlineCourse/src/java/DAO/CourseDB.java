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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author TRAN VAN AN
 */
public class CourseDB {
    public static Course GetCourseByCourseId(int courseId)
    { 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Course course = em.find(Course.class, courseId);
            return course;
        }finally{
            em.close();
        }
    }
    
    public static List<Course> GetCourseByUserId(int userId)
    { 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Course AS c WHERE c.UserId = :userId";
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
        q.setParameter("userId", userId);
        List<Course> courses = null;
        try{
            courses = q.getResultList();
        }catch(NoResultException ex)
        {
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return courses;
    }
    public static boolean insertCourse(Course course) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(course);
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

     public static boolean updateCourse(Course course) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.merge(course);
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
     
    public static boolean deleteCourse(Course course)
    {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.remove(course);
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
    
    public static int getMaxCourseID() {
       int max=0;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT max(courseid) from Course";

        try {
             max = Integer.parseInt(entityManager.createNativeQuery(queryS).getSingleResult().toString());

        } catch (Exception e) {
            max=0;
        } finally {
            entityManager.close();
        }
        return max;
    }

    public static Course getCourseById(int courseid) {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT c from Course c where c.CourseId = :courseid";
        TypedQuery<Course> query = entityManager.createQuery(queryS, Course.class);
        query.setParameter("courseid", courseid);

         Course course =null;
        try {
            course= query.getSingleResult();
        } catch (Exception ex) {
            
        }
        finally
        {
            entityManager.close();
        }
        return course;
    }
    
    public static boolean courseExists(int courseid)
    {
        Course u = getCourseById(courseid);
        return u!=null;
    }
    
     public static List<Exercise> getAllPartOfChap( int courseid, int chapid, int partid)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from Exercise e where e.CourseId = :courseid and e.ChapId = :chapid and e.PartId = :partid ";
        
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
