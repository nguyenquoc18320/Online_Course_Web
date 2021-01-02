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
public class PartDB {

    public static boolean insertPart(Part part) {

        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(part);
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

    public static boolean updatePart(Part part) {
        boolean result;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.merge(part);
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

    public static boolean partExists(int courseid, int chapid, int partid) {
        Part p = getPartByPrimaryKey(courseid, chapid, partid);
        return p != null;
    }

    public static boolean deletePart(int courseid, int chapid, int partid) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();
        try {
            Query query = entityManager.createNativeQuery("DELETE FROM part where CourseId=" + courseid + " and ChapId= " + chapid
                    + " and PartId =" + partid + ";");
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

    //get Chap defined by primay key
    public static Part getPartByPrimaryKey(int courseid, int chapid, int partid) {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select p from Part p where p.CourseId = :courseid and p.ChapId = :chapid and p.PartId = :partid";

        TypedQuery<Part> query = entityManager.createQuery(queryS, Part.class);
        query.setParameter("courseid", courseid);
        query.setParameter("chapid", chapid);
        query.setParameter("partid", partid);

        Part part;
        try {
            part = query.getSingleResult();
        } catch (Exception ex) {
            part = null;
        } finally {
            entityManager.close();
        }
        return part;
    }

    //get all part of a chap 
    public static List<Part> getAllPartOfChap( int courseid, int chapid)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select p from Part p where p.CourseId = :courseid and p.ChapId = :chapid";
        
        TypedQuery<Part> q = entityManager.createQuery(queryS, Part.class);
        q.setParameter("courseid", courseid);
        q.setParameter("chapid", chapid);
        
        List<Part> partList ;
        
        try
        {
            partList = q.getResultList();
            if( partList==null || partList.isEmpty())
                partList=null;
        }
        finally
        {
            entityManager.close();
        }
        return partList;
    }
}
