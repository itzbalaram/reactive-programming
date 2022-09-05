package com.itzbalaram.reactive.api.service;

import com.itzbalaram.reactive.api.dao.CustomerDao;
import com.itzbalaram.reactive.api.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total Execution Time : "+(end-start));
        return customers;
    }
}
