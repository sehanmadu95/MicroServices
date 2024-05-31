package com.technotic.Quize_Service.repository;



import com.technotic.Quize_Service.entity.Quize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizeRepository extends JpaRepository<Quize,Integer> {




}
