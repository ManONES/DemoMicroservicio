package com.manycode.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manycode.app.entity.Category;
import com.manycode.app.entity.Product;
import com.manycode.app.service.ProductService;

@RestController
@RequestMapping (value="/products")
public class ProductController {

	
	@Autowired
	private ProductService productService; 
	
	@GetMapping
	public ResponseEntity<List<Product>> ListProduct(@RequestParam(name="categoryIs",required = false) Long categoryId){
		List<Product> products = new ArrayList<>();
		if (null == categoryId) {
			products = productService.listAllProduct();
			if (products.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			products = productService.findByCategory(Category.builder().id(categoryId).build());
			if (products.isEmpty()) {
				return ResponseEntity.notFound().build();
			}			
		}
			
		return ResponseEntity.ok(products);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		Product product = productService.getProduct(id);
		if (null==product) {
			return ResponseEntity.notFound().build();
		}		

		return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product productCreate = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
	}
	
}
