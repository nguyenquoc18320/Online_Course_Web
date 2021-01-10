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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            throws ServletException, IOException, ParseException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

  HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        String sDate1 = "31/12/1998";
//        Date now=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);

        Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());

//        user= new User(1,"a", now,
        String url = "/Views/Pages/Course/CourseIntroduction_Teacher.jsp";      

         if (user == null ) {
            url = "/sign-in";
        }
        else if(user.getRole().getRoleId()!=2)
        {
             url = "/Views/Pages/Home/home.jsp";
        }else {
        String objective = (String) request.getParameter("objective");
        String courseName = (String) request.getParameter("courseName");
        String courseId = (String) request.getParameter("courseId");

        String message = "";

        int courseid;
        Course course=null;
        //if courseid does not exist in the database
        if (courseId == null || "".equals(courseId)) {
            int max = CourseDB.getMaxCourseID();
            session.setAttribute("info", max);
            courseid = max + 1;
            course = new Course(max + 1, courseName, objective, user, now, false, "");

            if (CourseDB.insertCourse(course)) {

                request.setAttribute("course", course);
            } else {
                message = "Tạo khóa học mới thất bại";
            }
        } else {
            courseid = Integer.parseInt(courseId);
            course = new Course(courseid, courseName, objective, user, now, false, "");
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
                    Chap chap = ChapDB.getChapOfCourseByOrder(course, i);
                    if (chap!=null) {  
                        chap.setName(chapName);
                        if (!ChapDB.updateChap(chap)) {
                            message = "Lưu thất bại";
                        }
                    } else {
                        chap = new Chap(course,i, chapName);
                        if (!ChapDB.insertChap(chap)) {
                            message = "Lưu thất bại";
                        }
                    }
                    request.setAttribute("chap" + i, chap);
                    maxChap = i;
                }
            }
            
//            //Xử lí part
            for (int chapid = 1; chapid <= 10; chapid++) {
                Chap chap = ChapDB.getChapOfCourseByOrder(course, chapid);
                //chưa xử lí nếu part rỗng
                
                for (int partid = 1; partid <= 10; partid++) {
                    String partName = (String) request.getParameter("chap" + chapid + "_part" + partid);
                    if (partName != null && !partName.equals("")) {
                        Part part = PartDB.getPartByCourseAndChap(course, chap, partid);
                        //the part exist in the database
                        if (part!=null) {
                            part.setName(partName);
                            if (!PartDB.updatePart(part)) {
                                message = "Lưu thất bại";
                            }
                        } else {
                            part = new Part(chap, course, partid, partName, "", "" );
                            if (PartDB.insertPart(part) == false) {
                                message = "Lưu thất bại";
                            }
                        }
                        request.setAttribute("chap" + chapid + "_part" + partid, part);
                    } else {
                        try {                                      
                            PartDB.deletePart(course, chap, partid);                  
                        } catch (Exception ex) {
                            message = "Lưu thất bại";
                        }
                    }
                }
            }
//
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
                    FAQ faq = FAQDB.getFAQByCourseAndOrder(course, faqId);

                    //update if the faq exists in db
                    if (faq!=null) {
                        faq.setQuestion(question);
                        faq.setAnswer(answer);
                        if (!FAQDB.updateFAQ(faq)) {
                            message = "Lưu thất bại";
                        }
                    } else//add new faq
                    {
                        faq=new  FAQ(faqId, course, question, answer);
                        if (!FAQDB.insertFAQ(faq)) {
                            message = "Lưu thất bại";
                        }
                    }
                    request.setAttribute("FAQ" + faqId, faq);
                } else//delete the faq if it exists in db
                {
                    if (!FAQDB.deleteFAQByCourse(course, faqId)) {
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Process_CourseIntroduction_Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Process_CourseIntroduction_Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
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
