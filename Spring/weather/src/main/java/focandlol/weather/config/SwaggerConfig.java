package focandlol.weather.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenApiCustomizer customOpenApiInfo() {
        return openApi -> openApi
                .info(new Info()
                        .title("날씨 일기 프로젝트") // API 제목
                        .description("날씨 일기를 crud 할 수 있는 백엔드 api") // API 설명
                        .version("1.0.1") // API 버전
                );
    }
}
