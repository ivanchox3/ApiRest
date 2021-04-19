package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductsDAO;
import com.example.demo.entities.Product;



@RestController
@RequestMapping("products") 
public class ProductRest {
	
	@Autowired //Anotacion para la interfaz que permite  manejo de las operaciones CRUD del entity con JPA
	private ProductsDAO productsDAO;
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){
		List<Product> products = productsDAO.findAll();
		return ResponseEntity.ok(products);
	}
	
	@RequestMapping(value= "{productID}") // products/{productID} --> /products/1
	public ResponseEntity<Product> getProductById(@PathVariable("productID") Long productId){
		Optional<Product> optionalProduct = productsDAO.findById(productId);
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
					
		}
		else 
			return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product newProduct=productsDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	@DeleteMapping(value= "{productID}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productID") Long productId){
		productsDAO.deleteById(productId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Optional<Product> optionalProduct = productsDAO.findById(product.getId());
		if(optionalProduct.isPresent()) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setNombre(product.getNombre());
			productsDAO.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		}
		else 
			return ResponseEntity.notFound().build();
		
	}
	
	
}
