package com.technotic.Quize_Service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

@Entity
@Table(name = "t_quize")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quizeId;

    private String titile;

    @ElementCollection
    private List<Integer> questions;
}
