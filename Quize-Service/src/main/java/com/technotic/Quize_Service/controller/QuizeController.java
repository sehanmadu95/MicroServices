package com.technotic.Quize_Service.controller;


import com.technotic.Quize_Service.entity.QuizeDto;
import com.technotic.Quize_Service.entity.ResponseAnswer;
import com.technotic.Quize_Service.service.QuizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/quize")
@RequiredArgsConstructor
@Slf4j
public class QuizeController {

    private final QuizeService quizeService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuize(@RequestBody QuizeDto quizeDto){
        log.info("hit quize controller create method...");
        return quizeService.createQuize(quizeDto.getCategoryName(),quizeDto.getNumQ(), quizeDto.getTitle());
    }

    @GetMapping("/getQuize/{id}")
    public ResponseEntity getQuize(@PathVariable int id){
        return quizeService.getQuize(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity submitAnswers(
            @PathVariable int id,@RequestBody List<ResponseAnswer> responseAnswer){
        return quizeService.checkMarks(id,responseAnswer);
    }
}
