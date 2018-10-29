package hello.app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.app.resource.PersonResource;
import hello.domain.DataSourceConfigProperties;
import hello.domain.model.Person;
import hello.domain.service.PersonService;

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
    DataSourceConfigProperties	prop;
	
	private static final int STATUS_OK = 0;
	private static final int STATUS_NG = 1;

	private class ResultInfo{
		public String memberId;
		public String memberName;
		public int status;
		public String error;
	}
	
	@RequestMapping("/MyAccount")
	public String MyAccount() {

		return "MyAccount/MyAccount";
	}
	
	@RequestMapping("/MyAccount/GetAccountInfo")
	@ResponseBody
	public String GetAccountInfo(String MemberId) {
		ResultInfo ret = new  ResultInfo();
		ret.memberId = "test";
		ret.memberName = "Motoshi Kawakatsu";
		ret.status = STATUS_OK;

		String retVal = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			retVal = mapper.writeValueAsString(ret);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			ret.status = STATUS_NG;
			ret.error = e.getMessage();
		}
		return retVal;
	}

	/**
     * Return the character string as it is to the request destination
     *
     * @return Hello, <employeeNumber>
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String topPage() {
        return "Hello, TopPage!";
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
     * Person Page
     *
     * @return Person
     */
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PersonResource person(@PathVariable int id) {
         Person	one = service.find(id);
        
        PersonResource	resource = new PersonResource();
        resource.setId(one.getId());
        resource.setName(one.getName());
        resource.setAge(one.getAge());
        
        return resource;
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

        String	databaseUrl = System.getenv("DATABASE_URL");
        if(databaseUrl != null) {
            URI		dbUri = URI.create(databaseUrl);

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