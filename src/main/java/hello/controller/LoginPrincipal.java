package hello.controller;

import java.util.Map;
import java.util.HashMap;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginPrincipal extends org.springframework.security.core.userdetails.User {

	private	final	String		employeeNumber;
	
	public LoginPrincipal(String loginId, String employeeNumber, String encodedPassword, String[] roles) {
		super(loginId, encodedPassword, true, true, true, true, AuthorityUtils.createAuthorityList(roles));
		this.employeeNumber = employeeNumber;
	}
	
	public String getLoginId() {
		return super.getUsername();
	}
	
	public String getEmployeeNumber() {
		return this.employeeNumber;
	}
	
	public static class LoginPrincipalService implements org.springframework.security.core.userdetails.UserDetailsService {
		public LoginPrincipal findByLoginId(String loginId) {
			return DB.AUTH_TABLE.get(loginId);
		}
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			LoginPrincipal found = this.findByLoginId(username);
			if(found == null) {
				throw new UsernameNotFoundException("username not found:" + username);
			}
			return found;
		}
	}
	
	private static class DB {
		public static final Map<String, LoginPrincipal> AUTH_TABLE = new HashMap<String, LoginPrincipal>();
		
		static {
			BCryptPasswordEncoder	passwordEncoder = new BCryptPasswordEncoder();
			LoginPrincipal[]		data = {
					new LoginPrincipal("U0001", "S0000001", passwordEncoder.encode("pass1"), new String[] { "USER" }),
					new LoginPrincipal("U0002", "S0000002", passwordEncoder.encode("pass2"), new String[] { "USER" }),
			};
			for(LoginPrincipal d : data) {
				AUTH_TABLE.put(d.getLoginId(), d);
			}
		}
	}
}
