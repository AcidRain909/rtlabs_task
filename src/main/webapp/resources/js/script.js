$(document).ready(function () {
    $("#departmentSelect").change(function () {
        var departmentCode = $("#departmentSelect option:selected").val();
        getServicesListAjax(departmentCode);
    });

    function getServicesListAjax(departmentCode) {
        var object = {departmentCode: departmentCode};
        $.ajax({
            url: "/serviceListAjax/",
            type: "POST",
            dataType: "json",
            data: object,
            success: function (services) {
                $('#serviceSelect').empty();
                $.each(services, function (i, obj) {
                    $('#serviceSelect').append($('<option>').text(obj.name).val(obj.id));
                });
            }
        });
    }

    $( "#database_button" ).click(function() {
        getApplicationsAjax();
    });

        function getApplicationsAjax() {
            $.ajax({
                url: "/getApplicationsAjax/",
                type: "POST",
                dataType: "json",
                success: function (applications) {
                    $('#body_for_applications').empty();
                    $.each(applications, function (i, obj) {
                        $('#body_for_applications').append('<tr><td>' + obj.applicationId
                            + '</td><td>' + obj.created
                            + '</td><td>' + obj.userFullName
                            + '</td><td>' + obj.userBirthDate
                            + '</td><td>' + obj.userContacts
                            + '</td><td>' + obj.serviceName
                            + '</td><td>' + obj.departmentName
                            + '</td></tr>');
                    });
                    $('#blackout').fadeIn();
                }
            });
        }

    $( "#blackout" ).click(function() {
        $('#blackout').fadeOut();
    });
});

