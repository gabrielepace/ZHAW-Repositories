package com.example.demo.bootstrap;


import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseBootstrap  implements InitializingBean {
    @Autowired
    CustomerRepository repository;
    private static Logger log = LoggerFactory.getLogger(DatabaseBootstrap.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        if (repository.findByFirstnameAndLastname("Max", "Mustermann") == null) {
            Customer customer = new Customer();
            customer.setFirstname("Max");
            customer.setLastname("Mustermann");
            repository.save(customer);
            log.info(customer.getFirstname() + " " + customer.getLastname() +
                    " created");
        }
        log.info("Bootstrap finished");
    }
}