package carDate.hire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HireDaoImpl implements HireDao {
	
	@Autowired
	private HireRepo hireRepo;
	
	@Override
	public List<Hire> getAllHires() {
		
		List<Hire> list = hireRepo.findAll();
		
		System.out.println("***** Hire List Size " + list.size());
		
		return list;
	}

}
