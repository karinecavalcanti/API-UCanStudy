package com.karine.ucanstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karine.ucanstudy.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}
