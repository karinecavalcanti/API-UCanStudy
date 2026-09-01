package com.karine.ucanstudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karine.ucanstudy.model.Goal;
import com.karine.ucanstudy.model.StudySession;
import com.karine.ucanstudy.repository.GoalRepository;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public void atualizarMetasComNovaSessao(StudySession sessao){
        // 1. Buscamos todas as metas na despensa
        List<Goal> todasMetas = goalRepository.findAll();
        for (Goal meta: todasMetas){
            // 2. Só mexemos nas metas que ainda NÃO estão completas (false)
            if (meta.getCompleted() != null && meta.getCompleted() == false){
                
                // 3. Somamos a duração da sessão (minutos) no progresso da meta
                // OBS: Como é um Integer, precisamos garantir que ele não é nulo antes de somar. Se for nulo, consideramos 0.
                int progressoAtual = (meta.getProgress() != null) ? meta.getProgress() : 0;
                int novoProgresso = progressoAtual + sessao.getDuration();
                meta.setProgress(novoProgresso);

                // 4. Transformamos target_hours em minutos para comparar (1 hora = 60 minutos)
                // Exemplo: Meta de 2 horas vira 120 minutos.
                int alvoEmMinutos = (meta.getTarget_hours() != null) ? meta.getTarget_hours() * 60 : 0;

                // 5. Se o novo progresso passou ou igualou o alvo, a meta foi batida!
                if (novoProgresso >= alvoEmMinutos) {
                    meta.setCompleted(true);
                }

                // 6. Guarda a meta atualizada de volta na prateleira
                goalRepository.save(meta);
            }
        }
    }

}
