package com.dxc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dxc.pojos.Customer;

@Repository
public interface MongoCustomer extends MongoRepository<Customer, Integer> {
	public List<Customer> findCustomerBycid(int i);

}