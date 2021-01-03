<%-- 
    Document   : exercise_student
    Created on : Jan 2, 2021, 3:45:04 PM
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
        <link rel="stylesheet" href="Views/Css/Course/exercise_student_css.css">
         <link rel="stylesheet" href="Views/Css/common.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        
    </head>
    <body>
       <div class = 'div_menu'> 
            <div class='div_logo'>
                <img id='image_logo' src ="logo.png" >
            </div>
            <div id ='div_CourseName'>
                <%
                    Part part =(Part) session.getAttribute("part");
    %>
             
                    <%Course course= CourseDB.getCourseById(part.getCourseId());%>
                <label id="label_courseName"><%=course.getName()%></label>
            </div>
            <div id='div_account'>
                <label id='label_account'>${User.getName()}<i class='fas fa-caret-down'></i></label>               
            </div>
        </div>
        <form action="Process_Exercise_Student" medthod ="post">
            
            <%
            Chap chap = (Chap)ChapDB.getChapByPrimaryKey(part.getCourseId(), part.getChapId());
            request.setAttribute("part", part);
            %>
            <div id="container">
                <h1 id='label_chapName'><%=chap.getName()%></h1>
                <h1 id='label_partName'><%=part.getName()%></h1>
                <div id='div_all_exercises'>
                    <div class='div_exercise' id='div_exercise1'>
                        <input type='button' class ='button_question' id='button_question1' >
                        <input type='radio' class='radio_answer' id='radio_answer1_A' name='answer1' value='A' ><label for='radio_answer1_A'id='label_answer1_A'></label><br>
                        <input type='radio' class='radio_answer' id='radio_answer1_B' name='answer1' value='B'><label for='radio_answer1_B' id='label_answer1_B'></label><br>
                        <input type='radio' class='radio_answer' id='radio_answer1_C' name='answer1' value='C' ><label for='radio_answer1_C' id='label_answer1_C'></label><br>
                        <input type='radio' class='radio_answer' id='radio_answer1_D' name='answer1' value='D'><label for='radio_answer1_D' id='label_answer1_D'></label><br>
                        <p class='p_correctAnswer' id = 'p_correctAnswer1'></p>
                      
                        <p class ='p_explaination' id='p_explaination1' ></p>
                    </div>           
                </div>
            </div>
            <div id ="div_button">
                <input id="button_completed" type="submit" value="Hoàn thành"> 
                 <a  href="#"><input  id='button_profile' type="button" value="Trang chính"></a>
               
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
        
        console.log("${message}");
        
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

            totalExercise++;
            //Create textarea to enter a question
            var newExerciseDiv = document.createElement('div');
            newExerciseDiv.setAttribute('class', 'div_exercise');
            newExerciseDiv.setAttribute('id', 'div_exercise' + emptyPosition);

            var allExerciseDiv = document.getElementById('div_all_exercises');
            allExerciseDiv.appendChild(newExerciseDiv);

            var newQuestion = document.createElement('input');
            newQuestion.setAttribute("type", "button");
            newQuestion.setAttribute('class', 'button_question');
            newQuestion.setAttribute('id', 'button_question' + emptyPosition);
            newExerciseDiv.appendChild(newQuestion);

            var name = ['A', 'B', 'C', 'D'];
            for (var i = 0; i < 4; i++)
            {
                var newAnswer = document.createElement('input');
                newAnswer.setAttribute("type","radio");
                newAnswer.setAttribute('class', 'radio_answer');
                newAnswer.setAttribute('id', 'radio_answer' + emptyPosition + '_' + name[i]);
                newAnswer.setAttribute("name", 'answer' + emptyPosition);
                newAnswer.setAttribute("value", name[i]);
                newExerciseDiv.appendChild(newAnswer);
                
                //Answer 
                var newLabel = document.createElement("label");
                newLabel.setAttribute("id", "label_answer"+emptyPosition+"_"+name[i]);
                newLabel.setAttribute("for",'radio_answer' + emptyPosition + '_' + name[i] );
                newExerciseDiv.appendChild(newLabel);
                
                var spaceLine = document.createElement("br");
                newExerciseDiv.appendChild(spaceLine);
            }

            var pCorrectAnswer = document.createElement('p');
            pCorrectAnswer.setAttribute('class', 'p_correctAnswer');
            pCorrectAnswer.setAttribute('id', 'p_correctAnswer'+emptyPosition);
