$(document).ready(function () {

    /* $("form#registration select[name='userRole']").change(function () {

         var userRole = $("form#registration select[name='userRole']").val();


         if (parseInt(userRole) == 3) {
             $("form#registration #userAge").fadeIn();
             return false;
         }
         else {
             $("form#registration #userAge").fadeOut();
             return false;
         }
     });*/

    //меню
    $("section .sidebar .open_close").click(function () {
        if ($(this).attr("class") == "open_close") {
            $("section .sidebar").animate({
                width: "+=210"
            }, 1000, function () {
                // Animation complete.
            });
            $(this).attr("class", "open_close close");
            $("section .sidebar a").css({"color": "#2bad4d"});
        }
        else {
            $("section .sidebar").animate({
                width: "-=210"
            }, 1000, function () {
                // Animation complete.
            });
            $(this).attr("class", "open_close");
            $("section .sidebar a").css({"color": "transparent"});
        }
    });

    //завуч. управление группами
    var group = $("form#groups table tbody tr");

    function sendAjaxForGroups(status, groupName, groupId) {

        var object = {status: status, groupName: groupName, groupId: groupId};

        jQuery.ajax(
            {
                url: "/AjaxForGroups/",
                type: "POST",
                data: object,
                success: function (html) {
                    $("#rez").html("<div class='msg success'>" + status + " success</div>");
                }
            });
    }

    $(group).each(function (i) {
        $(group[i]).find("td a.update").click(function () {
            sendAjaxForGroups("update", $(group[i]).find("input[name='group_name']").val(), $(this).attr("data-group-id"));

            return false;
        });
        $(group[i]).find("td a.delete").click(function () {
            sendAjaxForGroups("delete", $(group[i]).find("input[name='group_name']").val(), $(this).attr("data-group-id"));

            $(group[i]).remove();
            return false;
        });
    });

    //Завуч. добавление урока в расписание
    function sendAjaxAddLesson(subjectId, date, intervalId, groupId) {

        var object = {
            subjectId: subjectId,
            date: date,
            intervalId: intervalId,
            groupId: groupId
        };

        jQuery.ajax(
            {
                url: "/ajaxAddLesson/",
                type: "POST",
                data: object,
                success: function (html) {
                    $("#add_rez").html("<div class='msg success'>новый урок добавлен!</div>");
                }

            });
    }

    $("form#add_lesson").submit(function () {

        sendAjaxAddLesson($("form#add_lesson select[name='subject_name']").val(),
            $("form#add_lesson input[name='date']").val(),
            $("form#add_lesson select[name='time']").val(),
            $("form#add_lesson select[name='group']").val());
        return false;
    });


    //Завуч. удаление урока в расписании
    function sendAjaxDeleteLesson(lessonId) {

        var object = {lessonId: lessonId};

        jQuery.ajax(
            {
                url: "/ajaxDeleteLesson/",
                type: "POST",
                data: object,
                success: function (html) {
                    $("#del_rez").html("<div class='msg error'>урок удалён!</div>");
                }

            });

    }

    var lesson = $("form#timetable table tbody tr");
    $(lesson).each(function (i) {
        $(lesson[i]).find("td a.delete").click(function () {
            sendAjaxDeleteLesson($(this).attr("data-timetable-id"));
            $(lesson[i]).remove();
            return false;
        });
    });


    //Завуч. удаление предмета
    function sendAjaxSubjectDelete(subjectId) {
        var object = {subjectId: subjectId};
        jQuery.ajax(
            {
                url: "/AjaxSubjectDelete/",
                type: "POST",
                data: object,
                success: function (html) {
                    $("#rez").html("<div class='msg error'>предмет удалён</div>");
                }

            });

    }

    var subject = $("form#subjects table tbody tr");
    $(subject).each(function (i) {
        $(subject[i]).find("td a.delete").click(function () {
            sendAjaxSubjectDelete($(this).attr("data-subject-id"));
            $(subject[i]).remove();
            return false;
        });
    });

});