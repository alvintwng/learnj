package carDate.emp;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//public class UserDetailImpl implements UserDetails {
//	
//	private Employee employee;
//
//	public UserDetailImpl(Employee employee) {
//		super();
//		this.employee = employee;
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
////		Set<Role> roles = employee.getRoles();
////		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
////		
////		for (Role role : roles) {
////			authorities.add(new SimpleGrantedAuthority(role.getName()));
////
////		}
////		
////		return authorities;
//		return null;
//	}
//	
//	@Override
//	public String getPassword() {
//			return employee.getPassword();
//	}
//	
//
//	@Override
//	public String getUsername() {
//		return employee.getEmpName();
//	}
//	
//	@Override
//	public boolean isAccountNonExpired() {
//		return (employee.getUserExpiry().isAfter(java.time.LocalDate.now()));
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return employee.getIsActive() && (employee.getUserExpiry().isAfter(java.time.LocalDate.now()));
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return (employee.getPswdExpiry().isAfter(java.time.LocalDate.now()));
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return employee.getIsActive();
//	}
//
//}
