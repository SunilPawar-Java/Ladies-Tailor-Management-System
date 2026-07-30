package com.ltr.controller;

import com.ltr.dao.DesignDao;
import com.ltr.service.DesignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/design")
public class DesignController {
    private final DesignService designService;

    public DesignController(DesignService designService) {
        this.designService = designService;
    }

    @PostMapping
    public ResponseEntity<?> saveDesign(@RequestBody DesignDao designDao){
        designService.saveDesign(designDao);
        return ResponseEntity.ok().body("Design Saved...");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDesign(@PathVariable("id") Long id){
        DesignDao design = designService.getDesignById(id);
        return ResponseEntity.ok().body(Map.of("design", design));
    }
}
