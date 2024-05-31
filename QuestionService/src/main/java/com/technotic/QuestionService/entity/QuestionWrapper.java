package com.technotic.QuestionService.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionWrapper {
    private Integer qId;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
