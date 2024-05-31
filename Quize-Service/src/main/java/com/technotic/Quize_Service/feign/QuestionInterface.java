package com.technotic.Quize_Service.feign;

import com.technotic.Quize_Service.entity.QuestionWrapper;
import com.technotic.Quize_Service.entity.ResponseAnswer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuestionInterface {

    @GetMapping("/api/v1/question/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuize(
            @RequestParam String category, @RequestParam Integer numQ);

    @PostMapping("/api/v1/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionFromId(
            @RequestBody List<Integer> questionId );

    @PostMapping("/api/v1/question/getScore")
    public ResponseEntity<Integer>getScore(
            @RequestBody List<ResponseAnswer> res);

}
