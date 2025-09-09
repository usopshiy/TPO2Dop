package usopshiy.tpo2dop.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import usopshiy.tpo2dop.BaseIT;
import usopshiy.tpo2dop.Person;
import usopshiy.tpo2dop.PersonService;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonServiceITest extends BaseIT {

    @Autowired
    private PersonService service;

    @Test
    void service_save_and_update() {
        Person created = service.create(Person.builder().age(18).name("Olga").build());
        assertThat(created.getId()).isNotNull();

        Person afterUpdate = service.update(created.getId(), 19, "Olga Petrovna");
        assertThat(afterUpdate.getAge()).isEqualTo(19);
        assertThat(afterUpdate.getName()).isEqualTo("Olga Petrovna");
    }
}