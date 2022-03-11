package br.com.deyson.vuttr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class SwaggerConfig {

    public Properties properties;


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(properties.getInfo().getApp().getName())
                        .version(properties.getInfo().getApp().getVersion())
                        .description(properties.getInfo().getApp().getDescription()));
    }

}
