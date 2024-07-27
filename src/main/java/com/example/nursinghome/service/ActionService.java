package com.example.nursinghome.service;

import com.example.nursinghome.entitydto.ActionDTO;
import com.example.nursinghome.repository.ActionRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.nursinghome.entity.Action;
import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ActionService {
    private final ActionRepository actionRepository;


    public void addAction(HttpServletRequest httpServletRequest, ActionDTO actionDTO) {
        var action = Action.builder()
                .name(actionDTO.getName())
                .dateOfAction(actionDTO.getDateOfAction())
                .timeOfDay(actionDTO.getTimeOfDay())
                .dateOfAction(actionDTO.getDateOfAction())
                .isVisitable(actionDTO.getIsVisitable())
                .description(actionDTO.getDescription())
                .build();
        actionRepository.save(action);
    }

    public List<Object[]> getAction(HttpServletRequest httpServletRequest, Date dateOfAction) {
        return actionRepository.getAction(dateOfAction);
    }

    public List<Object[]> getAllActions() {
        return actionRepository.getListAction();
    }

    public Action getActionById(Long id) {
        return actionRepository.findById(id).orElse(null);
    }

    public List<Object[]> getActionsByDate(Date date) {
        return actionRepository.findByDateOfAction(date);
    }

    public Action updateAction(Action action) {
        return actionRepository.save(action);
    }

    public void deleteAction(Long id) {
        actionRepository.deleteById(id);
    }
}
