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
public class Process_Exercise_Student extends HttpServlet {

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

        HttpSession session = request.getSession();
        Part part = (Part) session.getAttribute("part");
        //The message sent to jsp
        String message = "";

        String url = "/Views/Pages/Course/exercise_student.jsp";
        //the following part to test
        // part = new Part(1, 1, 1, "");
        int maxExercise = 0;

        if (part == null) {
            request.setAttribute("message", "Không tìm thấy phần bài tập!");
//            url = "/" + (String) request.getParameter("previousPage");
        } else {
            session.setAttribute("part", part);
             
            List<Exercise> exerciseList = ExerciseDB.getAllExercisePartOfPart(part.getCourseId(), part.getChapId(), part.getPartId());
            maxExercise=exerciseList.size();
            if (exerciseList != null) {
                for (Exercise e : exerciseList) {
                    request.setAttribute("Exercise" + e.getExerciseId(), e);
                    maxExercise = e.getExerciseId();
                    
                    String answerOfStudent = (String)request.getParameter("answer"+e.getExerciseId());
                    
                    request.setAttribute("answer"+e.getExerciseId(), answerOfStudent);
                    
                    if(answerOfStudent!=null && e.getCorrectAnswer().equals(answerOfStudent))
                    {
                        request.setAttribute("resultOfAnswer"+e.getExerciseId(), "Trả lời đúng");                     
                    }
                    else 
                    {
                        request.setAttribute("resultOfAnswer"+e.getExerciseId(), "Trả lời sai! Đáp án đúng là "+e.getCorrectAnswer());                     
                    }
                     
                }
            }
            
              request.setAttribute("maxExercise", maxExercise);
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
