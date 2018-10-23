package hello.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * HelloController
 *
 * @author shiro
 *
 */
@Controller
public class HelloController {

	@Autowired
	PersonService service;
    
    @Autowired
    AppProperties	prop;
	
    /**
     * Return the character string as it is to the request destination
     *
     * @return The character string is displayed as it is.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String topPage() {
        return "The character string is displayed as it is !";
    }

    /**
     * HelloWorld Page
     *
     * @return HelloWorld Page
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello/hello";
    }

    /**
     * Person Page
     *
     * @return Person List
     */
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> person() {
        return service.findAll();
    }
    
    /**
     * Person Page Ver.2
     *
     * @return Person List
     */
    @RequestMapping(value = "/person2", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> person2() throws SQLException {
        List<Person>	personList = new ArrayList<Person>();
        String	username;
        String	password;
        String	url;

        Map<String, String>	databaseUrl = System.getenv();
        if(databaseUrl.containsKey("DATABASE_URL")) {
            URI		dbUri = URI.create(databaseUrl.get("DATABASE_URL"));

            url = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
        } else {
        	
        	url = prop.getUrl();
        	username = prop.getUsername();
        	password = prop.getPassword();
        }

        Connection	conn = DriverManager.getConnection(url, username, password);
        Statement	stmt = conn.createStatement();
        
        ResultSet	rs = stmt.executeQuery("SELECT * FROM person");
        
        while(rs.next()) {
        	Person	p = new Person();
        	p.setId(rs.getInt("Id"));
        	p.setName(rs.getString("name"));
        	p.setAge(rs.getInt("age"));
        	personList.add(p);
        }
        
        return personList;
    }
    
}