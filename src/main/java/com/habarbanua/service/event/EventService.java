package com.habarbanua.service.event;

import com.habarbanua.entity.User;
import com.habarbanua.model.event.EventFilter;
import com.habarbanua.model.event.EventModel;
import com.habarbanua.model.news.NewsResponse;
import org.springframework.data.domain.PageImpl;

public interface EventService {

    void addEvent(User user, EventModel request);

    void editEvent(User user,EventModel request);

    void deleteEvent(User user,Long id);

    EventModel getEvent(Long id);

    PageImpl<EventModel> getEvents(EventFilter filter);
}
