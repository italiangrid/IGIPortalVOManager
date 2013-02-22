
<%
	/**
	 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
	 *
	 * This library is free software; you can redistribute it and/or modify it under
	 * the terms of the GNU Lesser General Public License as published by the Free
	 * Software Foundation; either version 2.1 of the License, or (at your option)
	 * any later version.
	 *
	 * This library is distributed in the hope that it will be useful, but WITHOUT
	 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
	 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
	 * details.
	 */
%>

<%@ include file="/WEB-INF/jsp/init.jsp"%>

<style>
div.function {
	padding: 1em 5em 1em 1em;
	border: 1px;
	border-color: #C8C9CA;
	border-style: solid;
	background-color: #D1D6DC;
}
#tabella {
	margin: 3px 0 3px 0;
	padding: 1em;
/* 	border: 1px; */
/* 	border-color: #C8C9CA; */
/* 	border-style: solid; */
}
#search{float:left;}
#addVo{float:left;}
#clear{clear:both;}
</style>

<liferay-ui:success key="vo-deleted-successufully"
	message="vo-deleted-successufully" />
<liferay-ui:success key="vo-updated-successufully"
	message="vo-updated-successufully" />
<liferay-ui:success key="db-updated-successufully"
	message="db-updated-successufully" />
<liferay-ui:success key="vo-saved-successufully"
	message="vo-saved-successufully" />
	
	
	
<liferay-ui:error key="db-updated-problem"
	message="db-updated-problem" />

<div class="function">
<aui:fieldset>
<aui:column columnWidth="50">
<portlet:actionURL var="searchVOActionUrl">
	<portlet:param name="myaction" value="searchVo" />
</portlet:actionURL>

<aui:form name="searchVO" method="post"
	action="${searchVOActionUrl}">
	<aui:layout>
		<aui:button-row>
		<aui:input name="key" label="Cerca VO" type="text" inlineField="true" inlineLabel="true"/>
		<aui:button type="submit" value="Cerca" inlineField="true"/>
		<portlet:actionURL var="backURL">
			<portlet:param name="myaction" value="searchReset" />
		</portlet:actionURL>
		<aui:button type="cancel" value="Azzera Ricerca"
			onClick="location.href='${backURL}';" />
		</aui:button-row>
	</aui:layout>
</aui:form>

<c:if test="${!empty search }">
	<br/>
	Ricerca: <strong><c:out value="${search}" /></strong>	
</c:if>
</aui:column>
<aui:column columnWidth="50">
<portlet:renderURL var="addVOForm">
	<portlet:param name="myaction" value="addVoForm" />
</portlet:renderURL>

<aui:form name="addVO"
	action="${addVOForm}">
	<aui:layout>
		<aui:button-row align="right">
		<aui:button type="submit" value="Aggiungi VO" inlineField="true" />
		</aui:button-row>
	</aui:layout>
</aui:form>
</aui:column>
</aui:fieldset>
</div>
<div id="tabella">
<jsp:useBean id="vos"
	type="java.util.List<it.italiangrid.portal.dbapi.domain.Vo>" scope="request" />

<liferay-ui:search-container
	emptyResultsMessage="Non ci sono VO con questo nome" delta="20">
	<liferay-ui:search-container-results>
		<%
			results = ListUtil.subList(vos, searchContainer.getStart(),
							searchContainer.getEnd());
					total = vos.size();

					pageContext.setAttribute("results", results);
					pageContext.setAttribute("total", total);
		%>


	</liferay-ui:search-container-results>
	<liferay-ui:search-container-row
		className="it.italiangrid.portal.dbapi.domain.Vo" keyProperty="idVo"
		modelVar="vo">
		<liferay-ui:search-container-column-text name="VO Name"
			property="vo" />
		<liferay-ui:search-container-column-text name="Disciplina VO"
			property="discipline" />
		<liferay-ui:search-container-column-text name="Hostname">
			<a href="https://${vo.host}" target="_blank"><c:out value="${fn:substring(vo.host,0,25)}" />
			</a>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Enrollement URL">
			<a href="${vo.enrollementUrl}" target="_blank"><c:out value="${fn:substring(vo.enrollementUrl,0,25)}" />
			</a>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Descrizione VO">
			<c:out value="${fn:substring(vo.description,0,50)}" />
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="AUP">
			<c:out value="${fn:substring(vo.aup,0,50)}" />
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text name="Middleware"
			property="middleware" />
		<liferay-ui:search-container-column-text name="Configured"
			property="configured" />
		<c:if test="${vo.configured=='false' }">
		<liferay-ui:search-container-column-jsp
					path="/WEB-INF/jsp/admin-action.jsp" align="right" />
		</c:if>
		<c:if test="${vo.configured=='true' }">
		<liferay-ui:search-container-column-jsp
					path="/WEB-INF/jsp/admin-action-not-configured.jsp" align="right" />
		</c:if>
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>

</div>
<div class="function">
<portlet:actionURL var="updateVOActionUrl">
	<portlet:param name="myaction" value="updateVo" />
</portlet:actionURL>

<aui:form name="searchVO"
	action="${updateVOActionUrl}">
	<aui:layout>
		<aui:button type="submit" value="Update VO" inlineField="true"/>
		<c:if test="${!empty vos }">
			Ultimo update: <strong><c:out value="${vos[0].insertTime}" /></strong>
		</c:if>
	</aui:layout>
</aui:form>

</div>