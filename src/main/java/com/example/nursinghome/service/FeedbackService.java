package com.example.nursinghome.service;

import com.example.nursinghome.entity.Feedback;
import com.example.nursinghome.entity.User;
import com.example.nursinghome.entitydto.FeedBackDTO;
import com.example.nursinghome.repository.FeedbackRepository;
import com.example.nursinghome.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public Feedback saveFeedback(FeedBackDTO feedBackDTO) {
        User user = userRepository.findByID(feedBackDTO.getUserId());
        var feedback = Feedback.builder()
                .user(user)
                .comment(feedBackDTO.getComment())
                .build();
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
