<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<img src="http://localhost:8090/WebProject/captcha" style="display: block; margin: 0 auto;"><br>
<c:if test = "${not empty applicationScope.captchaId}">
           <input type='hidden' name='hidden' value="${applicationScope.captchaId}"><br>
</c:if>
<input class="form-control" type='text' name='Captcha Value' placeholder='Captcha Value'
    style="width: 30% !important; display: block; margin: 0 auto;">
<br>