package com.example.nursinghome.controller;

import com.example.nursinghome.entitydto.ActionDTO;
import com.example.nursinghome.entitydto.BedDTO;
import com.example.nursinghome.service.ActionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

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

}
