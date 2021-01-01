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
public class FAQDB {
    public static boolean insertFAQ(FAQ faq) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(faq);
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
    
    public static boolean updateFAQ(FAQ faq) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.merge(faq);
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
    
    public static boolean deleteFAQ(int courseid, int faqid)
    {
            boolean result = true;
            EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
            EntityTransaction tran = entityManager.getTransaction();
            tran.begin();
       try {
           FAQ faq = getFAQByFK(courseid, faqid);
           Query query = entityManager.createNativeQuery("DELETE FROM faq where CourseId=" + courseid + " and FAQId= " + faqid+";");
            query.executeUpdate();
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
    
     public static FAQ getFAQByFK(int courseid, int FAQId) {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT f from FAQ f where f.CourseId = :courseid and f.FAQId = :faqid";
        TypedQuery<FAQ> query = entityManager.createQuery(queryS, FAQ.class);
        query.setParameter("courseid", courseid);
        query.setParameter("faqid", FAQId);

         FAQ faq =null;
        try {
            faq= (FAQ)query.getSingleResult();
        } catch (Exception ex) {
            
        }
        finally
        {
            entityManager.close();
        }
        return faq;
    }
    
     public static boolean FAQExists(int courseid, int faqid)
    {
        FAQ u = getFAQByFK(courseid, faqid);
        return u!=null;
    }
     
    public static List<FAQ> getAllFAQOfCourse(int courseid)
    {
        EntityManager entityManager= DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT f from FAQ f where f.CourseId = :courseid";
        
        TypedQuery<FAQ> q = entityManager.createQuery(queryS, FAQ.class);
        q.setParameter("courseid", courseid);
        
        List<FAQ> faqList ;
        
        try
        {
            faqList = q.getResultList();
            if(faqList==null || faqList.isEmpty())
                faqList=null;
        }
        finally
        {
            entityManager.close();
        }
        return faqList;
        
    }
}
