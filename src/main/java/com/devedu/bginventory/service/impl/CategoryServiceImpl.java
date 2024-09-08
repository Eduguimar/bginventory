package com.devedu.bginventory.service.impl;

import com.devedu.bginventory.controller.dto.CategoryDTO;
import com.devedu.bginventory.domain.model.Category;
import com.devedu.bginventory.domain.repository.CategoryRepository;
import com.devedu.bginventory.service.CategoryService;
import com.devedu.bginventory.service.exception.BusinessException;
import com.devedu.bginventory.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDTO::new).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(NotFoundException::new);
        return new CategoryDTO(category);
    }

    @Override
    @Transactional
    public CategoryDTO create(CategoryDTO categoryDTO) {
        var category = new Category(categoryDTO);
        var savedCategory = categoryRepository.save(category);
        return new CategoryDTO(savedCategory);
    }

    @Override
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        var category = categoryRepository.findById(id).orElseThrow(NotFoundException::new);
        category.setName(categoryDTO.name());
        category.setDescription(categoryDTO.description());
        var savedCategory = categoryRepository.save(category);
        return new CategoryDTO(savedCategory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException();
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Integrity violation");
        }
    }
}
