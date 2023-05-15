package com.pwr.ztw.mysql.setup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MySQLSetupRunner implements CommandLineRunner {

    private final CreateTablesQueriesExecutor createTablesQueriesExecutor;

    public MySQLSetupRunner(CreateTablesQueriesExecutor createTablesQueriesExecutor) {
        this.createTablesQueriesExecutor = createTablesQueriesExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        createTablesQueriesExecutor.executeCreateTableQueries();
        System.exit(0);
    }
}
