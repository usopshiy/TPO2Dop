package usopshiy.tpo2dop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Transactional
public class PersonService {
    private final PersonRepository repository;


    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }


    public Person create(Person p) {
        return repository.save(p);
    }


    @Transactional(readOnly = true)
    public Person getById(Long id) {
        return repository.findById(id).orElseThrow();
    }


    @Transactional(readOnly = true)
    public List<Person> getAll() {
        return repository.findAll();
    }


    public Person update(Long id, Integer newAge, String newName) {
        Person p = repository.findById(id).orElseThrow();
        if (newAge != null) p.setAge(newAge);
        if (newName != null) p.setName(newName);
        return repository.save(p);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }
}