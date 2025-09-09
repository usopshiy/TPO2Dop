package usopshiy.tpo2dop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonService service;


    public PersonController(PersonService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person p) {
        Person saved = service.create(p);
        return ResponseEntity.created(URI.create("/api/people/" + saved.getId())).body(saved);
    }


    @GetMapping("/{id}")
    public Person get(@PathVariable Long id) { return service.getById(id); }


    @GetMapping
    public List<Person> all() { return service.getAll(); }


    @PutMapping("/{id}")
    public Person update(@PathVariable Long id, @RequestBody Person p) {
        return service.update(id, p.getAge(), p.getName());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}