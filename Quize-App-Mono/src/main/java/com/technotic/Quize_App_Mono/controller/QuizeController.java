package com.technotic.Quize_App_Mono.controller;

import com.technotic.Quize_App_Mono.entity.ResponseAnswer;
import com.technotic.Quize_App_Mono.service.QuizeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/quize")
@RequiredArgsConstructor
public class QuizeController {

    private final QuizeService quizeService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuize(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizeService.createQuize(category,numQ,title);
    }

    @GetMapping("/getQuize/{id}")
    public ResponseEntity getQuize(@PathVariable int id){
        return quizeService.getQuize(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity submitAnswers(@PathVariable int id,@RequestBody List<ResponseAnswer> responseAnswer){
        return quizeService.checkMarks(id,responseAnswer);
    }
}
