package design.car.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {
    public static final String AUTHORIZATION_TYPE = "Bearer Authentication";


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(
                        new SecurityRequirement().
                                addList(AUTHORIZATION_TYPE)
                )
                .components(
                        new Components().addSecuritySchemes(AUTHORIZATION_TYPE, createAPIKeyScheme())
                )
                .info(
                        new Info().title("Cars Dashboard")
                                .description("Spring Boot 3 Reference Design")
                                .version("1.0").contact(
                                        new Contact().name("Al Kaliutau")
                                                .email("aliaksei.kaliutau@gmail.com").url("avkaliutau.com")
                                )
                                .license(
                                        new License().name("Apache License 2.0")
                                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                                )
                );
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}