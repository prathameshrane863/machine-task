package com.springboot.machineTest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	 @Autowired
	 private CategoryRepository repository;
	
	 
	  public void save(Category category) {
		 repository.save(category);
	  }
	
	  public List <Category> findCategoryList() {
		    return repository.findAll();
	  }
	  
	  public Category findById(Integer id) {
		    return repository.findById(id).get();
	}

	public Category updateCategory(Category category) {
		Category existingCategory = repository.findById(category.getId()).orElse(null);
        existingCategory.setName(category.getName());
        return repository.save(existingCategory);
	}

	public Category getCategoryById(int id) {
		return repository.findById(id).orElse(null);
	}

	public String deleteCategory(int id) {
		repository.deleteById(id);
        return "Category removed !! " + id;
	}

	public Page<Category> getCategoryPagination(Integer page, Integer pageSize, String sort) {
		
		  Pageable pageable = null;
		    if (sort != null) {
		      pageable = PageRequest.of(page, pageSize);
		    } else {
		      pageable = PageRequest.of(page, pageSize);
		    }
		    return repository.findAll(pageable);
	}
	
}
