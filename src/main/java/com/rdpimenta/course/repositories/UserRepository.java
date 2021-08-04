package com.rdpimenta.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdpimenta.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
