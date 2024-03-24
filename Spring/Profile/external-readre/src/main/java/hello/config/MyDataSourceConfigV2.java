package hello.config;

import hello.dataSource.MyDataSource;
import hello.dataSource.MyDataSourcePropertiesV1;
import hello.dataSource.MyDataSourcePropertiesV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
//@EnableConfigurationProperties(MyDataSourcePropertiesV1.class)
public class MyDataSourceConfigV2 {

    private final MyDataSourcePropertiesV2 properties;


    public MyDataSourceConfigV2(MyDataSourcePropertiesV2 properties) {
        this.properties = properties;
    }

    @Bean
    public MyDataSource myDataSource(){
        return new MyDataSource(properties.getUrl(), properties.getUsername(), properties.getPassword(), properties.getEtc().getMaxConnection(),properties.getEtc().getTimeout(), properties.getEtc().getOptions());
    }
}
