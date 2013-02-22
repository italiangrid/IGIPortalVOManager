package portal.vomanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;

import portal.vomanager.exception.VomanagerException;
import portal.vomanager.util.MyValidator;
import portal.vomanager.util.SendMail;
import portal.vomanager.util.VomanagerConfig;
import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.UserToVoService;
import it.italiangrid.portal.dbapi.services.VoService;



@Controller("actionController")
@RequestMapping(value = "VIEW")
@SessionAttributes("vo")
public class ActionController {
	
	private static final Logger log = Logger.getLogger(ActionController.class);
	
	@Autowired
	private VoService voService;
	
	@Autowired
	private UserToVoService userToVoService;
	
	@RenderMapping(params = "myaction=editVoForm")
	public String showEditUserInfoForm() {
		return "editVo";
	}
	
	@ModelAttribute("vo")
	public Vo getUserInfo(@RequestParam int idVo) {
		return voService.findById(idVo);
	}
	
	@ActionMapping(params = "myaction=editVo")
	public void editUserInfo(@ModelAttribute("vo") Vo vo,
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) throws SystemException{
		ArrayList<String> errors = new ArrayList<String>();
		if (MyValidator.validate(vo, errors)) {
			
			GregorianCalendar c = new GregorianCalendar();
			Date oggi = c.getTime();
			
			vo.setInsertTime(oggi);
		
			voService.save(vo);
			
			VosController.setSearch(request.getParameter(""));
			
			SessionMessages.add(request, "vo-updated-successufully");
			
			response.setRenderParameter("myaction", "vos");
		}else{
			for (String error : errors) {
				SessionErrors.add(request, error);
			}

			response.setRenderParameter("myaction", "editVoForm");
			response.setRenderParameter("idVo", Integer.toString(vo.getIdVo()));
			
		}
		
		
	}
	
	@ActionMapping(params="myaction=removeVo")
	public void removeUserInfo(@RequestParam int idVo, ActionRequest request){
		
		VosController.setSearch(request.getParameter(""));
		voService.delete(idVo);
		SessionMessages.add(request, "vo-deleted-successufully");
		
	}
	
	@ActionMapping(params="myaction=configuredVo")
	public void configuredVo(@RequestParam int idVo, ActionRequest request){
		
		VosController.setSearch(request.getParameter(""));
		Vo vo = voService.findById(idVo);
		
		vo.setConfigured("true");
		
		voService.save(vo);
		
		List<UserInfo> users = userToVoService.findUserByVo(vo);
		
		if(users == null)
			log.error("nooooooo");
		
		
		try {
			for(UserInfo userInfo: users){
				String text = VomanagerConfig.getProperties("Vomanager.properties", "mail").replaceAll("##NL##", "\n").replaceAll("##VO##", vo.getVo()).replaceAll("##HOST##", VomanagerConfig.getProperties("Vomanager.properties", "home.url")).replaceAll("##USER##", userInfo.getFirstName());
				
				SendMail sm = new SendMail(VomanagerConfig.getProperties("Vomanager.properties", "igiportal.mail"), userInfo.getMail(), VomanagerConfig.getProperties("Vomanager.properties", "mail.subject").replaceAll("##VO##", vo.getVo()) , text);
				sm.send();
			}
		} catch (VomanagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SessionMessages.add(request, "db-updated-successufully");
		
	}
	
	@ActionMapping(params="myaction=notConfiguredVo")
	public void notConfiguredVo(@RequestParam int idVo, ActionRequest request){
		
		VosController.setSearch(request.getParameter(""));
		Vo vo = voService.findById(idVo);
		
		vo.setConfigured("false");
		
		voService.save(vo);
		SessionMessages.add(request, "db-updated-successufully");
		
	}

}
