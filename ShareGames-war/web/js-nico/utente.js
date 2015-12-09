/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function logoutUtente() {
    form = $(document.createElement('form'));
    $(form).attr("action", "ServletController");
    $(form).attr("method", "post");
    input = $("<input>").attr("type", "hidden").attr("name", "action").val("logout");
    $(form).append($(input));
    $(form).submit();
}

