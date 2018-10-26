package hello.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.domain.model.Person;
import hello.domain.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
	}
	
	public Person find(int id) {
		return repository.getOne(id);
	}
}
