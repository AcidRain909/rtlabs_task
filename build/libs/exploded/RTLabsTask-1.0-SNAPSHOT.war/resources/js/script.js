$(document).ready(function () {
    $( "#departmentSelect" ).change(function() {
        var departmentCode = $("#departmentSelect option:selected" ).val();
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
                    $.each(services, function(i, obj) {
                        $('#serviceSelect').append($('<option>').text("" + obj.name).attr('value', obj.code));
                    });
                }
            });
    }

});

