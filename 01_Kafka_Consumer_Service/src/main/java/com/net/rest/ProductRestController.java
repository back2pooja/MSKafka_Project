package com.net.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.net.entity.Product;
import com.net.service.ProductService;
@RestController
@RequestMapping("/product")
public class ProductRestController {
	

	//private static final Logger log= LoggerFactory.getLogger(ProductRestController.class);
	
	private ProductService service;
	
	@Autowired
	public void setProductService(ProductService service) {
		this.service=service;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?>save(@RequestBody Product product){
		Product response =service.save(product);
		return new ResponseEntity<Product>(response, HttpStatus.OK);
	}
	@GetMapping("/list")
	public Iterable<Product>list(){
		Iterable<Product>products = service.findAll();
		return products;
	}
	

}
