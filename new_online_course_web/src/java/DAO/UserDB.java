/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<User> usersFilter = new ArrayList<>();
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
    
    public static List<User> GetUsersByTypeDate(String typeDate, java.util.Date date)
    {
        java.sql.Date dateStart = new java.sql.Date(UserDB.GetDateStart(typeDate, date).getTime());
        java.sql.Date dateEnd = new java.sql.Date(UserDB.GetDateEnd(typeDate, date).getTime());
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM User u WHERE CAST(u.CreatedDate AS DATE) BETWEEN CAST(:dateStart AS DATE) AND CAST(:dateEnd AS DATE)";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("dateStart", dateStart);
        q.setParameter("dateEnd", dateEnd);
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
    
    public static Date GetDateStart( String typeDate, Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (typeDate.equals("week"))
        {
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, - dayOfWeek + 1);
        }
        else
        {
            c.set(Calendar.DATE, 1);
        }
        return (Date)c.getTime();
    }
    
     public static Date GetDateEnd( String typeDate, Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (typeDate.equals("week"))
        {
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            c.add(Calendar.DATE, 7 - dayOfWeek);
        }
        else
        {
            c.add(Calendar.MONTH, 1);
            c.set(Calendar.DATE, 1);
            c.add(Calendar.DATE, -1);
        }
        return (Date) c.getTime();
    }
    
    public static Map<String, Integer> ReportRegistrationAccount(List<User> users)
    {
        Map<String, Integer> map = new HashMap<>();
        users.forEach((User user) -> {
            String key  = (new java.sql.Date(user.getCreatedDate().getTime())).toString();
            if(map.containsKey(key)){
                map.replace(key, map.get(key), map.get(key) + 1);
            }else{
                map.put(key, 1);
            }
        });
        return map;
    }
    
}

 