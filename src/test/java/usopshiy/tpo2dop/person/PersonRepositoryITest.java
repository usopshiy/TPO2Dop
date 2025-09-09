package usopshiy.tpo2dop.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import usopshiy.tpo2dop.BaseIT;
import usopshiy.tpo2dop.Person;
import usopshiy.tpo2dop.PersonRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonRepositoryITest extends BaseIT {

    @Autowired
    private PersonRepository repository;

    @Test
    void crudOperations_work() {
        // Create
        Person saved = repository.save(Person.builder().age(30).name("Ivan").build());
        assertThat(saved.getId()).isNotNull();

        // Read
        Person loaded = repository.findById(saved.getId()).orElseThrow();
        assertThat(loaded.getName()).isEqualTo("Ivan");

        // Update
        loaded.setAge(31);
        repository.save(loaded);
        Person updated = repository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getAge()).isEqualTo(31);

        // Delete
        repository.deleteById(saved.getId());
        assertThat(repository.findById(saved.getId())).isEmpty();
    }

    @Test
    @Transactional
    void findAll_returnsInserted() {
        repository.save(Person.builder().age(20).name("Anna").build());
        repository.save(Person.builder().age(22).name("Petr").build());
        List<Person> all = repository.findAll();
        assertThat(all).hasSizeGreaterThanOrEqualTo(2);
    }
}
