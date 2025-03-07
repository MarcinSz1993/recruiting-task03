package com.marcinsz.recruitingtask03.controller;

import com.marcinsz.recruitingtask03.model.ExpectedResponse;
import lombok.RequiredArgsConstructor;
import com.marcinsz.recruitingtask03.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @GetMapping("/solution")
    public ResponseEntity<List<ExpectedResponse>> getAllRequiredInfo(@RequestParam String username){
        return ResponseEntity.ok(apiService.getAllRequiredInfo(username));
    }
}
