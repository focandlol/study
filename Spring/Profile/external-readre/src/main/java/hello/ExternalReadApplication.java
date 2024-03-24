package hello;

import hello.config.MyDataSourceConfigV1;
import hello.config.MyDataSourceEnvConfig;
import hello.config.MyDataSourceValueConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "hello.dataSource")
@ConfigurationPropertiesScan
//@Import(MyDataSourceEnvConfig.class)
//@Import(MyDataSourceValueConfig.class)
@Import(MyDataSourceConfigV1.class)
public class ExternalReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalReadApplication.class, args);
    }

}
