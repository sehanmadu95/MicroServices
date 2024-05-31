package com.technotic.QuestionService.controller;



import com.technotic.QuestionService.entity.Question;
import com.technotic.QuestionService.entity.QuestionWrapper;
import com.technotic.QuestionService.entity.ResponseAnswers;
import com.technotic.QuestionService.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    private final Environment environment;

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
/*
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuize
            (@RequestParam String category,@RequestParam Integer numQ){
        return questionService.getQuestionsForQuize(category,numQ);
    }*/


    @GetMapping("/generate")
    public ResponseEntity<List<Integer>>getQuestionForQuize(
            @RequestParam String category,@RequestParam Integer numQ){
        return questionService.getQuestionForQuize(category,numQ);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionFromId(@RequestBody List<Integer> questionId ){
        log.info(environment.getProperty("local.server.port"));

        return questionService.getQuestionFromId(questionId);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer>getScore(@RequestBody List<ResponseAnswers> res){
        return questionService.getScore(res);
    }


}
