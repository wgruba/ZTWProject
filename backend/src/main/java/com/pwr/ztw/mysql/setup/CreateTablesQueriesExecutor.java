package com.pwr.ztw.mysql.setup;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Component
public class CreateTablesQueriesExecutor {
    private final JdbcTemplate jdbcTemplate;

    public CreateTablesQueriesExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void executeCreateTableQueries() {
        try {
            ClassPathResource resource = new ClassPathResource("mysqlsetup/create_tables.sql");
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String queries = new String(data, StandardCharsets.UTF_8);

            // Split queries by semicolon and execute each query
            String[] queryArray = queries.split(";");
            for (String query : queryArray) {
                if (!query.trim().isEmpty()) {
                    jdbcTemplate.execute(query);
                }
            }

            System.out.println("Create table queries executed successfully!");
        } catch (IOException | DataAccessException e) {
            System.err.println("Failed to execute create table queries: " + e.getMessage());
        }
    }
}
