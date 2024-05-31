package com.technotic.Quize_App_Mono.controller;


import com.technotic.Quize_App_Mono.entity.Question;
import com.technotic.Quize_App_Mono.entity.QuestionWrapper;
import com.technotic.Quize_App_Mono.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<QuestionWrapper>> getAllQuestion(){
        return questionService.getAllQuestion();
    }


    @PostMapping("/addQuestion")
    public ResponseEntity addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }


    @GetMapping("/getQuestion/{id}")
    public ResponseEntity addQuestion(@PathVariable int id){
        return questionService.getQuestion(id);
    }

    @GetMapping("/getQuestionByCategory/{category}")
    public ResponseEntity getQuestionByCat(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }


}
