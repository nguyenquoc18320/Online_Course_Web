<%-- 
    Document   : exercise_teacher
    Created on : Nov 27, 2020, 4:10:12 AM
    Author     : A556U
--%>

<%@page import="Model.Exercise"%>
<%@page import="Model.Part"%>
<%@page import="Model.Course"%>
<%@page import="DAO.CourseDB"%>
<%@page import="Model.Chap"%>
<%@page import="DAO.ChapDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exercise</title>
        <link rel="stylesheet" href="Views/Css/Course/exercise_teacher_css.css">
         <link rel="stylesheet" href="Views/Css/common.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        
    </head>
    <body>
        <%--<c:out value="${part}"/>--%>
       
        <div class = 'div_menu'> 
            <div class='div_logo'>
                <img id='image_logo' src ="logo.png" >
            </div>
            <div id ='div_CourseName'>
                <%Part part =(Part) session.getAttribute("part");
                    Course course= DAO.CourseDB.getCourseById(part.getCourseId());%>
                <label id="label_courseName"><%=course.getName()%></label>
            </div>
            <div id='div_account'>
                <label id='label_account'>${User.getName()}<i class='fas fa-caret-down'></i></label>               
            </div>
        </div>
        <form action="Process_Exercise_Teacher" medthod ="post">
            <c:set value="${part}" var="part" scope="request"/>
            <%
            Chap chap = (Chap)ChapDB.getChapByPrimaryKey(part.getCourseId(), part.getChapId());
            request.setAttribute("part", part);
            %>
            <div id="container">
                <h1 id='label_chapName'><%=chap.getName()%></h1>
                <h1 id='label_partName'><%=part.getName()%></h1>
                <div id='div_all_exercises'>
                    <div class='div_exercise' id='div_exercise1'>
                        <textarea class ='textarea_question' id='question1' name ='question1' placeholder="Nhập câu hỏi"></textarea>
                        <input type='text' class='input_answer' id='answer1_A' name='answer1_A' placeholder='Đáp án A'>
                        <input type='text' class='input_answer' id='answer1_B' name='answer1_B' placeholder='Đáp án B'>
                        <input type='text' class='input_answer' id='answer1_C' name='answer1_C' placeholder='Đáp án C'>
                        <input type='text' class='input_answer' id='answer1_D' name='answer1_D' placeholder='Đáp án D'>
                        <label class='label_correctAnswer'>Đáp án đúng:</label>
                        <select class = 'radio_correctAnswer' name ='correctAnswer1' id="correctAnswer1" >
                            <option value='A'>A</option>
                            <option value='B'> B</option>
                            <option value='C'> C </option>
                            <option value ='D' >D </option>
                        </select><br/>
                        <textarea class ='textarea_explaination' id='explaination1' name ='explaination1' placeholder="Giải thích"></textarea>
                    </div>           
                </div>
                <input type="button" id ='button_add_exercise' value='Thêm' onclick='AddExercise()'>
            </div>
            <div class ="div_save">
                <input id="button_save" type="submit" value="Lưu">                                   
                <a  href="teacher-profile"><input  id='button_profile' type="button" value="Trang chính"></a>
            </div>
            <div class="footer">
                <div class="small-container">
                    <div class="info-member">
                        <div class="member-detail">
                            <h3>Name of Member</h3>
                            <ul>
                                <li>Trần Văn Ân</li>
                                <li>Nguyễn Phan Sự</li>
                                <li>Nguyễn Anh Quốc</li>
                            </ul>
                        </div>
                        <div class="member-detail">
                            <h3>ID Student</h3>
                            <ul>
                                <li>18110249</li>
                                <li>18110355</li>
                                <li>18110345</li>
                            </ul>
                        </div>
                    </div>
                    <div class="info-contact member-detail">
                        <h3>Contact</h3>
                        <ul>
                            <li><a href="#"><i class="fab fa-facebook"></i> Facebook</a></li>
                            <li><a href="#"><i class="fas fa-at"></i> Email</a></li>
                            <li><a href="#"><i class="fab fa-telegram"></i> Telegram</a></li>
                        </ul>
                    </div>
                    <div class="icon-logo">

                    </div>
                </div>
            </div>
            <hr>
        </form>
        <% String message = (String) request.getAttribute("message");
                        if (message != null) {%>
            <%="<script> alert('" + message + "');</script>"%>
            <% request.setAttribute("message", null);%>
            <%}%>
    </body>
    <script>
        var totalExercises = 30;
        var exerciseList = [];
        var totalExercise=1;
        exerciseList[1] = 1;
        for (var i = 0; i <= totalExercises; i++)
        {
            exerciseList.push(0);
        }

        function AddExercise()
        {
            var emptyPosition = 0;
            for (var i = 1; i <= totalExercises; i++)
            {
                if (exerciseList[i] === 0)
                {
                    emptyPosition = i;
                    break;
                }
            }

            if (emptyPosition === 0)
            {
                alert("Bạn chỉ được tối đa 30 câu hỏi!");
                return;
            }

            totalExercise++;
            //Create textarea to enter a question
            var newExerciseDiv = document.createElement('div');
            newExerciseDiv.setAttribute('class', 'div_exercise');
            newExerciseDiv.setAttribute('id', 'div_exercise' + emptyPosition);

            var allExerciseDiv = document.getElementById('div_all_exercises');
            allExerciseDiv.appendChild(newExerciseDiv);

            var newQuestionTextarea = document.createElement('textarea');
            newQuestionTextarea.setAttribute('class', 'textarea_question');
            newQuestionTextarea.setAttribute('id', 'question' + emptyPosition);
            newQuestionTextarea.setAttribute('name', 'question' + emptyPosition);
            newQuestionTextarea.setAttribute('placeholder', 'Nhập câu hỏi');
            newExerciseDiv.appendChild(newQuestionTextarea);


            var name = ['A', 'B', 'C', 'D'];
            for (var i = 0; i < 4; i++)
            {
                var newAnswer = document.createElement('input');
                newAnswer.setAttribute('class', 'input_answer');
                newAnswer.setAttribute('id', 'answer' + emptyPosition + '_' + name[i]);
                newAnswer.setAttribute('name', 'answer' + emptyPosition + '_' + name[i]);
                newAnswer.setAttribute('placeholder', 'Đáp án ' + name[i]);

                newExerciseDiv.appendChild(newAnswer);
            }

            var label = document.createElement('label');
            label.setAttribute('class', 'label_correctAnswer');
            label.innerHTML = 'Đáp án đúng: ';
            newExerciseDiv.appendChild(label);

            //Create a selection for the answer
            var newSelect = document.createElement('select');
            newSelect.setAttribute('class', 'radio_correctAnswer');
            newSelect.setAttribute('name', 'correctAnswer' + emptyPosition);
            newSelect.setAttribute('id', 'correctAnswer' + emptyPosition);
            newExerciseDiv.appendChild(newSelect);

            for (var i = 0; i < 4; i++)
            {
                var newoption = document.createElement("option");
                newoption.setAttribute('value', name[i]);
                newoption.innerHTML = name[i];
                newSelect.appendChild(newoption);
            }

            var newExplanationTextarea = document.createElement('textarea');
            newExplanationTextarea.setAttribute('class', 'textarea_explaination');
            newExplanationTextarea.setAttribute('id', 'explaination' + emptyPosition);
            newExplanationTextarea.setAttribute('name', 'explaination' + emptyPosition);
            newExplanationTextarea.setAttribute('placeholder', 'Giải thích');
            newExerciseDiv.appendChild(newExplanationTextarea);
    
            exerciseList[emptyPosition] = 1;
        }
       
        ///Load data
        
