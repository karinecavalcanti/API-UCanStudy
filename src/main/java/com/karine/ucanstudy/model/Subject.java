package com.karine.ucanstudy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//mostra pro Spring que essa classe representa uma tabela do bd

@Table(name = "tb_subjects")
//dá um nome específico para a tabela do banco

public class Subject {
    @Id
    //é a chave primária

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //cria o id de forma sequencial

    private Long id;

    private String name;

    public Subject(){
        /*o hibernate/jpa exige um construtor vazio 
        para conseguir criar objetos quando busca do banco*/

    }
    //lembro dessa parte
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}