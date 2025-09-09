package usopshiy.tpo2dop.flyway;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import usopshiy.tpo2dop.BaseIT;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;

import static org.assertj.core.api.Assertions.assertThat;

public class FlywayRollbackITest extends BaseIT {

    @Autowired
    private DataSource dataSource;

    @Test
    void migrate_to_v1_then_forward_to_v2() throws Exception {
        Flyway flywayToV1 = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .target("1")
                .cleanDisabled(false)
                .load();
        flywayToV1.clean();
        flywayToV1.migrate();

        try (Connection conn = dataSource.getConnection()) {
            // Таблица должна быть
            ResultSet rs = conn.getMetaData().getTables(null, null, "person", null);
            assertThat(rs.next()).isTrue();

            // Индекса idx_person_name быть НЕ должно
            assertThat(hasIndex(conn, "person", "idx_person_name")).isFalse();
        }

        Flyway flywayToLatest = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .cleanDisabled(false)
                .load();
        flywayToLatest.migrate();

        try (Connection conn = dataSource.getConnection()) {
            assertThat(hasIndex(conn, "person", "idx_person_name")).isTrue();
        }
    }

    private boolean hasIndex(Connection conn, String tableName, String indexName) throws Exception {
        ResultSet idx = conn.getMetaData().getIndexInfo(null, null, tableName, false, false);
        while (idx.next()) {
            String current = idx.getString("INDEX_NAME");
            if (indexName.equalsIgnoreCase(current)) {
                return true;
            }
        }
        return false;
    }
}

