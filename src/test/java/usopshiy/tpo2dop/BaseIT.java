package usopshiy.tpo2dop;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import usopshiy.tpo2dop.testcontainers.PostgresTestContainer;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public abstract class BaseIT {
    protected static final PostgresTestContainer POSTGRES = PostgresTestContainer.getInstance();

    @BeforeAll
    static void init() { POSTGRES.start(); }

    @DynamicPropertySource
    static void registerProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
    }
}
