package com.app.api.swaggertest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/swagger-test")
@RestController
public class SwaggerParamTestController {

    @Parameters({
            @Parameter(name = "query", description = "query param", in = ParameterIn.QUERY, required = false),
            @Parameter(name = "variable", description = "path variable", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("/{variable}")
    public String swaggerTest(String query, @PathVariable String variable) {
        log.info("query : {}, path variable : {}", query, variable);
        return "swagger test";
    }

}
