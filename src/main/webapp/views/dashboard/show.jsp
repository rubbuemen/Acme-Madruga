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

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryC1"/></summary>

<ul>
<li><b><spring:message code="dashboard.avg"/>:</b> <jstl:out value="${avgQueryC1 == \"null\" ? 0 : avgQueryC1}"></jstl:out></li>
<li><b><spring:message code="dashboard.min"/>:</b> <jstl:out value="${minQueryC1 == \"null\" ? 0 : minQueryC1}"></jstl:out></li>
<li><b><spring:message code="dashboard.max"/>:</b> <jstl:out value="${maxQueryC1 == \"null\" ? 0 : maxQueryC1}"></jstl:out></li>
<li><b><spring:message code="dashboard.stddev"/>:</b> <jstl:out value="${stddevQueryC1 == \"null\" ? 0 : stddevQueryC1}"></jstl:out></li>
</ul>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryC2"/></summary>

<display:table class="displaytag" name="queryC2" id="row">
	<spring:message code="brotherhood.titleH" var="titleH" />
	<display:column property="title" title="${titleH}" />
	
	<spring:message code="brotherhood.establishmentDateH" var="establishmentDateH" />
	<display:column title="${establishmentDateH}">
			<fmt:formatDate var="format" value="${row.establishmentDate}" pattern="dd/MM/YYYY" />
			<jstl:out value="${format}" />
	</display:column>
	
	<spring:message code="brotherhood.commentsH" var="commentsH" />
	<display:column property="comments" title="${commentsH}" />
</display:table>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryC3"/></summary>

<display:table class="displaytag" name="queryC3" id="row">
	<spring:message code="brotherhood.titleH" var="titleH" />
	<display:column property="title" title="${titleH}" />
	
	<spring:message code="brotherhood.establishmentDateH" var="establishmentDateH" />
	<display:column title="${establishmentDateH}">
			<fmt:formatDate var="format" value="${row.establishmentDate}" pattern="dd/MM/YYYY" />
			<jstl:out value="${format}" />
	</display:column>
	
	<spring:message code="brotherhood.commentsH" var="commentsH" />
	<display:column property="comments" title="${commentsH}" />
</display:table>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryC4"/></summary>

<display:table class="displaytag" name="queryC4" id="row">
	<spring:message code="brotherhood.titleH" var="titleH" />
	<display:column title="${titleH}">
		<jstl:out value="${row[0]}" />
	</display:column>
	<spring:message code="requestMarch.status" var="statusH" />
	<display:column title="${statusH}">
		<jstl:out value="${row[1]}" />
	</display:column>
	<spring:message code="dashboard.ratio" var="ratio" />
	<display:column title="${ratio}">
		<jstl:out value="${row[2]}" />
	</display:column>
</display:table>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryC5"/></summary>

<display:table class="displaytag" name="queryC5" id="row">
	<spring:message code="procession.ticker" var="ticker" />
	<display:column property="ticker" title="${ticker}" />
	
	<spring:message code="procession.title" var="title" />
	<display:column property="title" title="${title}" />
	
	<spring:message code="procession.description" var="description" />
	<display:column property="description" title="${description}" />
	
	<spring:message code="procession.momentOrganise" var="momentOrganise" />
	<display:column title="${momentOrganise}">
			<fmt:formatDate var="format" value="${row.momentOrganise}" pattern="dd/MM/YYYY HH:mm" />
			<jstl:out value="${format}" />
	</display:column>
	
	<spring:message code="procession.maxRows" var="maxRows" />
	<display:column property="maxRows" title="${maxRows}" />
	
	<spring:message code="procession.maxColumns" var="maxColumns" />
	<display:column property="maxColumns" title="${maxColumns}" />
	
</display:table>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryC6"/></summary>

<display:table class="displaytag" name="queryC6" id="row">
	<spring:message code="requestMarch.status" var="statusH" />
	<display:column title="${statusH}">
		<jstl:out value="${row[0]}" />
	</display:column>
	<spring:message code="dashboard.ratio" var="ratio" />
	<display:column title="${ratio}">
		<jstl:out value="${row[1]}" />
	</display:column>
</display:table>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryC7"/></summary>

<display:table class="displaytag" name="queryC7" id="row">
	<<spring:message code="actor.name" var="name" />
	<display:column property="name" title="${name}" />
	
	<spring:message code="actor.surname" var="surname" />
	<display:column property="surname" title="${surname}" />
	
	<spring:message code="actor.email" var="email" />
	<display:column property="email" title="${email}" />
	
	<spring:message code="text.infoH" var="infoH" />
	<display:column title="${infoH}">
		<acme:button url="member/show.do?memberId=${row.id}" code="button.more" />
	</display:column>
	
</display:table>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryC8"/></summary>

<display:table class="displaytag" name="queryC8" id="row">
	<spring:message code="positionBrotherhood.name" var="name" />
	<display:column title="${name}">
		<jstl:if test="${language eq 'en'}">
			<jstl:out value="${row[0]}" />
		</jstl:if>
		<jstl:if test="${language eq 'es'}">
			<jstl:out value="${row[1]}" />
		</jstl:if>
	</display:column>
	<spring:message code="dashboard.count" var="count" />
	<display:column title="${count}">
		<jstl:out value="${row[2]}" />
	</display:column>
</display:table>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryB1"/></summary>

<ul>
<li><b><spring:message code="dashboard.ratio"/>:</b> <jstl:out value="${ratioQueryB1 == \"null\" ? 0 : ratioQueryB1}"></jstl:out></li>
<li><b><spring:message code="dashboard.count"/>:</b> <jstl:out value="${countQueryB1 == \"null\" ? 0 : countQueryB1}"></jstl:out></li>
<li><b><spring:message code="dashboard.avg"/>:</b> <jstl:out value="${avgQueryB1 == \"null\" ? 0 : avgQueryB1}"></jstl:out></li>
<li><b><spring:message code="dashboard.min"/>:</b> <jstl:out value="${minQueryB1 == \"null\" ? 0 : minQueryB1}"></jstl:out></li>
<li><b><spring:message code="dashboard.max"/>:</b> <jstl:out value="${maxQueryB1 == \"null\" ? 0 : maxQueryB1}"></jstl:out></li>
<li><b><spring:message code="dashboard.stddev"/>:</b> <jstl:out value="${stddevQueryB1 == \"null\" ? 0 : stddevQueryB1}"></jstl:out></li>
</ul>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryB2"/></summary>

<ul>
<li><b><spring:message code="dashboard.min"/>:</b> <jstl:out value="${minQueryB2 == \"null\" ? 0 : minQueryB2}"></jstl:out></li>
<li><b><spring:message code="dashboard.max"/>:</b> <jstl:out value="${maxQueryB2 == \"null\" ? 0 : maxQueryB2}"></jstl:out></li>
<li><b><spring:message code="dashboard.avg"/>:</b> <jstl:out value="${avgQueryB2 == \"null\" ? 0 : avgQueryB2}"></jstl:out></li>
<li><b><spring:message code="dashboard.stddev"/>:</b> <jstl:out value="${stddevQueryB2 == \"null\" ? 0 : stddevQueryB2}"></jstl:out></li>
</ul>

</details><br/>

<details>
<summary style="font-size: 26px;"><spring:message code="dashboard.queryB3"/></summary>

<ul>
<li><b><spring:message code="dashboard.ratio"/>:</b> <jstl:out value="${ratioQueryB3 == \"null\" ? 0 : ratioQueryB3}"></jstl:out></li>
</ul>

</details><br/>