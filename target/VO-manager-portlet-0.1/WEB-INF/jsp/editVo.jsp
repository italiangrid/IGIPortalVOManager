<%@ include file="/WEB-INF/jsp/init.jsp"%>

<portlet:actionURL var="editVoActionUrl">
	<portlet:param name="myaction" value="editVo" />
</portlet:actionURL>
<portlet:renderURL var="homeUrl">
	<portlet:param name="myaction" value="vos" />
</portlet:renderURL>

<jsp:useBean id="vo" type="portal.vomanager.domain.Vo"
	scope="request" />

<liferay-ui:error key="vo-name-required"
	message="vo-name-required" />
	<liferay-ui:error key="vo-aup-required"
	message="vo-aup-required" />
	<liferay-ui:error key="vo-description-required"
	message="vo-description-required" />
	<liferay-ui:error key="vo-discipline-required"
	message="vo-discipline-required" />
	<liferay-ui:error key="vo-middleware-required"
	message="vo-middleware-required" />
	<liferay-ui:error key="vo-host-required"
	message="vo-host-required" />
	<liferay-ui:error key="vo-host-url-problem"
	message="vo-host-url-problem" />
	<liferay-ui:error key="vo-enrollement-url-problem"
	message="vo-enrollement-url-problem" />
	


<br/><br/>

<aui:form name="editVoForm" commandName="vo"
			action="${editVoActionUrl}">

			<aui:layout>

				<aui:fieldset>
					<aui:column columnWidth="30">
						<aui:fieldset label="Dati VO">
							<br></br>

							<aui:input name="idVo" type="hidden"
								value="<%=vo.getIdVo() %>" />
							<aui:input name="vo" type="text" style="width: 300px;"
								value="<%=vo.getVo() %>" label="Nome VO" />
							<aui:input name="host" type="text" style="width: 300px;"
								value="<%=vo.getHost() %>" label="Hostname" />
							<aui:input name="voManagerMail" type="text" style="width: 300px;"
								value="<%=vo.getVoManagerMail() %>" label="e-Mail VO Manager" />
							<aui:input name="enrollementUrl" type="text" style="width: 300px;"
								value="<%=vo.getEnrollementUrl() %>" label="Enrollement URL" />
							<aui:input name="discipline" type="text" style="width: 300px;"
								value="<%=vo.getDiscipline() %>" label="Disciplina" />
							<aui:input name="middleware" type="text" style="width: 300px;"
								value="<%=vo.getMiddleware() %>" label="Tipo Middleware" />
							<aui:input name="configured" type="checkbox" value="<%=vo.getConfigured() %>"/>

						</aui:fieldset>
					</aui:column>
					
					<aui:column columnWidth="70">
						<aui:fieldset label="Descrizione e AUP">
							<br></br>
							

							<aui:input name="description" type="textarea" rows="9" cols="160"
								value="<%=vo.getDescription() %>" label="Descizione VO" />
							<aui:input name="aup" type="textarea" rows="9" cols="160"
								value="<%=vo.getAup() %>" label="AUP" />

						</aui:fieldset>
					</aui:column>

					<aui:button-row>
						<aui:button type="submit" />
						<aui:button type="cancel" value="Back"
						onClick="location.href='${homeUrl}';" />
					</aui:button-row>
				</aui:fieldset>
			</aui:layout>
		</aui:form>