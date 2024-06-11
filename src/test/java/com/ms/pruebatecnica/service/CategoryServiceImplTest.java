package com.ms.pruebatecnica.service;

import com.ms.pruebatecnica.dto.CategoryDto;
import com.ms.pruebatecnica.exception.ExceptionPruebaTecnica;
import com.ms.pruebatecnica.model.CategoryModel;
import com.ms.pruebatecnica.repository.CategoryRepository;
import com.ms.pruebatecnica.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void testCreateCategory_Success() {
        CategoryDto categoryDto = new CategoryDto(1, "category", "Description", "Picture");
        when(categoryRepository.findByCategoryName("category")).thenReturn(Optional.empty());
        when(categoryRepository.save(any())).thenReturn(new CategoryModel());
        CategoryDto result = categoryService.createCategory(categoryDto).block();
        assertNotNull(result);
        verify(categoryRepository, times(1)).findByCategoryName("category");
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    public void testCreateCategory_CategoryExists() {
        CategoryDto categoryDto = new CategoryDto(1, "category", "Description", "Picture");
        when(categoryRepository.findByCategoryName("category")).thenReturn(Optional.of(new CategoryModel()));
        assertThrows(ExceptionPruebaTecnica.class, () -> categoryService.createCategory(categoryDto).block());
    }
}