<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<div class="app-body">
    <div class="app-content">

        <div class="scrollable scrollable-content section" ng-controller="StatsForGamePlayerController">

            <ul class="nav nav-tabs">
                <li ng-class="{'active': activeTab == 0}">
                    <a ng-click="setActiveTab(0)">Trasa</a>
                </li>
                <li ng-class="{'active': activeTab == 1}">
                    <a ng-click="setActiveTab(1)">Dane</a>
                </li>
            </ul>

            <div class="stopro" ng-show="isActiveTab(0)">
                <div my-map=""></div>
            </div>

            <div ng-show="isActiveTab(1)">
                <br/>
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <label>Gra:</label>
                            <span>{{data.gameName}}</span> <br/>
                            <label>Gracz:</label>
                            <span>{{data.playerName}}</span> <br/>
                            <label>Przebyta odległość:</label>
                            <span>{{data.routeLength}} m</span> <br/>
                            <label>Czas przejścia:</label>
                            <span>{{data.routeTime}}</span> <br/>
                            <label>Średnia prędkość:</label>
                            <span>{{data.routeSpeed}} m/s</span> <br/>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table>
                                <thead><tr>
                                    <th>Odcinek</th>
                                    <th>Przebyto</th>
                                    <th>Czas</th>
                                    <th>Prędkość</th>
                                </tr></thead>
                                <tbody>
                                    <tr ng-repeat="section in data.sections">
                                        <td>{{section.name}}</td>
                                        <td>{{section.length}} m </td>
                                        <td>{{section.time}}</td>
                                        <td>{{section.speed}}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/pages/statsForGamePlayer.js" />"></script>