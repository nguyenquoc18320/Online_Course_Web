/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function CloseFrontDiv()
{
    var front_div = document.getElementById("front-div");
    front_div.style.display = 'none';
//    window.location = "/new_online_course_web/admin";
}

function ShowFrontDiv()
{
    var front_div = document.getElementById("front-div");
    front_div.style.display = 'flex';
}