package com.ms.pruebatecnica.repository;

import com.ms.pruebatecnica.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    Optional<CategoryModel> findByCategoryName (String categoryName);
}
