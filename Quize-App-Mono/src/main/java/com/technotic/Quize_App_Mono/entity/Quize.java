package com.technotic.Quize_App_Mono.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "t_quize")
@Data
public class Quize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quizeId;

    private String titile;

    @ManyToMany
    private List<Question> questions;
}
