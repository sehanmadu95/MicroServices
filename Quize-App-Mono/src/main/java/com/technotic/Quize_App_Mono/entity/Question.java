package com.technotic.Quize_App_Mono.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_question")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer qId;
    private String category;
    private String difficultyLevel;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;
}
