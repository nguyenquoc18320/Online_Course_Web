<%-- 
    Document   : account_managerment
    Created on : Jan 5, 2021, 2:02:49 PM
    Author     : TRAN VAN AN
--%>

<%@page import="DAO.*"%>
<%@page import="Model.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> <!--css-->
        <link href="Views/Css/common.css" type="text/css" rel="stylesheet">
        <link href="Views/Css/Admin/admin.css" type="text/css" rel="stylesheet"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        
        <script src="Views/Js/Admin/account_managerment.js"></script>
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
        
        <form action="account-managerment" method="post">
            <div class="small-container">
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
                                       List<Account> accounts = (List<Account>)request.getAttribute("Accounts");
                                       List<Role> roles = (List<Role>)request.getAttribute("Roles");
                                       if (users != null && accounts != null)
                                       {
                                            %> <script> tbody_table.empty();</script> <%
                                           for (int i = 0; i < users.size(); i++)
                                           {
                                               User user = (User)users.get(i);
                                               if (accounts != null && roles != null)
                                               {
                                                    Account account = AccountDB.getAccountInListByUserId(accounts, user.getUserId());
                                                    Role role = RoleDB.GetRoleInListByRoleId(roles, user.getRole());
                                                    %> <script>LoadUsers("<%=user.getUserId()%>", "<%=user.getName()%>", "<%=user.getEmail()%>",
                                                                "<%=role.getRoleName()%>", "<%= account.isStatus()%>")</script> <%
                                                }
                                           }
                                       }
                                   %>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </form>    
        
        
    </body>
</html>
