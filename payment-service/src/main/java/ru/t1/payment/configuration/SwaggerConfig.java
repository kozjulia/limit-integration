package ru.t1.payment.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author YKozlova
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Payment service",
                description = "Payment service", version = "1.0.0",
                contact = @Contact(
                        name = "Kozlova Julia"
                )
        ))
public class SwaggerConfig {

}
