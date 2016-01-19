<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="sidebar sidebar-left" ui-prevent-touchmove-defaults>
    <div class="scrollable">
        <h1 class="scrollable-header app-name">StreetChase</h1>
        <div class="scrollable-content">
            <div class="list-group" ui-turn-off='uiSidebarLeft'>
                <a class="list-group-item" href="/streetChase/protected/streetGames">Lista gier<i class="fa fa-chevron-right pull-right"></i></a>
                <a class="list-group-item" href="/streetChase/protected/streetGames/add">Stwórz grę <i
                        class="fa fa-chevron-right pull-right"></i></a>
                <a class="list-group-item" href="/streetChase/protected/statistics">Statystyki <i class="fa fa-chevron-right pull-right"></i></a>
            </div>
        </div>
    </div>
</div>

<%--<div class="app"--%>
<%--ui-swipe-right='Ui.turnOn("uiSidebarLeft")'--%>
<%--ui-swipe-left='Ui.turnOff("uiSidebarLeft")'/>--%>
