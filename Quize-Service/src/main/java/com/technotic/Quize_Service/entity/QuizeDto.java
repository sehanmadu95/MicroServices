package com.technotic.Quize_Service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizeDto {
    private String categoryName;
    private Integer numQ;
    private String title;
}
