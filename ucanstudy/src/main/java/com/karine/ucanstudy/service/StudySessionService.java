package com.karine.ucanstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karine.ucanstudy.model.StudySession;
import com.karine.ucanstudy.model.User;
import com.karine.ucanstudy.repository.StudySessionRepository;
import com.karine.ucanstudy.repository.UserRepository;

@Service //avisa ao Spring que essa classe é a inteligência do negócio
public class StudySessionService {
    @Autowired
    private StudySessionRepository studySessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoalService goalService;

    public StudySession criarSessaoComXp(StudySession novaSessao){
        // 1. REGRA DE NEGÓCIO: Cálculo de XP
        // Cada minuto estudado (duration) vale 5 de XP
        int xpGanho = novaSessao.getDuration() * 5;
        novaSessao.setXp_earned(xpGanho); // O usuário não manda o XP no Postman, nós calculamos!

        // 2. Buscar o usuário que estudou para atualizar o XP total dele
        // O '.get()' no final extrai o User de dentro do banco
        User usuario = userRepository.findById(novaSessao.getUser().getId()).get();

        // 3. Somar o XP novo com o XP que o usuário já tinha
        int xpTotalAtualizado = usuario.getXp() + xpGanho;
        usuario.setXp(xpTotalAtualizado);

        // 4. REGRA DE NEGÓCIO: Level Up!
        // A cada 1000 de XP, ele sobe um nível.
        int nivelAtualizado = (xpTotalAtualizado / 1000) + 1; // Começa no nível 1
        usuario.setLevel(nivelAtualizado);

        // 5. Salvar as alterações no banco de dados
        userRepository.save(usuario); // Atualiza o perfil do usuário com o XP e Level novos
        
        goalService.atualizarMetasComNovaSessao(novaSessao);
        
        return studySessionRepository.save(novaSessao); // Salva o histórico da sessão de estudo
    }
}


