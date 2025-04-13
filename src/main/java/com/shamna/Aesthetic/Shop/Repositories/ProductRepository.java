package com.shamna.Aesthetic.Shop.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shamna.Aesthetic.Shop.Entities.ProductDetails;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails,Integer>{

    @Query("SELECT p FROM ProductDetails p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<ProductDetails> searchProducts(String search);
}
