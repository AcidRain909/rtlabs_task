<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>RTLabs Task</title>

    <link href="<c:url value="/resources/img/favicon.ico"/>" rel="shortcut icon" type="image/x-icon">
    <link href="<c:url value="/resources/css/reset.css"/>"  rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">

    <script src="<c:url value="/resources/js/jquery-1.8.2.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.maskedinput-1.3.js"/>"></script>
    <script>
        jQuery(function ($) {
            $.mask.definitions['~'] = '[+-]';
            $('.phone').mask('+7(999) 999-99-99');
            $('.date').mask('9999-99-99');
            $('.email').blur(function() {
                    var pattern = /^([a-z0-9_\.-])+@[a-z0-9-]+\.([a-z]{2,4}\.)?[a-z]{2,4}$/i;
                    if(pattern.test($(this).val()) || $(this).val() == ''){
                        $(this).css({'border' : '1px solid #569b44'});
                        $('#valid').text('');
                    } else {
                        $(this).css({'border' : '1px solid #ff0000'});
                        $('#valid').text('e-mail заполнено некорректно. Оно НЕ будет добавлено в вашу заявку.');
                    }
            });
        });
    </script>
</head>
<body>
<div>
    <header>
        <div class="wrapper">
            <div class="sys_name">RT Labs Task</div>
        </div>
    </header>
    <div class="container">
            <div class="wrapper">
