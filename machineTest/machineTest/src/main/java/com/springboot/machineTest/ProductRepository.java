package com.springboot.machineTest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	Product findByName(String name);
}
