package com.springboot.machineTest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	 private ProductRepository repository;
	

	public void saveProduct(Product product) {
		
		repository.save(product);
	}

	public List<Product> saveProducts(List<Product> products) {
		 return repository.saveAll(products);
	}
	
	public List<Product> getProducts() {
		return repository.findAll();
	}
	
	
	public Page < Product > getProductPagination(Integer page, Integer pageSize, String sort) {
	    Pageable pageable = null;
	    if (sort != null) {
	      
	      pageable = PageRequest.of(page, pageSize);
	    } else {
	      
	      pageable = PageRequest.of(page, pageSize);
	    }
	    return repository.findAll(pageable);
	  }
	
	
	public Product getProductById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public Product getProductByName(String name) {
	    return repository.findByName(name);
	    }

	public Product updateProduct(int id,Product product) {
		product.setId(id);
	    return repository.save(product);
	}

	public String deleteProduct(int id) {
		repository.deleteById(id);
        return "product removed !!"+ id;
	}

}
