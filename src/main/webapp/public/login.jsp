<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="app-body">
    <div class="app-content" ng-controller="loginController">
        <div class="scrollable scrollable-content section">
            <legend><spring:message code="login.header" /></legend>
            <%--<div class="alert alert-error" ng-class="{'': displayLoginError == true, 'none': displayLoginError == false}">--%>
                <%--<spring:message code="login.error" />--%>
            <%--</div>--%>
            <form method="post" action="j_spring_security_check">
                <div class="form-group">
                    <label>Login</label>
                    <input name="j_username" id="j_username" type="text" class="form-control" placeholder="Login "><br/>
                    <label>Haslo</label>
                    <input name="j_password" id="j_password" type="password"  class="form-control" placeholder="Haslo"><br/>
                </div>
                <button type="submit" name="submit" class="btn btn-primary btn-block"><spring:message code="login.signIn" /></button>
            </form>
        </div>
    </div>
</div>
<script src="<c:url value='/resources/js/pages/login.js' />"></script>