//        while ()
        <% try
        {
            int maxExercise = Integer.parseInt(request.getAttribute("maxExercise").toString());
            for(int exerciseid =1; exerciseid<=maxExercise; exerciseid++)
            {
                Exercise exercise = (Exercise) request.getAttribute("Exercise"+exerciseid);
                if(exercise!=null)
                {%>
                  var textarea_question = document.getElementById("question"+<%=exerciseid%>);
                  while( textarea_question==null)
                  {
                      AddExercise();
                      textarea_question = document.getElementById("question"+<%=exerciseid%>);
                  }
                  
                  textarea_question.value="<c:out value='<%=exercise.getQuestion()%>'/>";
                  var textarea_answer = document.getElementById("answer"+<%=exerciseid%>+"_A");
                  textarea_answer.setAttribute("value","<c:out value="<%=exercise.getAnswerA()%>"/>");
                  
                  //ans B
                  textarea_answer = document.getElementById("answer"+<%=exerciseid%>+"_B");
                  textarea_answer.setAttribute("value", "<c:out value='<%=exercise.getAnswerB()%>'/>");
                 
                 //ans C
                  textarea_answer = document.getElementById("answer"+<%=exerciseid%>+"_C");
                  textarea_answer.setAttribute("value", "<c:out value='<%=exercise.getAnswerC()%>'/>");
                  
                  //Ans D
                   textarea_answer = document.getElementById("answer"+<%=exerciseid%>+"_D");
                   textarea_answer.setAttribute("value", "<c:out value='<%=exercise.getAnswerD()%>'/>");

                   //Correct Ans
                   var select_correct_answer= document.getElementById("correctAnswer"+ <%=exerciseid%>);
                   select_correct_answer.value="<c:out value='<%=exercise.getCorrectAnswer()%>'/>";
                   
                  //explain
                   textarea_answer = document.getElementById("explaination"+<%=exerciseid%>);
                   textarea_answer.value="<c:out value='<%=exercise.getExplaination()%>'/>"
                <%}
            }
        }
        catch(Exception ex)
            {}%>
        
    </script>
</html>
