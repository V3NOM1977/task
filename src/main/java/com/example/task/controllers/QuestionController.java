package com.example.task.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.NextQuestionDto;
import com.example.task.models.Question;
import com.example.task.services.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /**
     * This function returns a question for a game if available, otherwise returns a
     * not found
     * response.
     * 
     * @return This method returns a ResponseEntity object that contains either a
     *         Question object if it
     *         is present, or a not found response if it is not present.
     */
    @GetMapping(path = "play")
    public ResponseEntity<Question> play() {
        Optional<Question> opt = questionService.getQuestion();
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * This is a Java function that returns the next question based on the provided
     * question ID and
     * answer.
     * 
     * @param questionId A Long value representing the ID of the current question.
     * @param answer     A string representing the answer to the current question.
     * @return This method returns a ResponseEntity object that contains a
     *         NextQuestionDto object if
     *         the next question is available, or a not found response if the next
     *         question is not available.
     */
    @PostMapping(path = "next")
    public ResponseEntity<NextQuestionDto> next(@RequestParam("question_id") Long questionId,
            @RequestParam("answer") String answer) {
        NextQuestionDto next = questionService.nextQuestion(questionId);
        if (next != null) {
            return ResponseEntity.ok(next);
        }
        return ResponseEntity.notFound().build();
    }

}
