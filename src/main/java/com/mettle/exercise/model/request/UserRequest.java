package com.mettle.exercise.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    @NotNull
    @NotBlank(message = "userName is mandatory f")
    private String userName;
    private List<String> features;
}