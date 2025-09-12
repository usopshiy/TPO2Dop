package usopshiy.tpo2dop.testcontainers;

import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;

public class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {
    private static final String IMAGE = "postgres:16-alpine";
    private static PostgresTestContainer container;

    private PostgresTestContainer() {
        super(IMAGE);
    }

    public static PostgresTestContainer getInstance() {
        if (container == null) {
            container = new PostgresTestContainer()
                    .withDatabaseName("testdb")
                    .withUsername("tni  est")
                    .withPassword("test")
                    .withReuse(true);
        }
        return container;
    }
}
