/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ExerciseDB;
import DAO.PartDB;
import Model.Exercise;
import Model.Part;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author A556U
 */
public class Display_Exercise_Student extends HttpServlet {

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
        Part part = null;
//        int courseid = 1;
//        int chapid = 1;
//        int partid = 1;
        String url = "/Views/Pages/Course/exercise_student.jsp";
        try {
            int courseid = Integer.parseInt(request.getParameter("courseid"));
            int chapid = Integer.parseInt(request.getParameter("chapid"));
            int partid = Integer.parseInt( request.getParameter("partid"));

           

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("User");

            String requirement = request.getParameter("requirement");

            if (user == null) {
                url = "/sign-in";
            } else {
                //không phải là tạo kh
//                try {
//                    courseid = Integer.parseInt(request.getParameter("courseid"));
//                    chapid = Integer.parseInt(request.getParameter("chapid"));
//                    partid = Integer.parseInt(request.getParameter("partid"));
//                } catch (Exception ex) {
//
//                }

                part = PartDB.getPartByPrimaryKey(courseid, chapid, partid);

                int maxExercise = 0;

                if (part == null) {
                    request.setAttribute("message", "Không tìm thấy phần bài học để thêm bài tập!");
                    url = "/" + (String) request.getParameter("previousPage");
                } else {

                    session.setAttribute("part", part);

                    List<Exercise> exerciseList = ExerciseDB.getAllExercisePartOfPart(courseid, chapid, partid);
                    if (exerciseList != null) {
                        for (Exercise e : exerciseList) {
                            request.setAttribute("Exercise" + e.getExerciseId(), e);
                            maxExercise = e.getExerciseId();
                        }
                    }
                }

                request.setAttribute("maxExercise", maxExercise);
            }
        }
        catch(Exception ex)
        {
            //url nhớ trả về trang sinh viên
             url = "/Views/Pages/Home/home.jsp";
             request.setAttribute("message", "Không thấy phần bìa tập");
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);

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
