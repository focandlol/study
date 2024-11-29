package focandlol.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = ["focandlol.domain.repository"])
@EntityScan(basePackages = ["focandlol.domain.domain"])
@ComponentScan(basePackages = ["focandlol"]) // 모든 모
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}