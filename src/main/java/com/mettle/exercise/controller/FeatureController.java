package com.mettle.exercise.controller;

import com.mettle.exercise.model.Feature;
import com.mettle.exercise.model.request.FeatureRequest;
import com.mettle.exercise.service.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
public class FeatureController {
    private final FeatureService featureService;

    @GetMapping("/admin/features")
    public ResponseEntity<List<Feature>> getFeatures() {
        return featureService.getFeatures();
    }

    @PostMapping(value = "/admin/features", consumes = "application/json")
    public ResponseEntity<HttpStatus> createFeature(@Valid @RequestBody FeatureRequest feature) {
        return featureService.createFeature(feature);
    }
}