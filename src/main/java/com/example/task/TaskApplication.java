package com.example.task;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.example.task.services.QuestionService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class TaskApplication {

	private final QuestionService questionService;

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

	/**
	 * This function fetches five random questions using the questionService when
	 * the
	 * ApplicationReadyEvent is triggered/Started.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void fetchRandomFiveQuestions() throws ParseException {
		questionService.fetchRandomFiveQuestions();
	}

}
