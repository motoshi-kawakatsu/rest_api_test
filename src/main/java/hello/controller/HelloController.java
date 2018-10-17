package hello.controller;

import java.util.List;
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
}