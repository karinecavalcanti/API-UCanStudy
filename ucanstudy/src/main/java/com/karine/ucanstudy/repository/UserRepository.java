package com.karine.ucanstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karine.ucanstudy.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
