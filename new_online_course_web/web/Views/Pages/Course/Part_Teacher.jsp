<%-- 
    Document   : Part_Teacher
    Created on : Jan 13, 2021, 4:35:12 AM
    Author     : ad
--%>
<%@page import="java.util.List"%>
<%@page import="Model.Chap"%>
<%@page import="Model.Part"%>
<%@page import="Model.FAQ"%>
<%@page import="Model.User"%>
<%@page import="Model.Instructor"%>
<%@page  import = "Model.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ASQ Part </title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <link rel="stylesheet" href="Views/Css/Course/PartTeacher.css">
    <link rel="stylesheet" href="Views/Css/Course/CourseIntroduction_Teacher_css.css">
    <link href="Views/Css/Course/course.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="Views/Css/common.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="./path/to/dropzone.js"></script>
    <body>
        <input type ="hidden" name='courseId' value="${course.getCourseId()}">
        <%--<c:out value="${course.getCourseId()}"/>--%>
         <div class="small-container horizontal">
             <div class='div_logo'>
                <a href="home"><img id='image_logo' src ="logo.png" ></a>
            </div>

            <div id='div_account' class="div_account">
                <label id='label_account'>${User.getName()} </label>
                <div class="drop-down account" id="drop-down-person">
<!--                        <a href="admin"><button>Thông tin cá nhân</button></a>-->
                    <!--<a href="sign-in"><button>Đăng xuất</button></a>-->
                 </div>    
                <i class='fas fa-caret-down' onclick="ToggleDropDown('drop-down-person')"></i>    
            </div>
         </div>
        <div class = 'div_menu'>
            <div class='div_logo'>
                <img id='image_logo'href='#' src ="logo.png" >
            </div>
            <div id='div_account'>
                <label id='label_account'>Name  <i class='fas fa-caret-down'></i></label>               
            </div>
            </div>
        <div class="main_title">
            <div class="title_lesson">
                <h2>Nội dung bài học</h2>
            </div>
        </div>
        <div class="main_namelesson">
            <textarea rows="1" id ="lessonname" placeholder="Nhập tên bài học....."></textarea>
        </div>
        <div class="content_lesson">
            <textarea rows="6" id = "inf-lesson"placeholder="Mô tả bài học..."></textarea>
        </div>
        <form method="post" action="UploadFile" enctype="multipart/form-data">
           <div class="drag_drop" ondrop="dropHandler(event);" ondragover="dragOverHandler(event);">           
<!--                    <div class="powers">
                        <div class="powers-drag-handle" title="Double-click to expand."></div>
                    </div>-->
                    
                <div class="zone">
<!--                        <div>Chọn hoặc kéo thả file ở đây</div>                    
                        <span>_______________</span>
                        <i class="fas fa-cloud-upload-alt"></i>-->
