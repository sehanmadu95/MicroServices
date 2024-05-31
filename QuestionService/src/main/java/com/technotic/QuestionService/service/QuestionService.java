package com.technotic.QuestionService.service;


import com.technotic.QuestionService.entity.Question;
import com.technotic.QuestionService.entity.QuestionWrapper;
import com.technotic.QuestionService.entity.ResponseAnswers;
import com.technotic.QuestionService.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public ResponseEntity<List<QuestionWrapper>> getAllQuestion() {
        List<Question> quesionList = questionRepository.findAll();
        List<QuestionWrapper> questionWrappersList = new ArrayList<>();
        for (Question qList : quesionList
        ) {
            questionWrappersList.add(toQuestionWrapper(qList));
        }
        return ResponseEntity.ok(questionWrappersList);

    }


    public ResponseEntity addQuestion(Question question) {
        questionRepository.save(question);

        return ResponseEntity.ok(toQuestionWrapper(question));
    }

    public ResponseEntity getQuestion(int id) {
        Optional<Question> q = questionRepository.findById(id);
        Question qGet = q.get();

        return ResponseEntity.ok(toQuestionWrapper(qGet));
    }

    public ResponseEntity getQuestionByCategory(String category) {
        List<Question> quesionList = questionRepository.findByCategory(category);


        List<QuestionWrapper> questionWrappersList = new ArrayList<>();
        for (Question qList : quesionList
        ) {
            questionWrappersList.add(toQuestionWrapper(qList));
        }
        return ResponseEntity.ok(questionWrappersList);
    }

    private static QuestionWrapper toQuestionWrapper(Question question) {
        return QuestionWrapper.builder()
                .qId(question.getQId())
                .question(question.getQuestion())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4())
                .build();
    }

  /*  public ResponseEntity<List<Integer>> getQuestionsForQuize(String category, Integer numQ) {
        List<Integer> qList=questionRepository.findRandomQuestionCate(category,numQ);
        return new ResponseEntity<>(qList, HttpStatus.CREATED);
    }*/

    public ResponseEntity<List<Integer>> getQuestionForQuize(String category, Integer numQ) {
        List<Integer>questions=questionRepository.findRandomQuestionCate(category,numQ);

        return new ResponseEntity<>(questions,HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionId) {
    List<QuestionWrapper> wrapper=new ArrayList<>();
    List<Question>questions=new ArrayList<>();

        for (Integer x:questionId
             ) {
            questions.add(questionRepository.findById(x).get());
        }

        for (Question q:questions
             ) {
            wrapper.add(toQuestionWrapper(q));
        }

        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<ResponseAnswers> res) {
        int right=0;

        for (ResponseAnswers resAnswer:res
             ) {
            Question question=questionRepository.findById(resAnswer.getId()).get();
            if(resAnswer.getResponse().equals(question.getCorrectAnswer())){
                right++;
            }
        }

        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
