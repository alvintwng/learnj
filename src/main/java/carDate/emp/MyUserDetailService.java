package carDate.emp;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailService implements UserDetailsService {
//	
//	@Autowired
//	private EmployeeRepo repo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String empName) throws UsernameNotFoundException {
//		Employee employee = repo.findByEmpName(empName);
//		if(employee==null)
//			throw new UsernameNotFoundException(empName);
//		
//		return new UserDetailImpl(employee);
//		}
//
//}
