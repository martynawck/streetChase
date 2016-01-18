<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="navbar navbar-app navbar-absolute-top">
    <div class="navbar-brand navbar-brand-center"><span>StreetChase</span></div>
</div>
<div class="app-body">
    <div class="app-content" ng-controller="newGameController">

        <div class="scrollable scrollable-content section">

            <ul class="nav nav-tabs">
                <li ng-class="{'active': activeTab == 0}">
                    <a ng-click="setActiveTab(0)">Gra</a>
                </li>
                <li ng-class="{'active': activeTab == 1}">
                    <a ng-click="setActiveTab(1)">Trasa</a>
                </li>
                <li ng-class="{'active': activeTab == 2}">
                    <a ng-click="setActiveTab(2)">Punkty</a>
                </li>
            </ul>

            <div ng-show="isActiveTab(0)">
                <p>tutaj bedzie form</p>
            </div>

            <div ng-show="isActiveTab(1)">
                <div my-map=""></div>
            </div>

            <div ng-show="isActiveTab(2)">
                <p>tu bedzie lista punktow i pytania do uzupelnienia</p>
            </div>

        </div>
    </div>
</div>

<br/>
<br/>
<br/>

<script src="<c:url value="/resources/js/pages/streetGamesAddNew.js" />"></script>
