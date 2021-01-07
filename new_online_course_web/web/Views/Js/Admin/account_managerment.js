/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    

function LoadUsers(userId, name, email, role, status)
{
    var tbody_table = document.getElementById("tbody-table");
    //tbody_table.empty();
    var NewTr = document.createElement("tr");
    var self = NewTr;

    var UserId = document.createElement('td');
    UserId.setAttribute('class', 'center');
    UserId.innerHTML = userId;
    NewTr.appendChild(UserId);

    var Name = document.createElement('td');
    Name.innerHTML = name;
    NewTr.appendChild(Name);

    var Email = document.createElement('td');
    Email.innerHTML = email;
    NewTr.appendChild(Email);

    var Role = document.createElement('td');
    Role.setAttribute('class', 'center');
    Role.innerHTML = role;
    NewTr.appendChild(Role);

    var Status = document.createElement('td');
    Status.setAttribute('class', 'center');
    //Status.innerHTML = status//account.getUserId();
    var CheckStatus = document.createElement('input');
    CheckStatus.setAttribute('type','checkbox');
    if (status === 'true' || status === true)
        CheckStatus.setAttribute('checked', 'true');
     CheckStatus.setAttribute('onchange','changeStatus(' + status + ', '+ userId +')');
    Status.appendChild(CheckStatus);
    NewTr.appendChild(Status);
    
    
    var Detail = document.createElement('td');
    Detail.setAttribute('class', 'center');
    Detail.innerHTML = '<i class="fas fa-info-circle"></i>';
    NewTr.appendChild(Detail);
    
    tbody_table.appendChild(NewTr); 
}

function changeStatus(status, userId)
{
    status = !status;
    window.location = "/new_online_course_web/account-managerment?status=" + status + "&userId=" + userId;
}