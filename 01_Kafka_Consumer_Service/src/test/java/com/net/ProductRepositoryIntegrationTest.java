package com.net;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.net.entity.Product;
import com.net.repo.ProductRepo;
import com.net.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryIntegrationTest {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductService service;
	
	@Test
	public void injectMockTest() {
		Assertions.assertNotNull(service);
		Assertions.assertNotNull(productRepo);
	}
	@Test
	public void finalTest() {
		List<Product> products = new ArrayList<>();
		Product p1 = new Product();
		p1.setId("P123");
		Product p2 = new Product();
		p2.setId("P124");
		products.add(p1);
		products.add(p2);
		when(productRepo.findAll()).thenReturn(products);
		
		List<Product>response = service.findAll();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.size(), 2);
	}
	
	
}
