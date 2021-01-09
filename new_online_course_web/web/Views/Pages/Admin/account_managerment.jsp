<%-- 
    Document   : adminn
    Created on : Jan 8, 2021, 12:38:32 PM
    Author     : TRAN VAN AN
--%>

<%@page import="java.util.List"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw==" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link href="Views/Css/common.css" rel="stylesheet" type="text/css"/>
    <link href="Views/Css/Admin/admin.css" rel="stylesheet" type="text/css"/>
    <script src="Views/Js/Admin/admin.js"></script>
    <script src="Views/Js/Admin/account_managerment.js"></script>
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
               <form action="account-managerment" method="post">
                    <div class="">
                        <div class="filter">
                            <select id="account-type" class="select" name="accountType">
                                <option value="all" 
                                        <c:if test="${AccountType == 'all'}"><c:out value="selected"/></c:if>
                                        >-- Bộ lọc loại tài khoản --</option>
                                <option value="admin"
                                         <c:if test="${AccountType == 'admin'}"><c:out value="selected"/></c:if>
                                        >Quản lý</option>
                                <option value="teacher"
                                         <c:if test="${AccountType == 'teacher'}"><c:out value="selected"/></c:if>
                                        >Giáo viên</option>
                                <option value="student"
                                         <c:if test="${AccountType == 'student'}"><c:out value="selected"/></c:if>
                                        >Học viên</option>
                            </select>
                            <select id="account-state" class="select" name="accountState">
                                <option value="all"
                                         <c:if test="${AccountState == 'all'}"><c:out value="selected"/></c:if>
                                        >-- Bộ lọc trạng thái --</option>
                                <option value="activated"
                                        <c:if test="${AccountState == 'activated'}"><c:out value="selected"/></c:if>
                                        >Kích hoạt</option>
                                <option value="locked"
                                        <c:if test="${AccountState == 'locked'}"><c:out value="selected"/></c:if>
                                        >Đã khóa</option>
                            </select>
                            <input id="txtSearch" type="text" placeholder="Tìm kiếm" name="search" value="<c:out value="${Search}"/>"/>
                            <input type="submit" value="Tìm kiếm" class="btn-submit">
                        </div>
                        <div class="btn-admin">
                           <i class="fas fa-plus-circle" onclick="ShowFrontDivAddAdmin()"></i>
                           <i class="fas fa-chart-area" onclick="ShowFrontDivReport()"></i>
                       </div>
                        <div class="table-div">
                             <div class="grid" id="gird-transport">
                                <table border="1" cellpadding=0 id="table-transport" class="table">
                                    <thead class="thead-light">
                                        <tr>
                                            <th scope="col">Mã tài khoản</th>
                                            <th scope="col">Tên người dùng</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Vai trò</th>
                                            <th scope="col">Tình trạng (Kích hoạt)</th>
                                            <th scope="col">Chi tiết</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbody-table">
                                           <%
                                               List<User> users = (List<User>)request.getAttribute("Users");
                                               //List<Account> accounts = (List<Account>)request.getAttribute("Accounts");
                                               //List<Role> roles = (List<Role>)request.getAttribute("Roles");
                                               if (users != null )
                                               {
                                                    %> <script> tbody_table.empty();</script> <%
                                                   for (int i = 0; i < users.size(); i++)
                                                   {
                                                       User user = (User)users.get(i);
                                                       //if (accounts != null && roles != null)
                                                       //{
                                                            //Account account = AccountDB.getAccountInListByUserId(accounts, user.getUserId());
                                                            //Role role = RoleDB.GetRoleInListByRoleId(roles, user.getRole());
                                                            %> <script>LoadUsers("<%=user.getUserId()%>", "<%=user.getName()%>", "<%=user.getEmail()%>",
                                                                        "<%=user.getRole().getRoleName()%>", "<%= user.getAccount().isStatus()%>")</script> <%
                                                        //}
                                                   }
                                               }
                                           %>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="front-div admin" id="front-div">
                    <script>
                         if ("<c:out value="${IsShowAddAdmin}"/>" == "true")
                             document.getElementById('front-div').style.display = 'flex';
                         else
                             document.getElementById('front-div').style.display = 'none';
                     </script>
                     <div class="content-front">
                         <div class="btn-close" id="btn-close">
                             <i class="far fa-times-circle" onclick="CloseFrontDivAddAdmin()"></i>
                         </div>
                         <p class="header">Thêm tài khoản</p>
                         <form action="add-admin" method="post">
                             <input type="hidden" name="errorAddAdmin" value="<c:out value="${ErrorAddAdmin}"/>">
                             <% 
                                 String errorAddAdmin = (String)request.getAttribute("ErrorAddAdmin");
                             %>
                             <script>
                                 if ("<c:out value="${ErrorAddAdmin}"/>" !== "")
                                     alert("<c:out value="${ErrorAddAdmin}"/>");
                             </script>
                             <div class="small-container">
                                 <div class="field-input">
                                     <p>Họ tên:</p>
                                     <input type="text" name="nameAdd" required>
                                 </div>
                                 <div class="field-input">
                                     <p>Ngày sinh:</p>
                                     <input type="date" name="dateOfBirthAdd"" required>
                                 </div>
                                 <div class="field-input">
                                     <p>Giới tính:</p>
                                     <select id="genderAdd" name="genderAdd" required>
                                         <option value="true">Nam</option>
                                         <option value="false">Nữ</option>
                                     </select>
                                 </div>
                                 <div class="field-input">
                                     <p>Email:</p>
                                     <input type="email" name="emailAdd"" required>
                                 </div>
                                 <div class="field-input">
                                     <p>Điện thoại:</p>
                                     <input type="tel" name="phoneAdd"" required>
                                 </div>
                                 <div class="field-input">
                                     <p>Mật khẩu:</p>
                                     <input type="password" name="passwordAdd" required>
                                 </div>
                                 <div class="field-input">
                                     <p>Xác nhật mật khẩu:</p>
                                     <input type="password" name="confirmPasswordAdd" required>
                                 </div>
                                 <input type="submit" value="Thêm" class="btn-submit btn-submit-front"/>
                             </div>
                         </form>
                     </div>
                </div>
                <div class="front-div admin" id="front-div-report">
                    <script>
                        if ("<c:out value="${IsShowReport}"/>" == "true")
                            document.getElementById('front-div-report').style.display = 'flex';
                        else
                            document.getElementById('front-div-report').style.display = 'none';
                    </script>
                    <div class="content-front">
                        <div class="btn-close" id="btn-close">
                            <i class="far fa-times-circle" onclick="CloseFrontDivReport()"></i>
                        </div>
                        <p class="header">Thống kê tài khoản</p>
                        <form action="account-managerment?isShowReport=true" method="post">
                            <div class="">
                                <div class="select-date">
                                    <input type="date" id="txtDateProduct" name="dateReport" value="<c:out value="${DateReport}"/>"/>
                                    <select class="mdb-select md-form" id="select-date-product" name="typeDate">
                                        <option value="week" 
                                                <c:if test="${TypeDate.equals('week')}"><c:out value="selected"/></c:if>
                                                >Tuần</option>
                                        <option value="month"
                                                <c:if test="${TypeDate.equals('month')}"><c:out value="selected"/></c:if>
                                                >Tháng</option>
                                    </select>
                                    <input type="submit" value="Lọc" class="btn-submit">
                                </div>
                                <div class="chartjs">
                                    <canvas id="myChartReport" class="chart"></canvas>
                                </div>
                                <% 
                                    String reportAccounts = (String)request.getAttribute("ReportAccounts");
                                    String jsonReportAccounts = reportAccounts.replace("&#034;","");
                                %>
                                <script>LoadChartReport(<%=jsonReportAccounts%>)</script>
                            </div>
                        </form>    
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>