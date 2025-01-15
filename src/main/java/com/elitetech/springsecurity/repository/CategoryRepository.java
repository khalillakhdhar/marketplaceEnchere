package com.elitetech.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitetech.springsecurity.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
