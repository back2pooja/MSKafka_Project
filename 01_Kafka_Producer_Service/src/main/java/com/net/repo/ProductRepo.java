package com.net.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.net.entity.Product;

@Repository
public interface ProductRepo extends  JpaRepository<Product,String> {

}
