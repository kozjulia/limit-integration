package ru.t1.limit.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author YKozlova
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Limit service",
                description = "Limit service", version = "1.0.0",
                contact = @Contact(
                        name = "Kozlova Julia"
                )
        ))
public class SwaggerConfig {

}
