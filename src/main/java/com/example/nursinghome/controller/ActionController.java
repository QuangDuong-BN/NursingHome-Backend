package com.example.nursinghome.controller;

import com.example.nursinghome.entity.Action;
import com.example.nursinghome.entitydto.ActionDTO;
import com.example.nursinghome.service.ActionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63344")
@RequiredArgsConstructor
@RestController
@RequestMapping("/action")
public class ActionController {
    private final ActionService actionService;

    @PostMapping("/add")
    public ResponseEntity<?> addAction(HttpServletRequest httpServletRequest, @RequestBody ActionDTO actionDTO) {
        actionService.addAction(httpServletRequest, actionDTO);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAction(HttpServletRequest httpServletRequest, @RequestParam Date dateOfAction) {
        actionService.getAction(httpServletRequest, dateOfAction);
        return ResponseEntity.ok(actionService.getAction(httpServletRequest, dateOfAction));
    }

    @GetMapping
    public List<Object[]> getAllActions() {
        return actionService.getAllActions();
    }

    @GetMapping("/{id}")
    public Action getActionById(@PathVariable Long id) {
        return actionService.getActionById(id);
    }

    @GetMapping("/date/{date}")
    public List<Object[]> getActionsByDate(@PathVariable Date date) {
        return actionService.getActionsByDate(date);
    }

    @PutMapping
    public Action updateAction(@RequestBody Action action) {
        return actionService.updateAction(action);
    }

    @DeleteMapping("/{id}")
    public void deleteAction(@PathVariable Long id) {
        actionService.deleteAction(id);
    }

}
