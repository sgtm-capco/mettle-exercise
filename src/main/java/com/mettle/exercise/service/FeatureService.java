package com.mettle.exercise.service;

import com.mettle.exercise.model.Feature;
import com.mettle.exercise.model.request.FeatureRequest;
import com.mettle.exercise.repository.FeatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeatureService {
    private final FeatureRepository featureRepository;

    public ResponseEntity<HttpStatus> createFeature(FeatureRequest feature) {
        Optional<Feature> f = featureRepository.findByName(feature.getFeatureName());
        if (f.isEmpty()) {
            featureRepository.save(new Feature(null, feature.getFeatureName(), feature.getGloballyEnabled()));
            return ResponseEntity.ok(HttpStatus.CREATED);

        } else {
            f.get().setGloballyEnabled(feature.getGloballyEnabled());
            featureRepository.save(f.get());
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        }
    }

    public ResponseEntity<List<Feature>> getFeatures() {
        return ResponseEntity.ok(featureRepository.findAll());
    }
}
