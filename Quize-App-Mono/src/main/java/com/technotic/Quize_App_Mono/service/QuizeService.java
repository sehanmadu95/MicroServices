package com.technotic.Quize_App_Mono.service;

import com.technotic.Quize_App_Mono.entity.Question;
import com.technotic.Quize_App_Mono.entity.QuestionWrapper;
import com.technotic.Quize_App_Mono.entity.Quize;
import com.technotic.Quize_App_Mono.entity.ResponseAnswer;
import com.technotic.Quize_App_Mono.repository.QuestionRepository;
import com.technotic.Quize_App_Mono.repository.QuizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizeService {

    private final QuestionRepository questionRepository;
    private final QuizeRepository quizeRepository;

    public ResponseEntity<String> createQuize(String category, int numQ, String title) {
        List<Question> qList=questionRepository.findRandomQuestionCate(category,numQ);
        Quize quize=new Quize();
        quize.setTitile(title);
        quize.setQuestions(qList);
        quizeRepository.save(quize);

        return new ResponseEntity<>("Quize created Successfully", HttpStatus.CREATED);
    }


    public ResponseEntity getQuize(int id) {
        Optional<Quize> quize=quizeRepository.findById(id);

        List<Question> questionList=quize.get().getQuestions();

        List<QuestionWrapper> questionWrapperList=new ArrayList<>();
        for (Question q:questionList
             ) {
            questionWrapperList.add(toQuestionWrapper(q));
        }

        return new ResponseEntity(questionWrapperList,HttpStatus.OK);
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

    public ResponseEntity checkMarks(int id, List<ResponseAnswer> responseAnswer) {
        Optional<Quize> byId = quizeRepository.findById(id);
        List<Question>qListInQuize=byId.get().getQuestions();
        int right=0;
        int i=0;

        for (ResponseAnswer res:responseAnswer
             ) {
            if(res.getResponse().equals(qListInQuize.get(i).getCorrectAnswer())){
                right++;
            }
            i++;
        }


        return new ResponseEntity("You got "+right,HttpStatus.OK);
    }
}
