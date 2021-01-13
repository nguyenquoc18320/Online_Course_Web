/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Chap;
import Model.Course;
import DAO.DBUtil;
import Model.Excercise;
import Model.Part;
import Model.StudentExcercise;
import Model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author A556U
 */
public class StudentExcerciseDB {
    public static boolean insert(StudentExcercise stEx)
    {
        boolean result = true;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        
        try
        {
            em.persist(stEx);
            tran.commit();
        }
        catch(Exception ex)
        {
            tran.rollback();
            result =false;
        }
        finally
        {
            em.close();
        }
        return result;
        
    }
    
    
    public static boolean update(StudentExcercise stEx)
    {
        boolean result = true;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        
        try
        {
            em.merge(stEx);
            tran.commit();
        }
        catch(Exception ex)
        {
            tran.rollback();
            result =false;
        }
        finally
        {
            em.close();
        }
        return result;
        
    }
    
     public static boolean delete(StudentExcercise stEx)
    {
        boolean result = true;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        
        try
        {
            em.remove(em.merge(stEx));
            tran.commit();
        }
        catch(Exception ex)
        {
            tran.rollback();
            result =false;
        }
        finally
        {
            em.close();
        }
        return result;        
    }
     
    public static List<StudentExcercise> getAllAnswerOfPart(User user, Course course, Chap chap, Part part)
    {
      
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from StudentExcercise e where e.course = :course and e.chap = :chap and e.part = :part";
        
        
        TypedQuery<StudentExcercise> q = entityManager.createQuery(queryS, StudentExcercise.class);
        q.setParameter("course", course);
        q.setParameter("chap", chap);
        q.setParameter("part", part);
        
        List<StudentExcercise> resultList ;
        
        try
        {
            resultList = q.getResultList();
            if( resultList==null || resultList.isEmpty())
                resultList=null;
        }
        finally
        {
            entityManager.close();
        }
        return resultList;
        
        
    }
    
    public static int getMaxTime(User user, Course course, Chap chap, Part part )
    {
      
       List<StudentExcercise> ansList = getAllAnswerOfPart(user, course, chap, part);
             
        int max=0;
       if(ansList!=null)
       {
           for(int i=0; i<ansList.size(); i++)
           {
               
               if(ansList.get(i).getTime()>max)
                   max=ansList.get(i).getTime();
           }
       }
  
        return max;
    }
    
    
}

