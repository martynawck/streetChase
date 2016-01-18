<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="navbar navbar-app navbar-absolute-top">
    <div class="navbar-brand navbar-brand-center"><span>StreetChase</span></div>
    <div class="btn-group pull-left"><div class="btn">Dodaj nowa</div></div>
    <div class="btn-group pull-right"><div class="btn">Wyloguj</div></div>
</div>
<div class="app-body">
    <div class="app-content" ng-controller="streetGamesController">

        <div class="scrollable scrollable-content section">

            <div class="panel-group">
                <div class="panel panel-default" ng-repeat="(i, game) in page.source">
                    <div class="panel-heading" ng-click="clickOnGame(i)">
                        <h4 class="panel-title">
                            {{game.name}}
                        </h4>
                    </div>
                    <div ng-show="shouldBeVisible(i)">
                        <div class="panel-body">
                            <div><label>Opis:</label> {{game.description}}</div>
                            <div><label>Poczatek:</label> {{game.startPointDesc}}</div>
                            <div><label>Start: </label> {{game.startTime}}</div>
                            <div><label>Koniec:</label> {{game.endTime}}</div>
                            <div><label>Prywatna:</label> <span ng-show="game.isPrivate">TAK</span><span
                                    ng-show="!game.isPrivate">NIE</span></div>
                        </div>
                    </div>
                </div>


            </div>

        </div>
    </div>
</div>

<br/>
<br/>
<br/>

<script src="<c:url value="/resources/js/pages/streetGames.js" />"></script>