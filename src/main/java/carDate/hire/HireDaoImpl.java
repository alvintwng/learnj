package carDate.hire;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HireDaoImpl implements HireDao {
	
	@Autowired
	private HireRepo hireRepo;
	
	@Override
	public List<Hires> getAllHires() {
		
		List<Hires> list = hireRepo.findAll();
		
		//System.out.println("***** Hire List Size " + list.size());
		return list;
	}

	@Override
	public void save(Hires hire) {
		hireRepo.save(hire);

	}

	@Override
	public Hires getHireById(long hireId) {
		
		Optional <Hires> optional =  hireRepo.findById(hireId);
		Hires h = null;
		
		if (optional.isPresent())
			h = optional.get();
		else
			throw new RuntimeException(" Hire not found for id :: " + hireId);
		
		return h;
	}

	@Override
	public void delete(Long hireId) {
		hireRepo.deleteById(hireId);
	}

}
