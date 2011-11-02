package portal.vomanager.dao;

import java.util.List;

import portal.vomanager.domain.Vo;

public interface VoDAO {

	public List<Vo> getAllVo();

	public Vo findById(Integer id);

	public List<String> getAllDiscipline();

	public void editVo(Vo vo);

	public void delete(int idVO);

	public List<Vo> getAllVoByName(String search);

	public void addVo(Vo vo);

}
