package com.example.nursinghome.controller;

import com.example.nursinghome.entity.Feedback;
import com.example.nursinghome.entitydto.FeedBackDTO;
import com.example.nursinghome.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/feedback")

public class FeedbackController {

    private final FeedbackService feedbackService;
    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/get_by_id")
    public Feedback getFeedbackById(@RequestParam("id") Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @PostMapping("/create")
    public Feedback createFeedback(@RequestBody FeedBackDTO feedBackDTO) {
        return feedbackService.saveFeedback(feedBackDTO);
    }

    @DeleteMapping("/delete")
    public void deleteFeedback(@RequestParam("id") Long id) {
        feedbackService.deleteFeedback(id);
    }
}
