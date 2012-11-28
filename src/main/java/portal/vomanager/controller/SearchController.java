package portal.vomanager.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;



@Controller("searchController")
@RequestMapping(value = "VIEW")
public class SearchController {
	
	@ActionMapping(params = "myaction=searchVo")
	public void searchVo(
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus){
		
		VosController.setSearch(request.getParameter("key")); 
		
	
		sessionStatus.setComplete();
		
		response.setRenderParameter("myaction", "vos");
	}
	
	@ActionMapping(params = "myaction=searchReset")
	public void searchReset(
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus){
		
		VosController.setSearch(""); 
		sessionStatus.setComplete();
		
		response.setRenderParameter("myaction", "vos");
	}

}
