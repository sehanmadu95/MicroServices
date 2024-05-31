package com.technotic.Quize_App_Mono.entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class ResponseAnswer {
    private Integer id;
    private String response;
}
