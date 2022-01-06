package com.mettle.exercise.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureRequest {
    @NotNull
    @NotBlank(message = "featureName is mandatory")
    private String featureName;
    private Boolean globallyEnabled = false;
}