package usopshiy.tpo2dop.flyway;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import usopshiy.tpo2dop.BaseIT;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.assertj.core.api.Assertions.assertThat;

public class FlywayMigrationITest extends BaseIT {

    @Autowired
    private DataSource dataSource;

    @Test
    void migrate_and_clean_and_migrate_again() throws Exception {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .cleanDisabled(false)
                .load();

        flyway.clean();
        flyway.migrate();

        try (Connection conn = dataSource.getConnection()) {
            ResultSet rs = conn.getMetaData().getTables(null, null, "person", null);
            assertThat(rs.next()).isTrue();
        }

        flyway.clean();
        flyway.migrate();
    }
}
