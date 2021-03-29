/* ref: mHCapStoneProj6/210126-DaoAuthenticate/ */

package carDate.emp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmployeeRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String empName) throws UsernameNotFoundException {
		Employee employee = repo.findByEmpName(empName);
		if(employee==null)
			throw new UsernameNotFoundException(empName);
		
		log.warn("=====> loadUserByUsername, getEmpName: " + employee.getEmpName());
		return new UserDetailImpl(employee);
		}

}
