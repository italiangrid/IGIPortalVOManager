package portal.vomanager.util;

import java.util.List;

//import org.apache.log4j.Logger;

import portal.vomanager.domain.Vo;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

public class MyValidator {
	
	//private static final Logger log = Logger.getLogger(MyValidator.class);

	public static boolean validate(Vo target, List<String> errors)
			throws SystemException {
		boolean result = true;

		if (Validator.isNull(target.getVo())) {
			errors.add("vo-name-required");
			result = false;
		}

		if (Validator.isNull(target.getAup())) {
			errors.add("vo-aup-required");
			result = false;
		}

		if (Validator.isNull(target.getDescription())) {
			errors.add("vo-description-required");
			result = false;
		}

		if (Validator.isNull(target.getDiscipline())) {
			errors.add("vo-discipline-required");
			result = false;
		}

		if (Validator.isNull(target.getMiddleware())) {
			errors.add("vo-middleware-required");
			result = false;
		}else{
			if((!target.getMiddleware().equals("gLite"))&&(!target.getMiddleware().equals("ARC"))&&(!target.getMiddleware().equals("UNICORE"))&&(!target.getMiddleware().equals("GLOBUS"))&&(!target.getMiddleware().equals("EMI"))){
				errors.add("vo-middleware-required");
				result = false;
			}
		}
		
		if (Validator.isNull(target.getHost())) {
			errors.add("vo-host-required");
			result = false;
		}
		
		/*if (Validator.isNotNull(target.getEnrollementUrl())) {
			if (Validator.isUrl(target.getEnrollementUrl())) {
				errors.add("vo-enrollement-url-problem");
				result = false;
			}
		}*/
	
		return result;
	}
	
}
