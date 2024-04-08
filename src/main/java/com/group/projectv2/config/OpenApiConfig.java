package com.group.projectv2.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Quizz App API",
                description = "Quizz App Management API Documentation",
                summary = "This API is used to manage Quizz App data",
                license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                version = "1.0.0"
        )
)
public class OpenApiConfig {
}
