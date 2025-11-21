package com.example.taxibookingbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tags;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for the Taxi Booking Backend.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Taxi Booking Backend API",
                version = "0.1.0",
                description = "REST APIs for user operations, ride booking, driver assignment, and booking status checks.",
                contact = @Contact(name = "QuickRide", email = "support@example.com")
        ),
        servers = {
                @Server(description = "Local", url = "/")
        },
        tags = {
                @Tag(name = "Users", description = "User management APIs"),
                @Tag(name = "Drivers", description = "Driver management and availability"),
                @Tag(name = "Bookings", description = "Booking lifecycle and status"),
                @Tag(name = "System", description = "System info and docs")
        }
)
@SecurityScheme(name = "none", type = SecuritySchemeType.HTTP, scheme = "none", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}
