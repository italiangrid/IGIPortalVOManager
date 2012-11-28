package portal.vomanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;

import portal.vomanager.domain.Vo;
import portal.vomanager.service.VoService;
import portal.vomanager.util.MyValidator;

@Controller("addController")
@RequestMapping(value = "VIEW")
@SessionAttributes("vo")
public class AddController {

	@Autowired
	private VoService voService;
	
	@RenderMapping(params = "myaction=addVoForm")
	public String showEditUserInfoForm() {
		return "addVo";
	}
	
	@ModelAttribute("vo")
	public Vo getUserInfo() {
		return new Vo();
	}
	
	@ActionMapping(params = "myaction=addVo")
	public void editUserInfo(@ModelAttribute("vo") Vo vo,
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws SystemException{
		ArrayList<String> errors = new ArrayList<String>();
		if (MyValidator.validate(vo, errors)) {
			
			GregorianCalendar c = new GregorianCalendar();
			Date oggi = c.getTime();
			
			vo.setInsertTime(oggi);
		
			voService.addVo(vo);
			
			VosController.setSearch(request.getParameter(""));
			
			SessionMessages.add(request, "vo-saved-successufully");
			
			response.setRenderParameter("myaction", "vos");
		}else{
			for (String error : errors) {
				SessionErrors.add(request, error);
			}

			response.setRenderParameter("myaction", "addVoForm");
			request.setAttribute("vo", vo);
		}
		
		
	}
}
