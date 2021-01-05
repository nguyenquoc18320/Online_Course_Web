/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author TRAN VAN AN
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String RoleId;
    private String RoleName;

    public Role() {
    }

    public Role(String RoleId, String RoleName) {
        this.RoleId = RoleId;
        this.RoleName = RoleName;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String RoleId) {
        this.RoleId = RoleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    
    
}
