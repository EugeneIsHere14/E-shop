<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${pageContext.request.locale}"/>
<fmt:setBundle basename="localization"/>

<li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" href="#" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><fmt:message key = "language"/></a>
    <div class="dropdown-menu" aria-labelledby="dropdown09">
        <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?${queryString}&lang=en">  <fmt:message key = "en_lang"/></a>
        <a class="dropdown-item" href="${requestScope['javax.servlet.forward.request_uri']}?${queryString}&lang=ru">  <fmt:message key = "ru_lang"/></a>
    </div>
</li>