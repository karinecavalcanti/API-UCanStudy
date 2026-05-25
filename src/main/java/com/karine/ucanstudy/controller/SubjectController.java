package com.karine.ucanstudy.controller;

// Importamos a nossa entidade e o nosso repositório para podermos usar aqui

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karine.ucanstudy.model.Subject;
import com.karine.ucanstudy.repository.SubjectRepository;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
// Essa anotação diz ao Spring que esta classe é um controlador de API REST. 
// Ela garante que as respostas dos métodos sejam convertidas automaticamente para JSON.

@RequestMapping("/subjects")
//Aqui definimos a rota padrão (o caminho da URL) para esse controlador.
// Significa que qualquer requisição que vá para "http://localhost:8080/subjects" será tratada aqui dentro.

public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;
    //subjectRepository: busca e salva dados pelo Spring

    @GetMapping
    // responder a requisições do tipo HTTP GET
    // Como deixamos os parênteses vazios, ele usa a rota padrão da classe: "/subjects".
    public List<Subject> listarTodos(){
        return subjectRepository.findAll();
        //findAll() busca todos os registros de um tabelas no bd
    
    }

    @PostMapping
    // responder a requisições do tipo HTTP POST
    public Subject criarSubject(@RequestBody Subject novoSubject){
        //RequestBody: serve como um tradutor pro JSON
        return subjectRepository.save(novoSubject);
        //save() grava no bd e retorna o objeto salvo
    }

    
}
