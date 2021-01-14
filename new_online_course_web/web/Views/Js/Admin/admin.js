/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ToggleDropDown(id)
{
    var front_div = document.getElementById(id);
    if (front_div !== null)
        if (front_div.style.display === 'flex')
        {
            front_div.style.display = 'none';
        }
        else
        {
            front_div.style.display = 'flex';
        }
}

function CloseFrontDiv(id)
{
    var front_div = document.getElementById(id);
    front_div.style.display = 'none';
//    window.location = "/new_online_course_web/admin";
}

function ShowFrontDiv(id)
{
    ToggleDropDown('drop-down-setting');
    var front_div = document.getElementById(id);
    front_div.style.display = 'flex';
}

function CloseFrontDivEditInfo()
{
    window.location = "/new_online_course_web/admin?isShowEditInfo=false";
}

function ShowFrontDivEditInfo()
{
    window.location = "/new_online_course_web/admin?isShowEditInfo=true";
}

function CloseFrontDivEditPass()
{
    window.location = "/new_online_course_web/admin?isShowEditPass=false";
}

function ShowFrontDivEditPass()
{
    window.location = "/new_online_course_web/admin?isShowEditPass=true";
}
//student
function CloseFrontDivEditInfoStudent()
{
    window.location = "/new_online_course_web/student?isShowEditInfo=false";
}

function ShowFrontDivEditInfoStudent()
{
    window.location = "/new_online_course_web/student?isShowEditInfo=true";
}

function CloseFrontDivEditPassStudent()
{
    window.location = "/new_online_course_web/student?isShowEditPass=false";
}

function ShowFrontDivEditPassStudent()
{
    window.location = "/new_online_course_web/student?isShowEditPass=true";
}