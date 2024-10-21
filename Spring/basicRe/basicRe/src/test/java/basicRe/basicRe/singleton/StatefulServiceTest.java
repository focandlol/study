package basicRe.basicRe.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        statefulService1.order("kkm1",10000);
        statefulService2.order("kkm2",20000);

        int price1 = statefulService1.getPrice();
        int price2 = statefulService2.getPrice();

        System.out.println("price1 = " + price1);
        System.out.println("price2 = " + price2);

        Assertions.assertThat(price1).isEqualTo(price2);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}