<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<div class="app-body">
    <div class="app-content" ng-controller="newGameController">

        <div class="scrollable scrollable-content section">

            <ul class="nav nav-tabs">
                <li ng-class="{'active': activeTab == 0}">
                    <a ng-click="setActiveTab(0)">Trasa</a>
                </li>
                <li ng-class="{'active': activeTab == 1}">
                    <a ng-click="setActiveTab(1)">Punkty</a>
                </li>
                <li ng-class="{'active': activeTab == 2}">
                    <a ng-click="setActiveTab(2)">Gra</a>
                </li>
            </ul>

            <div class="stopro" ng-show="isActiveTab(0)">
                    <div my-map=""></div>
            </div>

            <div ng-show="isActiveTab(1)">
                <br/>
                <div ng-repeat="(i, point) in page.points">
                    <h4>{{point.address}}</h4>
                    <form method="post" action="j_spring_security_check">
                        <div class="form-group">
                            <label>Wskazówka</label>
                            <input type="text" ng-model="point.hint" class="form-control" placeholder="Wskazówka"><br/>
                            <label>Pytanie</label>
                            <input type="text" ng-model="point.question" class="form-control" placeholder="Pytanie"><br/>
                            <label>Odpowiedź</label>
                            <input type="text" ng-model="point.answer" class="form-control" placeholder="Odpowiedź"><br/>
                        </div>
                    </form>
                </div>
            </div>

            <div ng-show="isActiveTab(2)">
                <form name="basicDataForm">
                    <div class="form-group">
                        <label>Nazwa</label>
                        <input ng-model="game.name" type="text" class="form-control" placeholder="Nazwa"><br/>
                        <label>Opis</label>
                        <textarea ng-model="game.description" class="form-control"></textarea><br/>
                        <label ng-show="game.isPrivate">Gra prywatna</label>
                        <label ng-show="!game.isPrivate">Gra publiczna</label>
                        <ui-switch  ng-model="game.isPrivate"></ui-switch>
                        <label>Start</label>
                        <input type="datetime-local" ng-model="game.startTime" class="form-control"
                               placeholder="yyyy-MM-ddTHH:mm:ss" min="2001-01-01T00:00:00" max="2017-12-31T00:00:00"/><br/>
                        <label>Koniec</label>
                        <input type="datetime-local" ng-model="game.endTime" class="form-control"
                               placeholder="yyyy-MM-ddTHH:mm:ss" min="2001-01-01T00:00:00" max="2017-12-31T00:00:00"/><br/>

                        <button class="btn btn-primary btn-block" ng-click="createGame()">Stwórz grę</button>
                    </div>
                </form>
            </div>



        </div>
    </div>
</div>

<br/>
<br/>
<br/>

<script src="<c:url value="/resources/js/pages/streetGamesAddNew.js" />"></script>
