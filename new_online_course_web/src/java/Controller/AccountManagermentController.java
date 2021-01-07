package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.*;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRAN VAN AN
 */
@WebServlet(urlPatterns = {"/account-managerment"})
public class AccountManagermentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        String url = "/Views/Pages/Admin/account_managerment.jsp";
        
        
        String accountType = request.getParameter("accountType");
        if (accountType == null)
            accountType = "all";
        request.setAttribute("AccountType", accountType);
        String accountState = request.getParameter("accountState");
        if (accountState == null)
            accountState = "all";
        request.setAttribute("AccountState", accountState);
        String search = request.getParameter("search");
        if (search == null)
            search = "";
        request.setAttribute("Search", search);
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");
        if (user != null)
        {
            String statusString = request.getParameter("status");
            String userIdString = request.getParameter("userId");
            if (statusString != null && userIdString != null)
            {
                boolean status = statusString.equals("true");
                int userId = Integer.parseInt(userIdString);
                User userUpdate = UserDB.GetUserByUserId(userId);
                Account account = userUpdate.getAccount();
                if (account.isStatus() != status)
                {
                    boolean isUpdated = AccountDB.UpdateStatusByUserId(account.getAccountId(), !account.isStatus());
                }
            }
            
            List<Role> roles = RoleDB.GetRolesByRole(accountType);
            //List<Account> accounts = AccountDB.GetAccountsByStatus(accountState);
            List<User> users = null;
            if (roles.size() == 1)
            {
                Role roleFilter = roles.get(0);
                users = UserDB.GetUsersByFilter(roleFilter, search);
            }
            else
            {
                users = UserDB.GetUsers();
            }
            
            users = UserDB.GetUsersByStatus(users, accountState);
            
            request.setAttribute("Users", users);
//            List<Role> roles = RoleDB.GetRoles();
//            request.setAttribute("Roles", roles);
//            Role role = RoleDB.GetRoleInListByRoleName(roles, accountType);
//            int roleId = role == null ? 0 : role.getRoleId();
//            List<User> users = UserDB.GetUsersByFilter(roleId, accountState, search);
//            request.setAttribute("Users", users);
//            List<Account> accounts = AccountDB.GetAccounts();
//            request.setAttribute("Accounts", accounts);
            String errorAddAdmin = (String)request.getAttribute("ErrorAddAdmin");
            if (errorAddAdmin == null)
                errorAddAdmin = "";
            request.setAttribute("ErrorAddAdmin", errorAddAdmin);
        }
        else
        {
            url = "/sign-in";
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
