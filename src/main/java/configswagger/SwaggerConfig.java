package configswagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Shopping cart API")
                                .description("Api que simula um serviço de shopping cart, " +
                                        "contendo produtos")
                                .termsOfService("https://swagger.io/terms/")
                                .version("1.0.0")
                                .license(new License().name("Apache 2.0"))
                                .contact(new Contact()
                                        .email("brunobilheri@hotmail.com")
                                        .name("Bruno Monteiro"))
                );
    }
}
