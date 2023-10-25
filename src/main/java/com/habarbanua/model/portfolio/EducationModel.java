package com.habarbanua.model.portfolio;

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
public class EducationModel {
    private Long id;

//    private Portfolio owner;

    private String name;

    private String start;

    private String end;

    private String summary;

    private Integer score;
}
