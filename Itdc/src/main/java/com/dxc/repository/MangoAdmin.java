package com.dxc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dxc.pojos.Admin;
@Repository

	public interface MangoAdmin extends MongoRepository<Admin, Integer>
	{

		
	}


