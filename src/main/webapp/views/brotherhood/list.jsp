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

<display:table pagesize="5" class="displaytag" name="brotherhoods" requestURI="${requestURI}" id="row">

	<fmt:formatDate var="format" value="${row.establishmentDate}" pattern="dd/MM/YYYY" />
	
	<spring:message code="brotherhood.titleH" var="titleH" />
	<display:column property="title" title="${titleH}" />
	
	<spring:message code="brotherhood.establishmentDateH" var="establishmentDateH" />
	<display:column property="establishmentDate" title="${establishmentDateH}" format="${format}" />
	
	<spring:message code="brotherhood.commentsH" var="commentsH" />
	<display:column property="comments" title="${commentsH}" />
	
	<spring:message code="brotherhood.membersH" var="membersH" />
	<display:column title="${membersH}">
		<acme:button url="member/list.do?brotherhoodId=${row.id}" code="button.show" />
	</display:column>
	
	<spring:message code="brotherhood.processionsH" var="processionsH" />
	<display:column title="${processionsH}">
		<acme:button url="procession/list.do?brotherhoodId=${row.id}" code="button.show" />
	</display:column>
	
	<spring:message code="brotherhood.floatsH" var="floatsH" />
	<display:column title="${floatsH}">
		<acme:button url="float/list.do?brotherhoodId=${row.id}" code="button.show" />
	</display:column>
	
	<spring:message code="text.infoH" var="infoH" />
	<display:column title="${infoH}">
		<acme:button url="brotherhood/show.do?brotherhoodId=${row.id}" code="button.more" />
	</display:column>
	
</display:table>
