package xy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "xy")
public class XyApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyApplication.class, args);
    }

}
