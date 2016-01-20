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
                            <span>{{data.playerName}}</span>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/pages/statsForGamePlayer.js" />"></script>