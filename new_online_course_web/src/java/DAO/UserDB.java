/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import DAO.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author TRAN VAN AN
 */
public class UserDB {
    
    public static boolean InsertAccount(User user)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        boolean isInserted = false;
        try{
            trans.begin();
            em.persist(user);
            trans.commit();
            isInserted = true;
        }catch(Exception ex)
        {
            trans.rollback();
            return false;
        }finally{
            em.close();
        }
        return isInserted;
    }
    
     public static List<User> GetUsers()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User AS u";
            
        TypedQuery<User> q = em.createQuery(qString, User.class);
       
        List<User> users = null;
        try{
            users = q.getResultList();
            if (users == null || users.isEmpty())
                users = null;
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            em.close();
        }
        return users;
    }
    
    public static List<User> GetUsersByStatus(List<User> users, String status)
    {
        if (users == null)
            return users;
        if (status.equals("all"))
            return users;
        boolean isActivated = AccountDB.GetStatusByState(status);
        List<User> usersFilter = new ArrayList<User>();
        for(int i = 0; i < users.size(); i++)
            if (users.get(i).getAccount().isStatus() == isActivated)
                usersFilter.add(users.get(i));
        return usersFilter;
    }
     
     public static User GetUserByUserId(int userId)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            User user = em.find(User.class, userId);
            return user;
        }finally{
            em.close();
        }
    }
    
    public static User GetUserByEmail(String email)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u WHERE u.Email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        User user = null;
        try{
            user = q.getSingleResult();
        }catch(NoResultException ex)
        {
            System.out.println("Kết nối thất bại! " + ex.getMessage());
            return null;
        }
        finally{
            em.close();
        }
        return user;
    }
    
    public static User GetUserByPhone(String phone)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User AS u WHERE u.Phone = :phone";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("phone", phone);
        User user = null;
        try{
            user = q.getSingleResult();
        }catch(NoResultException ex)
        {
            System.out.println("Kết nối thất bại!");
            return null;
        }
        finally{
            em.close();
        }
        return user;
    }
    
    public static List<User> GetUsersByFilter(Role role, String search)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "";
        if (!search.equals(""))
        {
            qString = "SELECT u FROM User AS u WHERE CONCAT(u.UserId, u.Name, u.Email) LIKE '%" + search + "%'";
        }
        else
        {
            qString = "SELECT u FROM User u WHERE u.role = :role";
        }
        TypedQuery<User> q = em.createQuery(qString, User.class);
        if (search.equals(""))
        {
            q.setParameter("role", role);
        }
       
        List<User> users = null;
        try{
            users = q.getResultList();
            if (users == null || users.isEmpty())
                users = null;
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            em.close();
        }
        return users;
    }
    
    public static boolean UpdateUser(int userId, String name, Date dateOfBirth, boolean gender, String email, String phone)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        String qString = "UPDATE User SET Name = :name , DateOfBirth = :dateOfBirth , Gender = :gender , Email = :email , Phone = :phone WHERE UserId = :userId";
        Query q = em.createQuery(qString);
        q.setParameter("name", name);
        q.setParameter("dateOfBirth", dateOfBirth);
        q.setParameter("gender", gender);
        q.setParameter("email", email);
        q.setParameter("phone", phone);
        q.setParameter("userId", userId);
        int count = 0;
        try{
            trans.begin();
            count = q.executeUpdate();
            trans.commit();
        }catch(Exception ex)
        {
            trans.rollback();
        }finally{
            em.close();
        }
        return count > 0;
    }
    
}