//            pCorrectAnswer.innerHTML = 'Đáp án đúng: ';
            newExerciseDiv.appendChild(pCorrectAnswer);

           

            var newExplanationTextarea = document.createElement('p');
            newExplanationTextarea.setAttribute('class', 'p_explaination');
            newExplanationTextarea.setAttribute('id', 'p_explaination' + emptyPosition);;
            newExerciseDiv.appendChild(newExplanationTextarea);
    
            exerciseList[emptyPosition] = 1;
        }
       
        ///Load data
        console.log("max", "<%=request.getAttribute("maxExercise")%>");
        <% try
        {
            int maxExercise = Integer.parseInt(request.getAttribute("maxExercise").toString());
             request.setAttribute("maxExercise", 0);
            for(int exerciseid =1; exerciseid<=maxExercise; exerciseid++)
            {
                
                Exercise exercise = (Exercise) request.getAttribute("Exercise"+exerciseid);
                if(exercise!=null)
                {
                    String correctAnswer = exercise.getCorrectAnswer();%>
                        console.log("chạy");
                    var button_question = document.getElementById("button_question"+<%=exerciseid%>);
                    while( button_question==null)
                    {
                        AddExercise();
                        button_question = document.getElementById("button_question"+<%=exerciseid%>);
                    }

                    button_question.value="<c:out value='<%=exercise.getQuestion()%>'/>";
                  
                  

                    var ansLabel = document.getElementById("label_answer"+"<%=exercise.getExerciseId()%>"+"_A");
                    if("<%=exercise.getAnswerA()%>"!=="")
                    {
                        ansLabel.innerHTML="A. "+ "<%=exercise.getAnswerA()%>";
                    }
                    else//không có thì xóa
                    {
                        ansLabel.remove();
                        document.getElementById("radio_answer"+"<%=exercise.getExerciseId()%>"+"_A").remove();
                    }

                     ansLabel = document.getElementById("label_answer"+"<%=exercise.getExerciseId()%>"+"_B");
                     if("<%=exercise.getAnswerB()%>"!=="")
                    {
                        ansLabel.innerHTML="B. "+"<%=exercise.getAnswerB()%>";
                    }
                    else//không có thì xóa
                    {
                        ansLabel.remove();
                        document.getElementById("radio_answer"+"<%=exercise.getExerciseId()%>"+"_B").style.display="none";
                    }


                    ansLabel = document.getElementById("label_answer"+"<%=exercise.getExerciseId()%>"+"_C");
                    if("<%=exercise.getAnswerC()%>"!=="")
                    {
                        ansLabel.innerHTML= "C. "+"<%=exercise.getAnswerC()%>";
                    }
                    else
                    {
                        ansLabel.style.display="none";
                       document.getElementById("radio_answer"+"<%=exercise.getExerciseId()%>"+"_C").style.display="none";
                    }

                    ansLabel = document.getElementById("label_answer"+"<%=exercise.getExerciseId()%>"+"_D");
                    if("<%=exercise.getAnswerD()%>"!=="")
                    {
                        ansLabel.innerHTML= "D. "+"<%=exercise.getAnswerD()%>";
                    }
                    else
                    {
                        ansLabel.style.display="none";
                        document.getElementById("radio_answer"+"<%=exercise.getExerciseId()%>"+"_D").style.display="none";
                    }
                    
                    //Đáp án
                        <%String result = (String)request.getAttribute("resultOfAnswer"+exercise.getExerciseId());
                        if(result!=null)
                        {%>
                        var correctAnswerLabel = document.getElementById("p_correctAnswer"+"<%=exercise.getExerciseId()%>");
                        correctAnswerLabel.innerHTML= "<%=result%>";
                         //Giải thích
                        var p_enplaination = document.getElementById("p_explaination"+"<%=exercise.getExerciseId()%>");
                         p_enplaination.innerHTML="<%=exercise.getExplaination()%>";
                         
                         //Thêm nút làm lại bài
                         if(document.getElementById("button_do_again")==null)
                         {
                         var div_button = document.getElementById("div_button");
                         var button_completed = document.getElementById("button_completed");
//                         var link_do_again = document.createElement("a");
//                         link_do_again.setAttribute("href", "Display_Exercise_Student?courseid="+"${part.getCourseId()}"
//                                 +"&chapid="+"${part.getChapId()}" +"&partid="+"${part.getPartId()}");
                         //div_button.appendChild(link_do_again);
                         button_completed.insertAdjacentHTML("afterend", "<a id='link_do_again' href= "+ "Display_Exercise_Student?courseid="+"${part.getCourseId()}"
                                +"&chapid="+"${part.getChapId()}" +"&partid="+"${part.getPartId()}"+">");
                          
                        var link_do_again = document.getElementById("link_do_again");
                         
                         var button_do_again = document.createElement("input");
                         button_do_again.setAttribute("type", "button");
                         button_do_again.setAttribute("id", "button_do_again");
                         button_do_again.setAttribute("value", "Làm lại");
                         link_do_again.appendChild(button_do_again);
                         console.log("tạo");
                        }
                         
                         //in kết quả làm
                        if("<%=result%>"==="Trả lời đúng")
                            correctAnswerLabel.setAttribute("style", "color:#01DF01");
                           else
                                correctAnswerLabel.setAttribute("style", "color:red");
                          <%}%>
                              
                    
                     
                        //Chọn lại lựa chọn mà student đã chọn
                          <%String selectedAns = (String)request.getAttribute("answer"+exercise.getExerciseId());
                          if(selectedAns!=null) {%>
                            var radio= document.getElementById("radio_answer"+"<%=exercise.getExerciseId()%>"+"_"+"<%=selectedAns%>");
                             radio.checked=true;
                        <%}%>
                <%}
            }
           
        }
        catch(Exception ex)
            {}%>
        
    </script>
</html>
