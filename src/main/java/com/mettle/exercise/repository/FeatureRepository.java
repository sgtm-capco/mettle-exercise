package com.mettle.exercise.repository;

import com.mettle.exercise.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Optional<Feature> findByName(String featureName);

    Set<Feature> findByGloballyEnabled(Boolean enabled);
}
