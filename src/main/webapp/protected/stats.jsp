<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<div class="app-body">
    <div class="app-content">

        <div class="scrollable scrollable-content section" ng-controller="StatsController">


            <div class="panel-group">
                <div class="panel panel-default" ng-repeat="(i, game) in games">
                    <div class="panel-heading" ng-click="clickOnGame(i)">
                        <h4 class="panel-title">
                            {{game.name}}
                        </h4>
                    </div>
                    <div ng-show="shouldBeVisible(i)">
                        <div class="panel-body">
                            <div ng-repeat="(j, player) in game.players">
                                <a href="/stats/{{game.id}}/{{player.id}}">
                                    {{player.name}}
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/pages/stats.js" />"></script>