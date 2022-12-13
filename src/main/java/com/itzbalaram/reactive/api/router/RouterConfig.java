package com.itzbalaram.reactive.api.router;

import com.itzbalaram.reactive.api.dto.Customer;
import com.itzbalaram.reactive.api.handler.CustomerHandler;
import com.itzbalaram.reactive.api.handler.CustomerStreamHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    CustomerHandler customerHandler;

    @Autowired
    CustomerStreamHandler streamHandler;

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/router/customers",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.GET,
                            beanClass = CustomerHandler.class,
                            beanMethod = "loadCustomersList",
                            operation = @Operation(
                                    operationId = "loadCustomersList",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "Successful Operation",
                                                    content = @Content(schema = @Schema(
                                                            implementation = Customer.class
                                                    ))
                                            )
                                    }
                            )
                    ),
                    @RouterOperation(
                            path = "/router/customers/stream",
                            produces = {
                                    MediaType.APPLICATION_JSON_VALUE
                            },
                            method = RequestMethod.GET,
                            beanClass = CustomerStreamHandler.class,
                            beanMethod = "loadCustomersStream",
                            operation = @Operation(
                                    operationId = "loadCustomersStream",
                                    responses = {
                                            @ApiResponse(
                                                    responseCode = "200",
                                                    description = "Successful Operation",
                                                    content = @Content(schema = @Schema(
                                                            implementation = Customer.class
                                                    ))
                                            )
                                    }
                            )
                    )

            }
    )
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", customerHandler::loadCustomersList)
                .GET("/router/customer/{input}", customerHandler::findCustomerById)
                .POST("/router/customer/save", customerHandler::saveCustomer)
                .GET("/router/customers/stream", streamHandler::loadCustomersStream)
                .build();

    }
}
