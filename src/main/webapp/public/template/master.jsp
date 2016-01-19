<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="pl-PL">
<%--<html>--%>
    <head>
        <title><spring:message code="project.title"/></title>

        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
        <meta name="apple-mobile-web-app-status-bar-style" content="yes" />

        <link href="<c:url value='/resources/bower_components/mobile-angular-ui/dist/css/mobile-angular-ui-base.min.css'  />"
              rel="stylesheet"/>
        <link href="<c:url value='/resources/bower_components/mobile-angular-ui/dist/css/mobile-angular-ui-desktop.min.css'  />"
              rel="stylesheet"/>
        <link href="<c:url value='/resources/bower_components/mobile-angular-ui/dist/css/mobile-angular-ui-hover.min.css'  />"
              rel="stylesheet"/>
        <link href="<c:url value='/resources/css/project_style.css'  />" rel="stylesheet"/>

        <script src="<c:url value='/resources/js/jquery-1.9.1.min.js' />"></script>
        <script src="<c:url value='/resources/js/angular.min.js' />"></script>
        <script src="<c:url value='/resources/bower_components/mobile-angular-ui/dist/js/mobile-angular-ui.js' />"></script>
        <script src="<c:url value='/resources/bower_components/mobile-angular-ui/dist/js/mobile-angular-ui.gestures.min.js' />"></script>

        <script src='//maps.googleapis.com/maps/api/js?key=AIzaSyA4AevpY6ENySQOgqadisjnYbZKYf4yybE'></script>
        <script src="<c:url value='/resources/bower_components/lodash/dist/lodash.js' />"></script>
        <script src="<c:url value='/resources/bower_components/angular-simple-logger/dist/angular-simple-logger.js' />"></script>
        <script src="<c:url value='/resources/bower_components/angular-google-maps/dist/angular-google-maps.js' />"></script>


        <script src="<c:url value='/resources/js/streetChaseApp.js' />"></script>

    </head>
    <body ng-app="streetChaseApp" class="has-navbar-top has-sidebar-left ">
    <%--<body class="has-navbar-top">--%>

        <tiles:insertAttribute name="menu"/>

        <div class="app">
            <tiles:insertAttribute name="header"/>
            <tiles:insertAttribute name="body"/>
        </div>

        <!--[if IE]>
        <script src="<c:url value='/resources/js/bootstrap.min.ie.js' />"></script>
        <![endif]-->
        <!--[if !IE]><!-->
        <script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
        <!--<![endif]-->

        <tiles:insertAttribute name="footer"/>



    </body>
</html>