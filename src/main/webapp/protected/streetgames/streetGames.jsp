<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="row-fluid" ng-controller="streetGamesController">
    <h2>
        <p class="text-center">
            <spring:message code='streetGamesList.header'/>
        </p>
    </h2>

    <%--<div>--%>

        <%--<div ng-class="{'alert badge-inverse': displayMessageToUser == true, 'none': displayMessageToUser == false}">--%>
            <%--<h4 class="displayInLine">--%>
                <%--<p class="messageToUser displayInLine"><i class="icon-info-sign"></i>&nbsp;{{page.actionMessage}}</p>--%>
            <%--</h4>--%>
        <%--</div>--%>

        <%--<div ng-class="{'alert alert-block alert-error': state == 'error', 'none': state != 'error'}">--%>
            <%--<h4><i class="icon-info-sign"></i> <spring:message code="error.generic.header"/></h4><br/>--%>

            <%--<p><spring:message code="error.generic.text"/></p>--%>
        <%--</div>--%>

        <%--<div ng-class="{'alert alert-info': state == 'noresult', 'none': state != 'noresult'}">--%>
            <%--<h4><i class="icon-info-sign"></i> <spring:message code="streetGamesList.emptyData"/></h4><br/>--%>

            <%--<p><spring:message code="streetGamesList.emptyData.text"/></p>--%>
        <%--</div>--%>

        <%--<div>--%>
            <%--<div ng-class="{'text-center': displayCreateTopicButton == true, 'none': displayCreateTopicButton == false}">--%>
                <%--<br/>--%>
                <%--<a href="#addTopicsModal"--%>
                   <%--role="button"--%>
                   <%--ng-click="resetTopic();"--%>
                   <%--title="<spring:message code='create'/>&nbsp;<spring:message code='streetGame'/>"--%>
                   <%--class="btn btn-inverse"--%>
                   <%--data-toggle="modal">--%>
                    <%--<i class="icon-plus"></i>--%>
                    <%--&nbsp;&nbsp;<spring:message code="streetGame.create"/>--%>
                <%--</a>--%>
            <%--</div>--%>

            <%--<div id="gridContainer" ng-class="{'': state == 'list', 'none': state != 'list'}">--%>
                <%--<table class="table table-bordered table-striped">--%>
                    <%--<thead>--%>
                    <%--<tr>--%>
                        <%--<th scope="col"><spring:message code="streetGame.name"/></th>--%>
                        <%--<th scope="col"><spring:message code="streetGame.description"/></th>--%>
                        <%--<th scope="col"><spring:message code="streetGame.isPrivate"/></th>--%>
                        <%--<th scope="col"><spring:message code="streetGame.startTime"/></th>--%>
                        <%--<th scope="col"><spring:message code="streetGame.endTime"/></th>--%>
                        <%--<th scope="col"><spring:message code="streetGame.startPointDesc"/></th>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody>--%>
                    <%--<tr ng-repeat="topic in page.source">--%>
                        <%--<td class="tdTopicsCentered">{{streetGame.name}}</td>--%>
                        <%--<td class="tdTopicsCentered">{{streetGame.description}}</td>--%>
                        <%--<td class="tdTopicsCentered">{{streetGame.isPrivate}}</td>--%>
                        <%--<td class="tdTopicsCentered">{{streetGame.startTime}}</td>--%>
                        <%--<td class="tdTopicsCentered">{{streetGame.endTime}}</td>--%>
                        <%--<td class="tdTopicsCentered">{{streetGame.startPointDesc}}</td>--%>

                        <%--<td class="width15">--%>

                            <%--<div class="text-center">--%>
                                <%--<input type="hidden" value="{{topic.id}}"/>--%>
                                <%--<a href="#updateTopicsModal"--%>
                                   <%--ng-click="selectedTopic(topic);"--%>
                                   <%--role="button"--%>
                                   <%--title="<spring:message code="topic.edit"/>"--%>
                                   <%--class="btn btn-inverse" data-toggle="modal">--%>
                                    <%--<i class="icon-pencil"></i>--%>
                                <%--</a>--%>
                                <%--<security:authorize ifAnyGranted="ROLE_ADMIN">--%>
                                    <%--<a href="#deleteTopicsModal"--%>
                                       <%--ng-click="selectedTopic(topic);"--%>
                                       <%--role="button"--%>
                                       <%--title="<spring:message code="topic.delete"/>"--%>
                                       <%--class="btn btn-inverse" data-toggle="modal">--%>
                                        <%--<i class="icon-minus"></i>--%>
                                    <%--</a>--%>
                                <%--</security:authorize>--%>

                                <%--<input type="hidden" value="{{topic.id}}"/>--%>
                                <%--<a href="#detailsTopicsModal"--%>
                                   <%--ng-click="selectedTopic(topic);"--%>
                                   <%--role="button"--%>
                                   <%--title="<spring:message code="topic.details"/>"--%>
                                   <%--class="btn btn-inverse" data-toggle="modal">--%>
                                    <%--<i class="icon-eye-open"></i>--%>
                                <%--</a>--%>
                                <%--<input type="hidden" value="{{topic.id}}"/>--%>
                                <%--<a href="#"--%>
                                   <%--ng-click="addTopicToDeclared(topic);"--%>
                                   <%--role="button"--%>
                                   <%--title="<spring:message code="topics.declare"/>"--%>
                                   <%--class="btn btn-inverse" data-toggle="modal">--%>
                                    <%--<i class="icon-plus"></i>--%>
                                <%--</a>--%>
                            <%--</div>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--</tbody>--%>
                <%--</table>--%>


            <%--</div>--%>


            <%--<jsp:include page="dialogs/streetGamesDialogs.jsp"/>--%>

        <%--</div>--%>
    <%--</div>--%>

    <br/>
    <br/>
    <br/>

    <script src="<c:url value="/resources/js/pages/streetGames.js" />"></script>