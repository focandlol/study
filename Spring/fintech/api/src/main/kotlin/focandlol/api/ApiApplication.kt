package focandlol.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EntityScan(basePackages = ["focandlol.domain"])
@ComponentScan(basePackages = ["focandlol"]) // 모든 모
@EnableCaching
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}