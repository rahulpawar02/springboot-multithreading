package com.springboot.multithreading.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.multithreading.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
}
