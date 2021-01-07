/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.*;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRAN VAN AN
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/sign-up"})
public class SignUpController extends HttpServlet {

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
        
        String url = "/Views/Pages/Login/signUp.jsp";
        String name = request.getParameter("name");
        if (name == null)
            name = "";
        request.setAttribute("Name", name);
        String birthDate = request.getParameter("birthDate");
        if (birthDate == null)
            birthDate = "2010-01-01";
        request.setAttribute("BirthDate", birthDate);
        
        String gender = request.getParameter("gender");
        request.setAttribute("Gender", gender);
        String email = request.getParameter("email");
        if (email == null)
            email = "";
        request.setAttribute("Email", email);
        String phone = request.getParameter("phone");
        if (phone == null)
            phone = "";
        request.setAttribute("Phone", phone);
        String role = request.getParameter("role");
        request.setAttribute("Role", role);
        String password = request.getParameter("password");
        if (password == null)
            password = "";
        request.setAttribute("Password", password);
        String rePassword = request.getParameter("rePassword");
        if (rePassword == null)
            rePassword = "";
        request.setAttribute("RePassword", rePassword);
        
        if (!"".equals(name.trim()))
        {
            if (password.trim().equals(rePassword.trim()))
            {
               
                if (UserDB.GetUserByEmail(email) == null)
                {
                    if (UserDB.GetUserByPhone(phone) == null)
                    {
                        Timestamp createDate = new Timestamp(System.currentTimeMillis());
                        boolean isInserted = false;
                        boolean isMale = "male".equals(gender);
                        int roleId = "teacher".equals(role) ? 2 : 3 ;
                        Role roleAdd = RoleDB.GetRoleByRoleId(roleId);
                        try{
                            Date birthDateUser = Date.valueOf(birthDate); 
                            Account account = new Account(password, true);
                            boolean isInsertedAcc = AccountDB.InsertAccount(account);
                            int idAccount = AccountDB.MaxAccountId();
                            account.setAccountId(idAccount);
                            User user = new User(name, birthDateUser, email, isMale, phone, roleAdd , account, createDate);
                            isInserted = UserDB.InsertAccount(user);
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        if (!isInserted)
                        {
                            // insert is fail
                        }
                        else
                        {
                            url = "/sign-in";
                        }
                      
                    }
                    else
                    {
                        //Phone is exist
                    }
                }
                else
                {
                    //Email is exist
                }
            }
            else
            {
                //password is not equals with repassword
            }
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
