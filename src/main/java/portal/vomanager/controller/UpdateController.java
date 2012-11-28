package portal.vomanager.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;



@Controller("updateController")
@RequestMapping(value = "VIEW")
public class UpdateController {
	
	private static final Logger log = Logger
	.getLogger(UpdateController.class);
	
	@ActionMapping(params = "myaction=updateVo")
	public void searchVo(
			ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus){
		
		try {
			log.info("lancio commando");
			Process p = Runtime.getRuntime().exec("/usr/bin/python /upload_files/get_vo_info.py ");
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(
					new InputStreamReader(stdout));
			String line = null;

			while (((line = output.readLine()) != null)) {

				log.info("[Stdout] " + line);
			}
			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {
				
				log.info("[Stderr] " + line);

			}
			log.info("commando lanciato");
			
			SessionMessages.add(request, "db-updated-successufully");
		} catch (IOException e) {
			
			SessionErrors.add(request, "db-updated-problem");
			log.info("eccezione");
			e.printStackTrace();
		}
		
		response.setRenderParameter("myaction", "vos");
	}

}
