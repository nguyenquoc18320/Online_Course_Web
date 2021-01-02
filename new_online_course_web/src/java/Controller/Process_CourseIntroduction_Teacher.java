package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpSession;

/**
 *
 * @author A556U
 */
public class Process_CourseIntroduction_Teacher extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

         HttpSession session = request.getSession();
        User user = (User)session.getAttribute("User");

        String url = "/Views/Pages/Course/CourseIntroduction_Teacher.jsp";

        if (user == null) {
            url = "/Views/Pages/Home/home.jsp";
        } else {
            String objective = (String) request.getParameter("objective");
            String courseName = (String) request.getParameter("courseName");
            String courseId = (String) request.getParameter("courseId");

            Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
            String message = "";

            int courseid;
            //if courseid does not exist in the database
            if (courseId == null || "".equals(courseId)) {
                int max = CourseDB.getMaxCourseID();
                session.setAttribute("info", max);
                courseid = max + 1;
                Course course = new Course(max+1, courseName, objective, user.getUserId(),now, false,""  );
                
                if (CourseDB.insertCourse(course)) {

                    request.setAttribute("course", course);
                } else {
                    message = "Tạo khóa học mới thất bại";
                }
            } else {
                courseid = Integer.parseInt(courseId);
                Course course = new Course(courseid, courseName, objective, user.getUserId(),now, false,""  );
                try {
                    if (CourseDB.courseExists(courseid)) {
                        request.setAttribute("course", course);
                        if (!CourseDB.updateCourse(course)) {
                            message = "Lưu thất bại";
                        }
                    } else {
                        message = "Không tìm thấy khóa học";
                    }
                } catch (Exception ex) {
                    message = "Lưu thất bại";
                }
            }

            int maxChap = 0;
            //Process the chaps
            for (int i = 1; i <= 10; i++) {
                String chapName = (String) request.getParameter("chap" + i);
                if (chapName != null && !chapName.equals("")) {
                    Chap chap = new Chap(courseid, i, chapName);
                    if (ChapDB.chapExists(courseid, i)) {
                        if (!ChapDB.updateChap(chap)) {
                            message = "Lưu thất bại";
                        }
                    } else {
                        if (!ChapDB.insertChap(chap)) {
                            message = "Lưu thất bại";
                        }
                    }
                    request.setAttribute("chap" + i, chap);
                    maxChap = i;
                }
            }

            //Xử lí part
            for (int chapid = 1; chapid <= 10; chapid++) {
                for (int partid = 1; partid <= 10; partid++) {
                    String partName = (String) request.getParameter("chap" + chapid + "_part" + partid);
                    if (partName != null && !partName.equals("")) {
                        Part part = new Part(courseid, chapid, partid, partName);
                        //the part exist in the database
                        if (PartDB.partExists(courseid, chapid, partid)) {
                            if (!PartDB.updatePart(part)) {
                                message = "Lưu thất bại";
                            }
                        } else {
                            if (PartDB.insertPart(part) == false) {
                                message = "Lưu thất bại";
                            }
                        }
                        request.setAttribute("chap" + chapid + "_part" + partid, part);
                    } else {
                        try {
                            PartDB.deletePart(courseid, chapid, partid);
                        } catch (Exception ex) {
                            message = "Lưu thất bại";
                        }
                    }
                }
            }

            ///Xử lí FAQ
            for (int faqId = 1; faqId <= 10; faqId++) {
                String question = request.getParameter("question" + faqId);
                if (question == null) {
                    question = "";
                }
                String answer = request.getParameter("answer" + faqId);
                if (answer == null) {
                    answer = "";
                }
                //There is at least one of the question or the answer
                if (!question.equals("") || !answer.equals("")) {
                    FAQ faq = new FAQ(courseid, faqId, question, answer);

                    //update if the faq exists in db
                    if (FAQDB.FAQExists(courseid, faqId)) {
                        if (!FAQDB.updateFAQ(faq)) {
                            message = "Lưu thất bại";
                        }
                    } else//add new faq
                    {
                        if (!FAQDB.insertFAQ(faq)) {
                            message = "Lưu thất bại";
                        }
                    }
                    request.setAttribute("FAQ" + faqId, faq);
                } else//delete the chap if it exists in db
                {
                    if (!FAQDB.deleteFAQ(courseid, faqId)) {
                        message = "Lưu thất bại";
                    }
                }
            }

            if (message.equals("")) {
                message = "Lưu thành công";
            }

            request.setAttribute("maxChap", maxChap);
            request.setAttribute("message", message);
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
