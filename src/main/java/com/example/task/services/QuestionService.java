package com.example.task.services;

import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.task.NextQuestionDto;
import com.example.task.models.Category;
import com.example.task.models.Question;
import com.example.task.repositories.QuestionRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {

    private final String questionSourceUrl = "https://jservice.io/api/random";

    private final RestTemplate restTemplate;

    private final QuestionRepo questionRepo;

    /**
     * This Java function fetches five random questions from a REST API, parses the
     * JSON response, and
     * saves the questions to a database using Spring Data JPA.
     */
    @Transactional
    public void fetchRandomFiveQuestions() throws ParseException {
        for (int i = 0; i < 5; i++) {
            String resp = restTemplate.getForObject(questionSourceUrl, String.class);

            JSONParser parser = new JSONParser();
            JSONArray data = (JSONArray) parser.parse(resp);

            JSONObject obj = (JSONObject) data.get(0);

            Question question = Question.builder()
                    .question_id((Long) obj.get("id"))
                    .answer((String) obj.get("answer"))
                    .question((String) obj.get("question"))
                    .value((Long) obj.get("value"))
                    .airdate((String) obj.get("airdate"))
                    .createdAt((String) obj.get("created_at"))
                    .updatedAt((String) obj.get("updated_at"))
                    .category(Category.builder()
                            .id((Long) ((JSONObject) obj.get("category")).get("id"))
                            .title((String) ((JSONObject) obj.get("category")).get("title"))
                            .createdAt((String) ((JSONObject) obj.get("category")).get("created_at"))
                            .updatedAt((String) ((JSONObject) obj.get("category")).get("updated_at"))
                            .cluesCount((Long) ((JSONObject) obj.get("category")).get("clues_count"))
                            .build())
                    .gameId((Long) obj.get("game_id"))
                    .invalidCount((Long) obj.get("invalid_count"))
                    .build();

            questionRepo.save(question);
        }
    }

    /**
     * This function retrieves a question from a repository by its ID and returns it
     * as an optional
     * value, or an empty optional if it is not found.
     * 
     * @return The method `getQuestion()` returns an `Optional` object that contains
     *         a `Question`
     *         object with an ID of 1, if it exists in the `questionRepo`. If the
     *         `Optional` object is null,
     *         then an empty `Optional` object is returned.
     */
    public Optional<Question> getQuestion() {
        Optional<Question> opt = questionRepo.findById(1L);
        return (opt != null) ? opt : Optional.empty();
    }

    /**
     * This Java function retrieves the answer to a given question and the next
     * question's information,
     * and returns them in a NextQuestionDto object.
     * 
     * @param questionId A Long value representing the ID of the current question.
     * @return A NextQuestionDto object is being returned.
     */
    public NextQuestionDto nextQuestion(Long questionId) {
        String answer = questionRepo
                .findById(questionId)
                .get()
                .getAnswer();
        Optional<Question> opt = questionRepo.findById(questionId + 1);

        NextQuestionDto next = NextQuestionDto.builder()
                .correct_answer(answer)
                .next_question(opt)
                .build();
        return next;
    }

}
