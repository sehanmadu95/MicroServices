package com.technotic.Quize_Service.service;


import com.technotic.Quize_Service.entity.QuestionWrapper;
import com.technotic.Quize_Service.entity.Quize;
import com.technotic.Quize_Service.entity.ResponseAnswer;
import com.technotic.Quize_Service.feign.QuestionInterface;
import com.technotic.Quize_Service.repository.QuizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizeService {

    private final QuestionInterface questionInterface;

    private final QuizeRepository quizeRepository;

    public ResponseEntity<String> createQuize(String category, int numQ,String titile) {
        log.info("Hit QuizeService creareQuize method....");
       List<Integer> question= questionInterface.getQuestionForQuize(category,numQ).getBody();
        Quize quize=Quize.builder()
                .titile(titile)
                .questions(question)
                .build();

        quizeRepository.save(quize);
        return new ResponseEntity<>("Quize Created..", HttpStatus.CREATED);
    }


    public ResponseEntity getQuize(int id) {
        Quize quize=quizeRepository.findById(id).get();
        List<Integer> qIds=quize.getQuestions();

        List<QuestionWrapper>q= questionInterface.getQuestionFromId(qIds).getBody();
        return new ResponseEntity(q,HttpStatus.OK);
    }



    public ResponseEntity<Integer> checkMarks(int id, List<ResponseAnswer> responseAnswer) {

            ResponseEntity<Integer> score=questionInterface.getScore(responseAnswer);

        return score;
    }
}
