package com.elitetech.springsecurity.mapper;

import org.modelmapper.ModelMapper;

import com.elitetech.springsecurity.dto.CategoryDTO;
import com.elitetech.springsecurity.entity.Category;

public class CategoryMapper {
	 private static final ModelMapper modelMapper = new ModelMapper();

	    public static CategoryDTO convertToDto(Category category) {
	        return modelMapper.map(category, CategoryDTO.class);
	    }

	    public static Category convertToEntity(CategoryDTO categoryDTO) {
	        return modelMapper.map(categoryDTO, Category.class);
	    }
}
