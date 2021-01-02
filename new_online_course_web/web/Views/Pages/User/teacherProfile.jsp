<%-- 
    Document   : teacherProfile
    Created on : Dec 25, 2020, 10:15:45 AM
    Author     : TRAN VAN AN
--%>

<%@page import="DAO.CourseDB"%>
<%@page import="Model.Course"%>
<%@page import="java.util.List"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
        <link href="Views/Css/Course/course.scss" type="text/css" rel="stylesheet">
        <link rel="stylesheet" href="Views/Css/common.scss">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <link rel="stylesheet" href="Views/Css/Profile/teachProfile.scss">
        <title>ASQ | Profile</title>
        
        <script>
        
        function addCourse(text, idCourse)
        {
            var ListCourse = document.getElementById('list-course-teacher');
            
            var newDiv = document.createElement('div');
            newDiv.setAttribute('class', 'course-teacher');
            newDiv.setAttribute('id', 'course-teacher');
            ListCourse.appendChild(newDiv); 
            
            var newText = document.createElement('p');
            newText.setAttribute('class', 'text-course-teacher');
            newText.setAttribute('id', 'text-course-teacher' + '-idCourse');
            newText.innerHTML = text;
            newDiv.appendChild(newText);
            
              
            //link to edit the course
            var newLinkEdit = document.createElement('a');
            newLinkEdit.href ="Display_Course_Introduction_Teacher?courseid="+idCourse;
            newDiv.appendChild(newLinkEdit);
            
            var newButtonEdit = document.createElement('button');
            newButtonEdit.setAttribute('class', 'btn-course');
            newButtonEdit.classList.add('btn-course-edit');
            newButtonEdit.innerHTML = '!';
            newLinkEdit.appendChild(newButtonEdit);
            
            var newButtonDelete = document.createElement('button');
            newButtonDelete.setAttribute('class', 'btn-course');
            newButtonDelete.classList.add('btn-course-delete');
            newButtonDelete.innerHTML = '-';
            newDiv.appendChild(newButtonDelete);
            
        }
        

    </script>
    </head>
    <body>
        <div class = 'div_menu'>
           <div class="header">
                <a href="home"><div class="logo"></div></a>
            </div>
            <div id='div_account'>
                <label id='label_account'>Name<i class='fas fa-caret-down'></i></label>
            </div>
        </div>
        <div class="small-container">
            <div class="profile">
                <p class="name-user">${User.getName()}</p>
                <!--<p class="role-user">Student</p>-->    
            </div>
            <div class="course">
                <div class="title-course">
                    <h2>DANH SÁCH KHÓA HỌC</h2>
                </div>
                <div class="list-course" id="list-course-teacher">
                    <% 
                        List<Course> Courses = (List<Course>)session.getAttribute("CoursesTeacher");
                        if (Courses != null)
                            for(int i = 0; i < Courses.size(); i++)
                            { %>
                            <script>
                                addCourse("<%= Courses.get(i).getName() %>", "<%= Courses.get(i).getCourseId()%>");
                            </script>
                        <%}
                    %>
                    
                </div>
                   
                <form action="Display_Course_Introduction_Teacher?requirement=new" method="post" class="add-course-div">
                    <input type="submit" id="btnAddCourse" value="Thêm khóa học">
                </form>
            </div>
        </div>
    </body>
    
</html>
