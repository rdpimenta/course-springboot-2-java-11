package com.rdpimenta.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdpimenta.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
