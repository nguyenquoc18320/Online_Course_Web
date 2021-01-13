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

function reportAccounts()
{
    
}

function chartReport(labels_input, data_input) {
    var config = {
        type: 'line',
        data: {
            labels: labels_input,
            datasets: [{
                label: 'Số tài khoản đăng kí',
                backgroundColor: ['rgba(54, 162, 235, 0.2)'],
                borderColor: ['rgba(54, 162, 235, 1)'],
                fill: true,
                data: data_input
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: ''
            },
            scales: {
                xAxes: [{
                    display: true,
                }],
                yAxes: [{
                    display: true,
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    };
    var ctx = document.getElementById('myChartReport').getContext('2d');
    window.myLineRevenue = new Chart(ctx, config);
}

function LoadChartReport(reportAccounts)
{
    var dates = [];
    var counts = [];
    var i = 0;
    dates = Object.keys(reportAccounts);
    dates.forEach(function(date){
       date[i] = new Date(date);
       i++;
    });
    dates.sort(function(a,b){
        return new Date(a) - new Date(b);
    });
    dates.forEach(function(key){
        counts.push(reportAccounts[key.toString()]);  // <- obj is undefined !!
    });
    chartReport(dates, counts);
}

function CloseFrontDivReport()
{
    window.location = "/new_online_course_web/account-managerment?isShowReport=false";
}

function ShowFrontDivReport()
{
    window.location = "/new_online_course_web/account-managerment?isShowReport=true";
}

function CloseFrontDivAddAdmin()
{
    window.location = "/new_online_course_web/account-managerment?isShowAddAdmin=false";
}

function ShowFrontDivAddAdmin()
{
    window.location = "/new_online_course_web/account-managerment?isShowAddAdmin=true";
}

function numberOfPages(type, max = 9999)
{
    var number = document.getElementById("numberOfPages");
    if (type === 'start'){
        number.value = 1;
    }else if (type === 'end'){
        number.value = max;
    }else if (type === 'previous'){
        if (parseInt(number.value) > 1)
            number.value = parseInt(number.value) - 1;
    }else if (type === 'next')
    {
        if (parseInt(number.value) < max)
            number.value = parseInt(number.value) + 1;
    }
}

function reloadDataAccount(){
    var number = document.getElementById("numberOfPages");
    window.location = "/new_online_course_web/account-managerment?numberOfPages=" + number.value;
}