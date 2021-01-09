<%-- 
    Document   : adminn
    Created on : Jan 8, 2021, 12:38:32 PM
    Author     : TRAN VAN AN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="Views/Css/Admin/admin.css" rel="stylesheet" type="text/css"/>
    <link href="Views/Css/Admin/adminn.css" rel="stylesheet" type="text/css"/>
    <script src="Views/Js/Admin/admin.js"></script>
    <title>Admin</title>
</head>
<body>
    <div class="container-full">
        <div class="side-bar">
            <div class="logo">
                <img src="Views/Images/logo.png">
            </div>
            <div class="profile">
                <img src="Views/Images/iconLogo.png">
                <p>Trần Văn Ân</p>
                <p>Admin</p>
            </div>
            <div class="menu">
                <button><i class="fas fa-home"></i>Trang chủ</button>
                <a href="account-managerment"><button><i class="fas fa-address-book"></i> Quản lý tài khoản</button></a>
                <button><i class="fas fa-address-book"></i> Quản lý tài khoản</button>
                <button><i class="fas fa-address-book"></i> Quản lý tài khoản</button>
                <button><i class="fas fa-address-book"></i> Quản lý tài khoản</button>
            </div>
        </div>
        <div class="wrapped">
            <div class="nav-bar">
                <h1>Quản lý tài khoản - Admin</h1>
                <div class="drop-down" id="drop-down-user">
                    <a href="account-profile"><button>Thông tin cá nhân</button></a>
                    <a href="sign-in"><button>Đăng xuất</button></a>
                </div>
                <i class="fas fa-user-circle" onclick="ToggleDropDown('drop-down-user')"></i>
            </div>
            <div class="content">

            </div>
        </div>
    </div>
</body>
</html>