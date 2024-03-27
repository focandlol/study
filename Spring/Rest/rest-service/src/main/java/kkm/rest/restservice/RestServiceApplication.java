package kkm.rest.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) {

		ApplicationContext ac = SpringApplication.run(RestServiceApplication.class, args);

//		String[] allBeanNames = ac.getBeanDefinitionNames();
//		for (String allBeanName : allBeanNames) {
//			System.out.println("allBeanName = " + allBeanName);
//		}
	}

	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		System.out.println("localeResolver = " + localeResolver);
		return localeResolver;
	}

}
