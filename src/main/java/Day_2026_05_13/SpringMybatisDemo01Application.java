package Day_2026_05_13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringMybatisDemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisDemo01Application.class, args);
    }
}