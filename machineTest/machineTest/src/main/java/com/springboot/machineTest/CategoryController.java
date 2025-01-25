package com.springboot.machineTest;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
		@Autowired
		CategoryService categoryService;
  
	  @PostMapping("/categories")
	  public ResponseEntity < ? > save(@RequestBody Category category){
	    Map < String, Object > respCategory = new LinkedHashMap < String, Object > ();
	
	    categoryService.save(category);
	    respCategory.put("status", 1);
	    respCategory.put("message", "Record is Saved Successfully!");
	    return new ResponseEntity < > (respCategory, HttpStatus.CREATED);
	  }
	  

	  @GetMapping("/category/list")
	  public ResponseEntity < ? > getCategory() {
	    Map < String, Object > respCategory = new LinkedHashMap < String, Object > ();
	    List < Category> categoryList = categoryService.findCategoryList();
	    if (!categoryList.isEmpty())
	    {
	      respCategory.put("status", 1);
	      respCategory.put("data", categoryList);
	      return new ResponseEntity < > (respCategory, HttpStatus.OK);
	    }
	    else 
	    {
	      respCategory.clear();
	      respCategory.put("status", 0);
	      respCategory.put("message", "Data is not found");
	      return new ResponseEntity < > (respCategory, HttpStatus.NOT_FOUND);
	    }
	  }
	  
		
	  @PutMapping("/categories")
	  public Category updateCategory(@RequestBody Category category) 
	  {
		  return categoryService.updateCategory(category);
	  }
	  

	  @GetMapping("/categories/{id}")
	  public Category findProductById(@PathVariable int id) {
	      return categoryService.getCategoryById(id);
	  }
	  

	  @DeleteMapping("/categories/{id}")
	  public String deleteCategory(@PathVariable int id) {
	      return categoryService.deleteCategory(id);
	  }
	  

	  @GetMapping("/categories")
	  public List < Category > getCategory(@RequestParam(value="page",defaultValue="0",required=false)Integer page,
			@RequestParam(value="pageSize",defaultValue="2",required=false)Integer pageSize){
	    Page < Category > data = categoryService.getCategoryPagination(page, pageSize, null);
	    return data.getContent();
	  } 
  
}
