package usopshiy.tpo2dop;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Integer age;


    @Column(nullable = false)
    private String name;
}
