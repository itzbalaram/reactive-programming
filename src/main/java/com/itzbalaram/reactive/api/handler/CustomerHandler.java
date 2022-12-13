package com.itzbalaram.reactive.api.handler;

import com.itzbalaram.reactive.api.dao.CustomerDao;
import com.itzbalaram.reactive.api.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomersList(ServerRequest serverRequest) {
        Flux<Customer> customerList = customerDao.getCustomersList();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> findCustomerById(ServerRequest serverRequest) {
        int customerId = Integer.valueOf(serverRequest.pathVariable("input"));
//        Mono<Customer> customerMono = customerDao.getCustomersList().filter(c -> c.getId() == customerId).take(1).single();
        Mono<Customer> customerMono = customerDao.getCustomersList().filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse, String.class);
    }

}
