package com.net;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.net.entity.Product;
import com.net.repo.ProductRepo;
import com.net.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {
	
	@InjectMocks
	private ProductService productService;
	
	@Mock
	private ProductRepo productRepo;
	
	
	@Test
	public void injectMockTest() {
		assertThat(productService).isNotNull();
		assertThat(productRepo).isNotNull();
	}
	@Test
	public void findAllTest() {
		
		List<Product>products = new ArrayList<>();
		
		Product p1 = new Product();
		p1.setId("P1234");
		Product p2 =new Product();
		p2.setId("P456");
		products.add(p1);
		products.add(p2);
		when(productRepo.findAll()).thenReturn(products);
		
		List<Product> response = productService.findAll();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.size(),2);
		
	}

}
