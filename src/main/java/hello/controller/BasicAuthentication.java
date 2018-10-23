package hello.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BasicAuthentication extends WebSecurityConfigurerAdapter {
	@Override
	protected	void configure(AuthenticationManagerBuilder auth) throws Exception { super.configure(auth); }
	
	@Override
	public		void configure(WebSecurity web) throws Exception { super.configure(web); }
	
	@Override
	protected	void configure(HttpSecurity http) throws Exception {
		http.httpBasic().realmName("My sample realm");
		http.authorizeRequests().anyRequest().authenticated();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public		UserDetailsService userDetailsService() {
		return new LoginPrincipal.LoginPrincipalService();
	}
	
	@Bean
	public		PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
