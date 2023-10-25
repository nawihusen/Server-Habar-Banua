package com.habarbanua.model.portfolio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.habarbanua.entity.Education;
import com.habarbanua.entity.Experience;
import com.habarbanua.entity.Project;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioModel {
    private String name;

    private String summary;

    private String address;

    private String phone;

    private String email;

    private String status;

    @JsonProperty("linked_in")
    private String linkedIn;

    private String github;

    private String cv;

    private List<EducationModel> education;

    private List<ExperienceModel> experiences;

    private List<ProjectModel> projects;
}
