package com.habarbanua.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventFilter {

    private Long userId;

    private String date;

    private String title;

    private String location;

    private int page;

    private int limit;

    private String sort;
}
