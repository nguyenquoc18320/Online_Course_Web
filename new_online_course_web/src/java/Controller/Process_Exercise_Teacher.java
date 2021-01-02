package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.*;
import DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author A556U
 */
public class Process_Exercise_Teacher extends HttpServlet {

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
        Part part = (Part)session.getAttribute("part");
        //The message sent to jsp
        String message = "";
        
        String url ="/Views/Pages/Course/exercise_teacher.jsp";
        
        //the following part to test
       // part = new Part(1, 1, 1, "");
       if(part!=null)
             part = PartDB.getPartByPrimaryKey(part.getCourseId(), part.getChapId(), part.getPartId());
       else
           message="Hiện không tìm thấy phần bài để thêm bài tập";

        
        //MaxExercise 
        int maxExercise = 0;
        if (part != null) {
            request.setAttribute("part", part);
            int courseid = part.getCourseId();
            int chapid = part.getChapId();
            int partid = part.getPartId();
            
            for (int exerciseid = 1; exerciseid <= 30; exerciseid++) {
                String question = (String) request.getParameter("question" + exerciseid);
                String ansA = (String) request.getParameter("answer" + exerciseid + "_A");
                String ansB = (String) request.getParameter("answer" + exerciseid + "_B");
                String ansC = (String) request.getParameter("answer" + exerciseid + "_C");
                String ansD = (String) request.getParameter("answer" + exerciseid + "_D");
                String correctAns = (String) request.getParameter("correctAnswer" + exerciseid);
                String explaination = (String) request.getParameter("explaination" + exerciseid);
             
                if (question == null) {
                    question = "";
                }
                if (ansA == null) {
                    ansA = "";
                }
                if (ansB == null) {
                    ansB = "";
                }
                if (ansC == null) {
                    ansC = "";
                }
                if (ansD == null) {
                    ansD = "";
                }
                if (correctAns == null) {
                    correctAns = "";
                }
                if (explaination == null) {
                    explaination = "";
                }
//                try {
                    if (!question.equals("") || !ansA.equals("") || !ansB.equals("") || !ansC.equals("")
                            || !ansD.equals("")) {
                        Exercise exercise = new Exercise(courseid, chapid, partid, exerciseid, question, ansA, ansB, ansC, ansD, correctAns, explaination);
                        request.setAttribute("Exercise"+exerciseid, exercise);
                        maxExercise=exerciseid;
                        
                        //update if the exercise exists
                        if (ExerciseDB.exericseExists(courseid, chapid, partid, exerciseid)) {
                            if(!ExerciseDB.updateExercise(exercise))
                                message = "Lưu thất bại!";
                        } else {
                            if(!ExerciseDB.insertExercise(exercise))
                                 message = "Lưu thất bại!";
                        }
                    }
                    else//Delete the exercise
                    {
                        if(!ExerciseDB.deleteExercise(courseid, chapid, partid, exerciseid))
                             message = "Lưu thất bại!";
                    }
//                } catch (Exception ex) {
//                    message = "Lưu thất bại!";
//                }
            }
        }
        
        if(message.equals(""))
            message="Lưu thành công";
        request.setAttribute("maxExercise", maxExercise);
        request.setAttribute("message", message);
                
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
