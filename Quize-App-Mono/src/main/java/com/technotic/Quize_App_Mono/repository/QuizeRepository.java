package com.technotic.Quize_App_Mono.repository;

import com.technotic.Quize_App_Mono.entity.Question;
import com.technotic.Quize_App_Mono.entity.Quize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface QuizeRepository extends JpaRepository<Quize,Integer> {




}
