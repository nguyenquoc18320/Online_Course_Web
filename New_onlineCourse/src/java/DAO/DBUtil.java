/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.*;

/**
 *
 * @author TRAN VAN AN
 */
public class DBUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("New_onlineCoursePU");
    public static EntityManagerFactory getEmFactory()
    {
        return emf;
    }
}
