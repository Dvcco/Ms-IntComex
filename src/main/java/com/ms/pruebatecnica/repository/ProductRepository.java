package com.ms.pruebatecnica.repository;

import com.ms.pruebatecnica.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

    Optional<ProductModel> findByProductName (String productName);
    @Query(value = "SELECT c.picture FROM products p JOIN categories c ON p.categoryId = c.categoryId where p.productId = :productId")
    String findPictureByProductId(@Param("productId") Integer productId);

}
