package usopshiy.tpo2dop.person;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import usopshiy.tpo2dop.BaseIT;
import usopshiy.tpo2dop.Person;
import usopshiy.tpo2dop.PersonService;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class PersonServiceITest extends BaseIT {

    @Autowired
    private PersonService service;

    @Test
    void serviceSaveAndUpdate() {
        // Create
        Person created = service.create(Person.builder().age(18).name("Olga").build());
        assertThat(created.getId()).isNotNull();

        // Read
        Person read = service.getById(created.getId());
        assertThat(read.getName()).isEqualTo("Olga");

        // Update
        Person afterUpdate = service.update(read.getId(), 19, "Olga Petrovna");
        assertThat(afterUpdate.getAge()).isEqualTo(19);
        assertThat(afterUpdate.getName()).isEqualTo("Olga Petrovna");

        //Delete
        service.delete(afterUpdate.getId());
        assertThrows(NoSuchElementException.class, () -> service.getById(afterUpdate.getId()));
    }
}