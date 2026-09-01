package com.karine.ucanstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karine.ucanstudy.model.StudySession;

public interface StudySessionRepository extends JpaRepository<StudySession, Long> {

}
