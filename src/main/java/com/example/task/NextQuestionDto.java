package com.example.task;

import java.util.Optional;

import com.example.task.models.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * The NextQuestionDto class is a Java class with a correct answer string and an
 * optional next question
 * object, using the Lombok library annotations.
 */
@Getter
@Builder
@AllArgsConstructor
public class NextQuestionDto {

    private String correct_answer;

    private Optional<Question> next_question;

}
