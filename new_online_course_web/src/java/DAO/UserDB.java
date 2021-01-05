/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import DAO.*;
import static DAO.AccountDB.GetStatusByState;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author TRAN VAN AN
 */
public class UserDB {
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
        String qString = "SELECT u FROM User AS u WHERE u.Email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        User user = null;
        try{
            user = q.getSingleResult();
        }catch(NoResultException ex)
        {
            System.out.println("Kết nối thất bại!");
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
        }
        finally{
            em.close();
        }
        return user;
    }
    
    public static List<User> GetUsersByFilter(String role, String state, String search)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "";
        if (!"".equals(search))
            qString = "SELECT u FROM User AS u WHERE CONCAT(u.UserId, u.Name, u.Email) LIKE '%" + search + "%'";
        else if (role.equals("0"))
        {
            if (state.equals("all"))
                qString = "SELECT u FROM User AS u";
            else
            {
                boolean status = AccountDB.GetStatusByState(state);
                qString = "SELECT u FROM User AS u INNER JOIN Account as a ON u.UserId = a.UserId WHERE a.Status = " + status;
            }
        }
        else
        {
            if (state.equals("all"))
                qString = "SELECT u FROM User AS u WHERE u.Role = " + role;
            else
            {
                boolean status = AccountDB.GetStatusByState(state);
                qString = "SELECT u FROM User AS u INNER JOIN Account as a ON u.UserId = a.UserId WHERE u.Role = " + role + " AND a.Status = " + status;
            }
        }
            
        
        TypedQuery<User> q = em.createQuery(qString, User.class);
//        if (!role.equals("0") && search.equals(""))
//            q.setParameter("role", role);
       
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
    
    public static boolean InsertUser(User user, Account account)
    {
        boolean isInserted = false;
        int maxUserId = MaxUserId();
        if (maxUserId < 0)
            return false;
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            em.persist(user);
            em.persist(account);
            isInserted = true;
            trans.commit();
        }catch(Exception e)
        {
            isInserted = false;
            System.out.println("Thêm user thất bại!");
            trans.rollback();
        }finally{
            em.close();
        }
        return isInserted;
    }
    
    public static int MaxUserId()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT Max(UserId) FROM User";
        int max = -1;
        try{
            String UserId = em.createNativeQuery(qString).getSingleResult().toString();
            max = Integer.parseInt(UserId.toString());
        }catch(NoResultException ex)
        {
            max = -1;
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return max;
    }
}
