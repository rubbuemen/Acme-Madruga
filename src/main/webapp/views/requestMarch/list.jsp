<%--
 * index.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table pagesize="5" class="displaytag" name="requestsMarch" requestURI="${requestURI}" id="row">

	<spring:message code="requestMarch.status" var="status" />
	<display:column property="status" title="${status}" />
	
	<spring:message code="requestMarch.memberH" var="memberH" />
	<display:column title="${memberH}">
		<acme:button url="member/show.do?memberId=${row.member.id}" code="button.show" />
	</display:column>
	
	<spring:message code="requestMarch.decideRequest" var="decideRequest" />
	<display:column title="${decideRequest}">
		<jstl:if test="${row.status eq 'PENDING'}">
			<acme:button url="requestMarch/brotherhood/edit.do?requestMarchId=${row.id}&decision=APPROVED" code="button.approve" />
			<acme:button url="requestMarch/brotherhood/edit.do?requestMarchId=${row.id}&decision=REJECTED" code="button.reject" />
		</jstl:if>	
	</display:column>
		
	<spring:message code="requestMarch.positionProcession" var="positionProcession" />
	<display:column title="${positionProcession}">
		<jstl:if test="${row.status eq 'APPROVED'}">
			<spring:message code="requestMarch.positionRow"/>: <jstl:out value="${row.positionRow}" />
			<spring:message code="requestMarch.positionColumn"/>:<jstl:out value="${row.positionColumn}" />
		</jstl:if>	
	</display:column>
	
	<spring:message code="requestMarch.rejectReason" var="rejectReason" />
	<display:column title="${rejectReason}">
		<jstl:if test="${row.status eq 'REJECTED'}">
			<jstl:out value="${row.rejectReason}" />
		</jstl:if>	
	</display:column>
				
</display:table>

<acme:button url="procession/brotherhood/list.do" code="button.back" />
