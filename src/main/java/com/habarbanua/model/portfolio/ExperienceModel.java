package com.habarbanua.model.portfolio;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.habarbanua.entity.Portfolio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceModel {
    private Long id;

//    private Portfolio owner;

    @JsonProperty("company_name")
    private String companyName;

    private String role;

    private String description;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;
}
