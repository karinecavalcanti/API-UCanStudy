package com.karine.ucanstudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karine.ucanstudy.model.StudySession;
import com.karine.ucanstudy.repository.StudySessionRepository;
import com.karine.ucanstudy.service.StudySessionService;

@RestController

@RequestMapping("/studysession")
@CrossOrigin(origins = "http://localhost:5173")
public class StudySessionController {
    @Autowired
    private StudySessionRepository studysessionRepository;

    @Autowired
    private StudySessionService studySessionService;

    @GetMapping
    // responder a requisições do tipo HTTP GET
    // Como deixamos os parênteses vazios, ele usa a rota padrão da classe: "/subjects".
    public List<StudySession> listarTodos(){
        return studysessionRepository.findAll();
        //findAll() busca todos os registros de um tabelas no bd
    
    }
    @GetMapping("/{id}")
    public StudySession buscarPorId(@PathVariable Long id) {
        return studysessionRepository.findById(id).orElse(null);
    }

    @PostMapping
    // responder a requisições do tipo HTTP POST
    public StudySession criarStudySession(@RequestBody StudySession novoStudySession){
        //RequestBody: serve como um tradutor pro JSON
        return studySessionService.criarSessaoComXp(novoStudySession);
        //save() grava no bd e retorna o objeto salvo
    }
    
    @PutMapping("/{id}")
    public StudySession atualizarStudySession(@PathVariable Long id, @RequestBody StudySession sessaoAtualizada) {
        
        return studysessionRepository.findById(id).map(sessaoExistente -> {
            
            if (sessaoAtualizada.getDate() != null) {
                sessaoExistente.setDate(sessaoAtualizada.getDate());
            }
            
            if (sessaoAtualizada.getDuration() != null) {
                sessaoExistente.setDuration(sessaoAtualizada.getDuration());
            }
            
            if (sessaoAtualizada.getNotes() != null) {
                sessaoExistente.setNotes(sessaoAtualizada.getNotes());
            }
            
            // Também podemos bloquear a alteração do xp_earned, deixando de fora o "if" dele!
            
            return studysessionRepository.save(sessaoExistente);
            
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteStudySession(@PathVariable Long id){
        studysessionRepository.deleteById(id);
    }

}
