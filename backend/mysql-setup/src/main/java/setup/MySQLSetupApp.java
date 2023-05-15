package setup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MySQLSetupApp {
    public static void main(String[] args){
        SpringApplication.run(MySQLSetupApp.class, args);
    }
}
