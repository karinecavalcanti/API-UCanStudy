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

import com.karine.ucanstudy.model.User;
import com.karine.ucanstudy.repository.UserRepository;

@RestController

@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> listarTodos(){
        return userRepository.findAll();
        //findAll() busca todos os registros de um tabelas no bd
    }
    @GetMapping("/{id}")
    public User buscarPorId(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public User criarUser(@RequestBody User novoUser){
        //RequestBody: serve como um tradutor pro JSON
        return userRepository.save(novoUser);
        //save() grava no bd e retorna o objeto salvo
    }

    @PutMapping("/{id}")
    public User atualizarUser(@PathVariable Long id, @RequestBody User userAtualizado) {
        
        return userRepository.findById(id).map(userExistente -> {
            
            if (userAtualizado.getName() != null) {
                userExistente.setName(userAtualizado.getName());
            }
            
            if (userAtualizado.getEmail() != null) {
                userExistente.setEmail(userAtualizado.getEmail());
            }
            
            if (userAtualizado.getPassword() != null) {
                // No futuro, aqui entrará a lógica de criptografar a senha nova antes de salvar!
                userExistente.setPassword(userAtualizado.getPassword());
            }
            
            // Ignoramos xp e level de propósito para o usuário não "hackear" o próprio nível!
            
            return userRepository.save(userExistente);
            
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarUser(@PathVariable Long id){
        // O repositório vai direto no banco e deleta a linha que tem esse ID
        userRepository.deleteById(id);
    }


}
