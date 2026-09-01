package com.karine.ucanstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.karine.ucanstudy.model.Subject;

@Repository // este arquivo cuida do banco de dados
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    // Significa que estamos herdando todas as funções de banco de dados.
    // O primeiro parâmetro dentro do < > é a classe que queremos salvar (Subject).
    // O segundo parâmetro é o tipo do ID dessa classe (que definimos como Long na Entidade).
    
}