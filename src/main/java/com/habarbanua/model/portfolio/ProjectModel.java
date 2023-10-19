package com.habarbanua.model.portfolio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.habarbanua.entity.Portfolio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectModel {
    private Long id;

//    private Portfolio owner;

    private String name;

    private String company;

    private String summary;

    @JsonProperty("tech_stack")
    private String techStack;

    private String github;
}
