package com.habarbanua.service.event;

import com.habarbanua.entity.Event;
import com.habarbanua.entity.User;
import com.habarbanua.model.event.EventFilter;
import com.habarbanua.model.event.EventModel;
import com.habarbanua.utils.Helper;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService{
    @Override
    public void addEvent(User user, EventModel request) {
        var event = new Event();
        event.setDate(Helper.toInstantString(request.getDate()));
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
    }

    @Override
    public void editEvent(User user,EventModel request) {

    }

    @Override
    public void deleteEvent(User user,Long id) {

    }

    @Override
    public EventModel getEvent(Long id) {
        return null;
    }

    @Override
    public PageImpl<EventModel> getEvents(EventFilter filter) {
        return null;
    }
}
