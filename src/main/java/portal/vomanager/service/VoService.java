package portal.vomanager.service;

import java.util.List;

import portal.vomanager.domain.Vo;

public interface VoService {

	public List<Vo> getAllVo();

	public Vo findById(Integer id);

	public String findByVo(Vo vo);

	public List<String> getAllDiscplines();

	public void editVo(Vo vo);

	public void delete(int idVO);

	public List<Vo> getAllVoByName(String search);

	public void addVo(Vo vo);

}
