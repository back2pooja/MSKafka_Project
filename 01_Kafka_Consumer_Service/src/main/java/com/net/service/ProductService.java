package com.net.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.net.entity.Product;
import com.net.repo.ProductRepo;
@Service
public class ProductService {
	
	private final ProductRepo repo;
	
	public ProductService(ProductRepo repo) {
		this.repo=repo;
	}
	
	public Product save(Product product) {
		return repo.save(product);
	}
	
	public List<Product>findAll(){
		return repo.findAll();
	}

}
