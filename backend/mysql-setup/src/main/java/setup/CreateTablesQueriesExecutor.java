package setup;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CreateTablesQueriesExecutor {
    private final JdbcTemplate jdbcTemplate;

    public CreateTablesQueriesExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void executeCreateTableQueries() {
        try {
            ClassPathResource resource = new ClassPathResource("mysql_setup.sql");
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String queries = new String(data, StandardCharsets.UTF_8);

            // Split queries by semicolon and execute each query
            String[] queryArray = queries.split(";");
            for (String query : queryArray) {
                if (!query.trim().isEmpty()) {
                    try {
                        jdbcTemplate.execute(query);
                        //System.out.println(query);
                    } catch (DataAccessException e) {
                        System.err.println("Falied to execute query: "  + e.getMessage());
                    }

                }
            }
        } catch (IOException e) {
            System.err.println("Failed to execute create table queries: " + e.getMessage());
        }
    }
}
