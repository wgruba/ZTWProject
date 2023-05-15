package com.pwr.ztw.mysql.setup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MySQLSetupApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MySQLSetupApp.class, args);

        // Get the ApplicationRunner bean and call its run method
        MySQLSetupRunner runner = context.getBean(MySQLSetupRunner.class);
        try {
            runner.run();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
