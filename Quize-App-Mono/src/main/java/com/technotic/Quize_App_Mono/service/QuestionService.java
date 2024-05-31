package com.technotic.Quize_App_Mono.service;

import com.technotic.Quize_App_Mono.entity.Question;
import com.technotic.Quize_App_Mono.entity.QuestionWrapper;
import com.technotic.Quize_App_Mono.repository.QuestionRepository;
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

    public ResponseEntity addQuestion(Question question) {
        questionRepository.save(question);

        return  ResponseEntity.ok(toQuestionWrapper(question));
    }

    public ResponseEntity getQuestion(int id) {
        Optional<Question> q=questionRepository.findById(id);
        Question qGet=q.get();

        return ResponseEntity.ok(toQuestionWrapper(qGet));
    }

    public ResponseEntity getQuestionByCategory(String category) {
        List<Question> quesionList=questionRepository.findByCategory(category);


        List<QuestionWrapper> questionWrappersList = new ArrayList<>();
        for (Question qList : quesionList
        ) {
            questionWrappersList.add(toQuestionWrapper(qList));
        }
        return ResponseEntity.ok(questionWrappersList);
    }
}
