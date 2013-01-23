package portal.vomanager.controller;

import java.util.List;

import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.italiangrid.portal.dbapi.domain.Vo;
import it.italiangrid.portal.dbapi.services.VoService;



@Controller("vosController")
@RequestMapping(value = "VIEW")
public class VosController {
	
	private static String search = null;
	
	public static void setSearch(String search2){
		search = search2;
	}
	
	@Autowired
	private VoService voService;
	
	@RenderMapping
	public String showVos(RenderResponse response) {
		return "home";
	}

	@ModelAttribute("vos")
	public List<Vo> getVos() {
		
		if(search==null||search.equals(""))
			return voService.getAllVo();
		else
			return voService.getAllVoByName(search);
		
		
	}
	
	@ModelAttribute("search")
	public String getSearch() {
		
		return search;
		
		
	}

}
