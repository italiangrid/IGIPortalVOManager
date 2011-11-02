package portal.vomanager.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portal.vomanager.dao.VoDAO;
import portal.vomanager.domain.Vo;

@Service
public class VoServiceImpl implements VoService {

	private static final Logger log = Logger
			.getLogger(VoServiceImpl.class);

	@Autowired
	private VoDAO voDAO;

	@Transactional
	public List<Vo> getAllVo() {
		log.debug("getting all Vo instance");
		return voDAO.getAllVo();
	}

	@Transactional
	public Vo findById(Integer id) {
		log.debug("getting UserInfo instance with id: " + id);
		return voDAO.findById(id);
	}

	@Transactional
	public String findByVo(Vo vo) {
		log.debug("getting Vo instance");
		return voDAO.findById(vo.getIdVo()).getVo();
	}

	@Transactional
	public List<String> getAllDiscplines() {
		return voDAO.getAllDiscipline();
	}

	@Transactional
	public void editVo(Vo vo) {
		voDAO.editVo(vo);
		
	}

	@Transactional
	public void delete(int idVO) {
		voDAO.delete(idVO);
		
	}
	
	@Transactional
	public List<Vo> getAllVoByName(String search) {
		return voDAO.getAllVoByName(search);
	}

	@Transactional
	public void addVo(Vo vo) {
		voDAO.addVo(vo);
		
	}

}
