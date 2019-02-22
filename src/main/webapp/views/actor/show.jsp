<%--
 * index.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<ul style="list-style-type: disc;">
	<spring:message code="actor.name" var="name" />
	<li><b>${name}:</b> <jstl:out value="${actor.name}" /></li>
	
	<jstl:if test="${not empty actor.middleName}">
		<spring:message code="actor.middleName" var="middleName" />
		<li><b>${middleName}:</b> <jstl:out value="${actor.middleName}" /></li>
	</jstl:if>
	
	<spring:message code="actor.surname" var="surname" />
	<li><b>${surname}:</b> <jstl:out value="${actor.surname}" /></li>
	
	<jstl:if test="${not empty actor.photo}">
		<spring:message code="actor.photo" var="photo" />
		<li><b>${photo}:</b></li>
		<img src="<jstl:out value='${actor.photo}' />" />
	</jstl:if>
	
	<spring:message code="actor.email" var="email" />
	<li><b>${email}:</b> <jstl:out value="${actor.email}" /></li>
	
	<jstl:if test="${not empty actor.phoneNumber}">
		<spring:message code="actor.phoneNumber" var="phoneNumber" />
		<li><b>${phoneNumber}:</b> <jstl:out value="${actor.phoneNumber}" /></li>
	</jstl:if>
	
	<jstl:if test="${not empty actor.address}">
		<spring:message code="actor.address" var="address" />
		<li><b>${address}:</b> <jstl:out value="${actor.address}" /></li>
	</jstl:if>
	
	<jstl:if test="${authority == 'BROTHERHOOD'}">
		<spring:message code="brotherhood.title" var="title" />
		<li><b>${title}:</b> <jstl:out value="${actor.title}" /></li>
		
		<spring:message code="brotherhood.establishmentDate" var="establishmentDate" />
		<li><b>${establishmentDate}:</b> <jstl:out value="${actor.establishmentDate}" /></li>
		
		<spring:message code="brotherhood.comments" var="comments" />
		<li><b>${comments}:</b> <jstl:out value="${actor.comments}" /></li>
	</jstl:if>
</ul>

<acme:button url="brotherhood/list.do" code="button.back" />
