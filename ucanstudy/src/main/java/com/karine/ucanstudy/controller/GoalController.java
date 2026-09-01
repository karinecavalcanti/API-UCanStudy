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

import com.karine.ucanstudy.model.Goal;
import com.karine.ucanstudy.repository.GoalRepository;

@RestController
@RequestMapping("/goals")
@CrossOrigin(origins = "http://localhost:5173")
public class GoalController {
    @Autowired
    private GoalRepository goalRepository;
    //subjectRepository: busca e salva dados pelo Spring

    @GetMapping
    // responder a requisições do tipo HTTP GET
    // Como deixamos os parênteses vazios, ele usa a rota padrão da classe: "/subjects".
    public List<Goal> listarTodos(){
        return goalRepository.findAll();
        //findAll() busca todos os registros de um tabelas no bd
    
    }
    @GetMapping("/{id}")
    public Goal buscarPorId(@PathVariable Long id) {
        return goalRepository.findById(id).orElse(null);
    }

    @PostMapping
    // responder a requisições do tipo HTTP POST
    public Goal criarGoal(@RequestBody Goal novoGoal){
        //RequestBody: serve como um tradutor pro JSON
        return goalRepository.save(novoGoal);
        //save() grava no bd e retorna o objeto salvo
    }

    @PutMapping("/{id}")
    //O "{id}" na rota indica que vamos receber o número do id na URL (ex: /users/1)
    //@PathVariable avisa o Spring para pegar esse número da URL e colocar na variável 'id'
    public Goal atualizarGoal(@PathVariable Long id, @RequestBody Goal goalAtualizado){
        //1. Procuramos se o ID existe no banco de dados
        //1. Procuramos se o ID existe no banco de dados
        return goalRepository.findById(id).map(goalExistente ->{
            
            //2. Se existe, verificamos campo por campo antes de atualizar!
            if (goalAtualizado.getTitle() != null) {
                goalExistente.setTitle(goalAtualizado.getTitle());
            }
            
            if (goalAtualizado.getTarget_hours() != null) {
                goalExistente.setTarget_hours(goalAtualizado.getTarget_hours());
            }
            
            if (goalAtualizado.getProgress() != null) {
                goalExistente.setProgress(goalAtualizado.getProgress());
            }
            
            if (goalAtualizado.getDeadline() != null) {
                goalExistente.setDeadline(goalAtualizado.getDeadline());
            }
            
            if (goalAtualizado.getCompleted() != null) {
                goalExistente.setCompleted(goalAtualizado.getCompleted());
            }

            //3. Como o goalExistente já possui o ID original, o save() vai fazer um update
            return goalRepository.save(goalExistente);
        }).orElse(null);// se não achar o ID, retorna vazio
    }

    @DeleteMapping("/{id}")
    public void deletarGoal(@PathVariable Long id){
        // O repositório vai direto no banco e deleta a linha que tem esse ID
        goalRepository.deleteById(id);
    }


}
