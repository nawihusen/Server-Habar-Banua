package com.habarbanua.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventModel {
    @JsonProperty("user_id")
    private Long userId;

    private String date;

    private String title;

    private String description;

    private String location;
}
