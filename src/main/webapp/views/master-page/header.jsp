<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="welcome/index.do"><img src="${bannerUrl}" alt="${nameSystem} Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="brotherhood/list.do"><spring:message code="master.page.brotherhoods" /></a></li>
			<li><a class="fNiv"><spring:message code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/register-brotherhood.do"><spring:message code="master.page.register.brotherhood" /></a></li>
					<li><a href="actor/register-member.do"><spring:message code="master.page.register.member" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('BROTHERHOOD')">
			<li>
				<a class="fNiv"><spring:message code="master.page.brotherhood" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="float/brotherhood/list.do"><spring:message code="master.page.floats" /></a></li>
					<li><a href="procession/brotherhood/list.do"><spring:message code="master.page.processions" /></a></li>
					<li><a href="member/brotherhood/list.do"><spring:message code="master.page.members" /></a></li>
					<li><a href="enrolment/brotherhood/list.do"><spring:message code="master.page.enrolments" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('MEMBER')">
			<li>
				<a class="fNiv"><spring:message code="master.page.member" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="requestMarch/member/list.do"><spring:message code="master.page.requestsMarch" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		
		<security:authorize access="hasRole('ADMIN')">
			<li>
				<a class="fNiv"><spring:message code="master.page.admin" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/administrator/register-administrator.do"><spring:message code="master.page.register.admin" /></a></li>	
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
		<li><a class="fNiv" href="brotherhood/list.do"><spring:message code="master.page.brotherhoods" /></a></li>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<security:authorize access="hasRole('BROTHERHOOD')">
						<li><a href="actor/brotherhood/edit.do"><spring:message code="master.page.edit.profile" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('MEMBER')">
						<li><a href="actor/member/edit.do"><spring:message code="master.page.edit.profile" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('ADMIN')">
						<li><a href="actor/administrator/edit.do"><spring:message code="master.page.edit.profile" /></a></li>
					</security:authorize>		
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a style="text-decoration: underline; color: mediumblue; cursor: pointer;" onClick="javascript:preventRedirect('language=en')">en</a> | <a style="text-decoration: underline; color: mediumblue; cursor: pointer;" onClick="javascript:preventRedirect('language=es')" >es</a>
</div>

<script type="text/javascript">
	function preventRedirect(path) {
		var currentUrl = window.location.href;
		if (currentUrl.indexOf("language=en") > 0) {
			currentUrl = currentUrl.replace("language=en", path);
		} else if (currentUrl.indexOf("language=es") > 0) {
			currentUrl = currentUrl.replace("language=es", path);
		} else if (currentUrl.indexOf("?") > 0) {
			currentUrl += "&" + path;
		} else {
			currentUrl += "?" + path;
		}
		window.location.replace(currentUrl);
	}
</script>

