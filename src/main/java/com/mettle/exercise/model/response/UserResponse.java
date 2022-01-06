package com.mettle.exercise.model.response;

import com.mettle.exercise.model.Feature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String name;
    private Set<Feature> features;
}