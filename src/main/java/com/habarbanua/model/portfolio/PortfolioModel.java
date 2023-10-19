package com.habarbanua.model.portfolio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String education;

    @JsonProperty("linked_in")
    private String linkedIn;

    private String github;

    private String cv;
}
