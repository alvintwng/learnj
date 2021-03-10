/*  ref: /210121L-BankApp, 210203Q-BankApp 
 * 		mHCapStoneProj6/210127-SpringAUth */
package carDate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{

//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		
//		List<UserDetails> users= new ArrayList<>();
//
//		users.add(User.withDefaultPasswordEncoder().
//				username("ntuc").
//				password("ntuc").
//				roles("USER").
//				build());
//			
//		return new InMemoryUserDetailsManager(users);
//	}

	/* for non-Sql login. UN-comment the above, and commented ALL below 
	 * AND commented off MyUserDetailService/MyUserDetailService
	 * */
	@Autowired
	private UserDetailsService userDetailService;
	
	@Bean
	public AuthenticationProvider authprovider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService((UserDetailsService) userDetailService);
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/").hasAnyAuthority("USER", "MANAGER", "ADMIN")
			.antMatchers("/cust/**")	.hasAnyAuthority("USER", "MANAGER")
			.antMatchers("/hire*/**")	.hasAnyAuthority("USER", "MANAGER")
			.antMatchers("/veh*/**")	.hasAnyAuthority("USER", "MANAGER")
			.antMatchers("/emp*/**")	.hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
	}	
	
}