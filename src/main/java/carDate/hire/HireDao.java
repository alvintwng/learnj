package carDate.hire;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface HireDao {
	
	public List<Hires> getAllHires();
	public void save(Hires hires);
	public Hires getHireById(long hireId);
	public void delete(Long hireId);

}
