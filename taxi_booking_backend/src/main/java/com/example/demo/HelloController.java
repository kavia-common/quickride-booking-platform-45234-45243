package com.example.taxibookingbackend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Basic system endpoints for health and docs redirect.
 */
@RestController
@Tag(name = "System")
public class HelloController {

    // PUBLIC_INTERFACE
    @GetMapping("/")
    @Operation(summary = "Welcome endpoint", description = "Returns a welcome message")
    public String hello() {
        /** Simple welcome message. */
        return "Hello, Spring Boot! Welcome to taxibookingbackend";
    }

    // PUBLIC_INTERFACE
    @GetMapping("/docs")
    @Operation(summary = "API Documentation", description = "Redirects to Swagger UI preserving original scheme/host/port")
    public RedirectView docs(HttpServletRequest request) {
        /** Redirect to Swagger UI with correct base URL. */
        String target = UriComponentsBuilder
                .fromHttpRequest(new ServletServerHttpRequest(request))
                .replacePath("/swagger-ui.html")
                .replaceQuery(null)
                .build()
                .toUriString();

        RedirectView rv = new RedirectView(target);
        rv.setHttp10Compatible(false);
        return rv;
    }

    // PUBLIC_INTERFACE
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status")
    public String health() {
        /** Health OK. */
        return "OK";
    }

    // PUBLIC_INTERFACE
    @GetMapping("/api/info")
    @Operation(summary = "Application info", description = "Returns application information")
    public String info() {
        /** Application info. */
        return "Spring Boot Application: taxibookingbackend";
    }
}