<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${pageContext.request.locale}"/>
<fmt:setBundle basename="localization"/>

<c:choose>
    <c:when test="${user != null}">
        <form class="form-inline" method="POST" action="logout" style="text-align: right;">
            <c:if test="${sessionScope.avatar != null}">
            <img src="${sessionScope.avatar}" style="width: 15% !important;">
                <div class="form-group mx-sm-3 mb-2 float-right">
            </c:if>
                <p style="color: #fdfdfe; font-size: 20pt;"> Hi, <c:out value="${user.login}"/>!</p>
            </div>
            <button class="btn btn-dark float-right" type="submit"><fmt:message key = "log_out_tag"/></button>
        </form>
    </c:when>
    <c:otherwise>
        <form class="form-inline" method="POST" action="login" style="text-align: right; width: 700px;">
            <div class="form-group mx-sm-3 mb-2 float-right">
                <input type="text" class="form-control" placeholder="<fmt:message key = "login_tag"/>" name="LoginUp"
                       style="width: 70% !important;"
                       value="${LoginUpValue}">
                <div class="errormsg" style="color: red">${FalseLogin}</div>
            </div>
            <div class="form-group mx-sm-3 mb-2 float-right">
                <input type="password" class="form-control" placeholder="<fmt:message key = "pass_tag"/>" name="PasswordUp"
                       style="width: 70% !important;">
                <div class="errormsg" style="color: red">${FalsePassword}</div>
            </div>
            <button type="submit" class="btn btn-success mb-2 float-right"><fmt:message key = "log_in_tag"/></button>
        </form>
    </c:otherwise>
</c:choose>