<!--                        <div class="selectFile" for="choose-file">       
                            <label for="file">Chọn file:  </label><br>-->
                            
                          <input multiple type="file" name="files[]" id="choose-file" class="zone" >
                          <label for="choose-file">Upload
                            <p>Chọn hoặc kéo thả file ở đây</p>                    
                            <span>_______________</span>
                            <i class="fas fa-cloud-upload-alt"></i>
                            <div class="selectFile" for="choose-file"></div>
                            <label for="file">Chọn file:  </label>
                          </label>
                        </div>
                        <p>Chọn file bài học cung cấp cho học viên</p>
                </div>
           </div>
                <script>
                    //catch drop
                    var doc = document.documentElement;
                    doc.ondragover = funtion()
                    {
                        this.className = "hover";
                        return false;
                    };
                    doc.ondragend = function()
                    {
                        this.className = '';
                        return false;
                    };
                    doc.ondrop = function(event)
                    {
                        event.preventDefault && event.preventDefault();
                        this.classNane ="drag_drop";
                        ///keep files
                        var files = event.dataTrasfer.files;
                        var formData = new FormData();
                        for (var i = 0; i < files.length; i++) 
                        {
                            formData.append('file', files[i]);
                        }
                        // post a new XHR request
                        var xhr = new XMLHttpRequest();
                        xhr.open('POST', '/upload');
                        xhr.onload = function () 
                        {
                            if (xhr.status === 200) 
                            {
                              console.log('All done: ' + xhr.status);
                            } else 
                            {
                              console.log('Error');
                            }
                        };
                        xhr.send(formData);
                        xhr.upload.onprogress = function (event)
                        {
                        if (event.lengthComputable) 
                        {
                          var complete = (event.loaded / event.total * 100 | 0);
                          progress.value = progress.innerHTML = complete;
                        }
                        };
                        xhr.onload = function () 
                        {
                          // just in case we get stuck around 99%
                          progress.value = progress.innerHTML = 100;
                        };
                        return false;
                    };
                    function drop(e) {
                    /*hủy sự kiện cho các hành động khác*/
                      e.stopPropagation();
                      e.preventDefault();
                    /*Truy xuất kéo dữ liệu theo loại*/
                      var data = e.dataTransfer.getData("File");
                    /*Thêm hình ảnh được kéo vào ô chúng ta đã tạo từ trước*/
                      e.target.appendChild(document.getElementById(data));
                    }
                    }
                    function dropHandler(ev) 
                    {
                        console.log('File(s) dropped');

                        // không cho mở tệp
                        ev.preventDefault();

                        if (ev.dataTransfer.items) {
                          // Use DataTransferItemList interface to access the file(s)
                          for (var i = 0; i < ev.dataTransfer.items.length; i++) {
                            // If dropped items aren't files, reject them
                            if (ev.dataTransfer.items[i].kind === 'file') {
                              var file = ev.dataTransfer.items[i].getAsFile();
                              console.log('... file[' + i + '].name = ' + file.name);
                            }
                          }
                        } else {
                          // Use DataTransfer interface to access the file(s)
                          for (var i = 0; i < ev.dataTransfer.files.length; i++) 
                          {
                            console.log('... file[' + i + '].name = ' + ev.dataTransfer.files[i].name);
                          }
                        }
                    }
                    function dragOverHandler(ev)
                    {
                      console.log('File(s) in drop zone');

                      // Prevent default behavior (Prevent file from being opened)
                      ev.preventDefault();
                    }
                </script>                    
            <div class="footer">
                <div class = "footer-left">
                    <button class="btn" type="button">
                        <span class ="btn-contert">Đóng</span>
                    </button>
                </div>
                <div class="footer-right">
                    <button class="btn disable" type="button" aria-disable ="true" id="btn-cancel">
                        <span class="btn-contert">Mới</span>
                    </button>
                    <button class="btn disable upload-file" type="supmit" aria-dissable="true" id="btn-upload">
                        <span class="btn-contert">Tải lên</span>
                    </button>
                </div>
            </div>
        </form>
        <div>
            <div class="container"></div>
                <script>

                        const uploader = new Box.ContentUploader();

                        // Show the content uploader
                        uploader.show(configData.FOLDER_ID, configData.ACCESS_TOKEN, {
                            container: '.container'
                        });

                        // Log upload data to console
                        uploader.on('complete', (data) => { 
                            console.log(`All files successfully uploaded: ${JSON.stringify(data)}`); 
                        });

                        uploader.on('upload', (data) => {
                            console.log(`Successfully uploaded file with name "${data.name}" to Box File ID ${data.id}`);
                        });

                        uploader.on('error', (data) => {
                            console.log(`Error uploading file with name "${data.file.name}". The error was: "${data.error}"`);
                        });
                    
                </script>
        </div>
            
            <script>
                //rename 
//                $('#choose-file').inputFileText({
//                    text: 'Chọn:'
//                });
                //khung vien
                $(document).bind('dragover', function (e) {
                    var dropZone = $('.zone'),
                        timeout = window.dropZoneTimeout;
                    if (!timeout) {
                        dropZone.addClass('in');
                    } else {
                        clearTimeout(timeout);
                    }
                    var found = false,
                        node = e.target;
                    do {
                        if (node === dropZone[0]) {
                            found = true;
                            break;
                        }
                        node = node.parentNode;
                    } while (node != null);
                        if (found) {
                            dropZone.addClass('hover');
                        } else {
                            dropZone.removeClass('hover');
                        }
                    window.dropZoneTimeout = setTimeout(function () {
                        window.dropZoneTimeout = null;
                        dropZone.removeClass('in hover');
                    }, 100);
                });
                </script>
                            
        
    </body>
</html>

