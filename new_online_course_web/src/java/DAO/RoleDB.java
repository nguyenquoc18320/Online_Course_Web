/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;

import java.util.*;
import javax.persistence.*;
/**
 *
 * @author TRAN VAN AN
 */
public class RoleDB {
    
    public static List<Role> GetRoles()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Role r";
        TypedQuery<Role> q = em.createQuery(qString, Role.class);
        List<Role> roles = null;
        try{
            roles = q.getResultList();
            if (roles == null || roles.isEmpty())
                roles = null;
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }finally{
            em.close();
        }
        return roles;
    }
    
    public static Role GetRoleInListByRoleId(List<Role> roles, String roleId)
    {
        for (int i = 0; i < roles.size(); i++)
            if (roles.get(i).getRoleId().equals(roleId))
                return roles.get(i);
        return null;
    }
    
    public static Role GetRoleInListByRoleName(List<Role> roles, String roleName)
    {
        for (int i = 0; i < roles.size(); i++)
            if (roles.get(i).getRoleName().equals(roleName))
                return roles.get(i);
        return null;
    }
}
