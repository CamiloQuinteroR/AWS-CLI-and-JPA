package com.example.accessingdatajpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    <S extends Customer> S save(S entity);

    Iterable<Customer> findAll();


    List<Customer> findByLastName(String lastName);

    default Customer findById(long id) {
        Optional<Customer> customer = findById(Long.valueOf(id));
        return customer.orElse(null);
    }
}
