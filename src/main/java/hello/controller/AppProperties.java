package hello.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="spring.datasource")
public class AppProperties {

	private	String	username;
    private	String	password;
    private	String	url;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
    	return password;
    }
    
    public String getUrl() {
    	return url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
    	this.password = password;
    }
    
    public void setUrl(String url) {
    	this.url = url;
    }
}
