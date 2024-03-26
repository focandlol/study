package kkm.rest.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) {

		ApplicationContext ac = SpringApplication.run(RestServiceApplication.class, args);

		String[] allBeanNames = ac.getBeanDefinitionNames();
		for (String allBeanName : allBeanNames) {
			System.out.println("allBeanName = " + allBeanName);
		}
	}

}
