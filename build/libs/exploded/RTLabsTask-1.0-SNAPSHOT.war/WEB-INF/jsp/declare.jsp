<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="header.jsp" %>

<div>
    <c:choose>
        <c:when test="${paramsChecking.equals('success')}"><div class='msg success'>Ваша заявка успешно отправлена!</div></c:when>
        <c:otherwise><div class='msg error'> <c:out value="${paramsChecking}" escapeXml="false"/></div></c:otherwise>
    </c:choose>
    <h1>Создание заявки</h1>
    <form id="registration" action="declare" method="post">

        <div id="userLastName">
            <label>Фамилия</label>
            <input type="text" value="${userLastName}" name="userLastName" required>
        </div>

        <div id="userFirstName">
            <label>Имя</label>
            <input type="text" value="${userFirstName}" name="userFirstName" required>
        </div>

        <div id="userPatronymic">
            <label>Отчество</label>
            <input type="text" value="${userPatronymic}" name="userPatronymic">
        </div>

        <div id="userEmail">
            <label>e-mail</label>
            <span id="valid"></span>
            <input type="email" value="${userEmail}" class="email" name="userEmail">
        </div>

        <div id="userPhone">
            <label>Телефон</label>
            <input type="text" value="${userPhone}" class="phone" name="userPhone" required>
        </div>

        <div id="userBirthDate">
            <label>Дата рождения</label>
            <input type="date" value="${userBirthDate}" name="userBirthDate" style="margin-top: 10px;">
        </div>

        <label>Выберите департамент обраащения</label>
        <div class="styledsel">
            <select>
                <option value="1">Департамент 1</option>
                <option value="1">Департамент 2</option>
            </select>
        </div>

        <label>Выберите услугу</label>
        <div class="styledsel">
            <select>
                <option value="1">Услуга 1</option>
                <option value="1">Услуга 2</option>
            </select>
        </div>

        <input type="submit" value="Отправить заявку">
    </form>
</div>

<%@ include file="footer.jsp" %>