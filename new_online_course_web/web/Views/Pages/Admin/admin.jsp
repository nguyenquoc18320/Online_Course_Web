<%-- 
    Document   : admin
    Created on : Jan 4, 2021, 11:24:32 PM
    Author     : TRAN VAN AN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--css-->
        <link href="Views/Css/common.css" type="text/css" rel="stylesheet">
        <link href="Views/Css/Admin/admin.css" type="text/css" rel="stylesheet"/>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <title>ASQ | Admin</title>
    </head>
    <body>
         <div class = 'div_menu'>
            <div class='div_logo'>
                <img id='image_logo' src ="logo.png" >
            </div>
            <div id='div_account'>
                <label id='label_account'>${User.getName()}  <i class='fas fa-caret-down'></i></label>               
            </div>
        </div>
            
        <div class="small-container">
            <div class="image-profile">
                 <div class="background-profile">
                     <img src="Views/Images/background.png"/>
                </div>
                <div class="avatar-profile">
                    <img src="Views/Images/logoUTE.png"/>
                </div>
                <div class="name-profile">
                    <p>Trần Văn Ân</p>
                </div>
            </div>
            <div class="introduce-profile lagre">
                <div class="title-profile">
                    <p>Giới thiệu</p>
                </div>
                <div class="info-profile">
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Chức vụ: </p>
                        <p class="describe">Quản lý</p>
                    </div>
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Ngày sinh: </p>
                        <p class="describe">25/09/2000</p>
                    </div>
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Giới tính: </p>
                        <p class="describe">Nam</p>
                    </div>
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Email: </p>
                        <p class="describe">18110249@student.hcmute.edu.vn</p>
                    </div>
                    <div class="info-detail-profile">
                        <p class="title-info-detail-profile">Số điện thoại: </p>
                        <p class="describe">0392889894</p>
                    </div>
                </div>
            </div>
            <div class="function">
                <div class="introduce-profile small">
                    <div class="title-profile">
                        <p>Quản lý tài khoản</p>
                    </div>
                    <div class="info-profile">
                        <p class="detail-small">250/270 tài khoản đang hoạt động</p>
                    </div>
                </div>
            </div>   
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
    </body>
</html>
