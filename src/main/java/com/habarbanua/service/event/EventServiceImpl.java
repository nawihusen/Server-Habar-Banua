package com.habarbanua.service.event;

import com.habarbanua.entity.Event;
import com.habarbanua.entity.User;
import com.habarbanua.model.event.EventFilter;
import com.habarbanua.model.event.EventModel;
import com.habarbanua.model.news.NewsResponse;
import com.habarbanua.repository.mysql.EventRepository;
import com.habarbanua.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Override
    @Transactional
    public void addEvent(User user, EventModel request) {
        var event = new Event();
        event.setUserId(user);
        event.setDate(Helper.toInstantString(request.getDate()));
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());

        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void editEvent(User user,EventModel request, Long id) {
        var event = eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak di temukan"));

        if (Objects.nonNull(request.getDate())){
            event.setDate(Helper.toInstantString(request.getDate()));
        }

        if (Objects.nonNull(request.getTitle())){
            event.setTitle(request.getTitle());
        }
        if (Objects.nonNull(request.getDescription())){
            event.setDescription(request.getDescription());
        }
        if (Objects.nonNull(request.getLocation())){
            event.setLocation(request.getLocation());
        }

        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void deleteEvent(User user,Long id) {
        var event = eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak di temukan"));

        eventRepository.delete(event);
    }

    @Override
    public EventModel getEvent(Long id) {
        var event = eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak di temukan"));

        return toModelEvent(event);
    }

    @Override
    public PageImpl<EventModel> getEvents(EventFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getLimit() , Sort.by(Sort.Order.asc(filter.getSort())));
        var events = eventRepository.findAll(pageable);
        List<EventModel> responses = events.getContent().stream()
                .map(event -> toModelEvent(event))
                .collect(Collectors.toList());

        return new PageImpl<EventModel>(responses, pageable, events.getTotalElements());
    }
//gasan add
    private EventModel toModelEvent(Event event){
        EventModel result = new EventModel();
        result.setDate(Helper.toStringInstant(event.getDate()));
        result.setUserId(event.getUserId().getId());
        result.setLocation(event.getLocation());
        result.setDescription(event.getDescription());
        result.setTitle(event.getTitle());
        result.setCreatedAt(event.getCreatedAt().toString());
        result.setUpdatedAt(event.getUpdatedAt().toString());

        return result;
    }

}
