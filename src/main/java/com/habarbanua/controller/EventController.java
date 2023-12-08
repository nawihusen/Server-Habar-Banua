package com.habarbanua.controller;

import com.habarbanua.entity.User;
import com.habarbanua.model.PagingResponse;
import com.habarbanua.model.Response;
import com.habarbanua.model.event.EventFilter;
import com.habarbanua.model.event.EventModel;
import com.habarbanua.model.news.NewsResponse;
import com.habarbanua.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(path = "/event", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> postEvent(User user,@RequestBody EventModel request){
        eventService.addEvent(user, request);
        return Response.<String>builder().data("Success").build();
    }

    @PatchMapping(path = "/event/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> patchEvent(User user,@RequestBody EventModel request, @PathVariable(name = "id") Long id){
        eventService.editEvent(user, request, id);
        return Response.<String>builder().data("Success").build();
    }

    @DeleteMapping(path = "event/{id}")
    private Response<String> deleteEvent(User user,@PathVariable(name = "id") Long id){
        eventService.deleteEvent(user, id);
        return Response.<String>builder().data("Success").build();
    }

    @GetMapping(path = "event/{id}")
    private Response<EventModel> getEvent(@PathVariable Long id){
        var event = eventService.getEvent(id);
        return Response.<EventModel>builder().data(event).build();
    }


    @GetMapping(path = "event")
    public Response<List<EventModel>> getEvents(User user,
                                                @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                @RequestParam(name = "limit", defaultValue = "10", required = false) int limit,
                                                @RequestParam(name = "sort", defaultValue = "id", required = false) String sort){
        var resp = eventService.getEvents(new EventFilter());
        return Response.<List<EventModel>>builder()
                .data(resp.getContent())
                .pagingResponse(PagingResponse.builder()
                        .currentPage(resp.getNumber() + 1)
                        .totalPage(resp.getTotalPages())
                        .totalData(resp.getTotalElements())
                        .build())
                .build();
    }
}
