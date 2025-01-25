package com.springboot.machineTest;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class ProductController {

	@Autowired
    private ProductService productService;
	
	@Autowired
    private CategoryService categoryService;
	
	@PostMapping("/products/{categoryId}")
	public ResponseEntity < ? > save(@PathVariable("categoryId") Integer id, @RequestBody Product product){
	    Map < String, Object > respProduct  = new LinkedHashMap < String, Object > ();
	    Category category = categoryService.findById(id);
	    product.setCategory(category);
	    productService.saveProduct(product);
	    respProduct.put("status", category.getId());
	    respProduct.put("message", "Record is Saved Successfully!");
	    return new ResponseEntity < > (respProduct, HttpStatus.CREATED);
	  }
	
	@GetMapping("/products/list")
	public ResponseEntity < ? > getProduct() {
	    Map < String, Object > respProduct = new LinkedHashMap < String, Object > ();
	    List < Product > productList = productService.getProducts();
	    if (!productList.isEmpty()) {
	      respProduct.put("status", 1);
	      respProduct.put("data", productList);
	      return new ResponseEntity < > (respProduct, HttpStatus.OK);
	    } else {
	      respProduct.clear();
	      respProduct.put("status", 0);
	      respProduct.put("message", "Data is not found");
	      return new ResponseEntity < > (respProduct, HttpStatus.NOT_FOUND);
	    }
	}
	
		
	@GetMapping("/products")
	public List < Product > getProduct(@RequestParam(value="page",defaultValue="0",required=false)Integer page,
   		@RequestParam(value="pageSize",defaultValue="2",required=false)Integer pageSize){
	    Page < Product > data = productService.getProductPagination(page, pageSize, null);
	    return data.getContent();
	} 
	
	
	@GetMapping("/products/{id}")
    public Product findProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }
	
		
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable int id,@RequestBody Product product) {
	        return productService.updateProduct(id, product);
	        
	}
	 

	 @DeleteMapping("/products/{id}")
	 public String deleteProduct(@PathVariable int id) {
	        return productService.deleteProduct(id);
	 }

}
