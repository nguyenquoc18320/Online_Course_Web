/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ToggleDropDown(id)
{
    var drop_down = document.getElementById(id);
    if (drop_down !== null)
        if (drop_down.style.display === 'flex')
        {
            drop_down.style.display = 'none';
        }
        else
        {
            drop_down.style.display = 'flex';
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
    window.location = "/new_online_course_web/account-profile?isShowEditInfo=false"; 
}

function ShowFrontDivEditInfo()
{
    window.location = "/new_online_course_web/account-profile?isShowEditInfo=true";
}

function CloseFrontDivEditPass()
{
    window.location = "/new_online_course_web/account-profile?isShowEditPass=false";
}

function ShowFrontDivEditPass()
{
    window.location = "/new_online_course_web/account-profile?isShowEditPass=true";
}
