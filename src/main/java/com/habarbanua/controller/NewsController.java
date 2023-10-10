package com.habarbanua.controller;

import com.habarbanua.entity.New;
import com.habarbanua.entity.User;
import com.habarbanua.model.PagingResponse;
import com.habarbanua.model.Response;
import com.habarbanua.model.news.NewsRequest;
import com.habarbanua.model.news.NewsResponse;
import com.habarbanua.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.Buffer;
import java.util.List;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping(path = "/users/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> postNew(User user,@RequestBody NewsRequest request){
        newsService.postNew(user, request);
        return Response.<String>builder().data("Success").build();
    }

    @PatchMapping(path = "/users/new/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> updateNews(User user,@RequestBody NewsRequest newsRequest, @PathVariable("id") Long id){
        newsService.updateNew(user, newsRequest, id);
        return Response.<String>builder().data("Success").build();
    }

    @DeleteMapping(path = "/users/new/{id}")
    public Response<String> deleteNews(User user, @PathVariable("id") Long id){
        newsService.deleteNew(user, id);
        return Response.<String>builder().data("Success").build();
    }

    @GetMapping(path = "/users/new")
    public Response<List<NewsResponse>> getUserNews(User user,
                                                    @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                    @RequestParam(name = "limit", defaultValue = "10", required = false) int limit,
                                                    @RequestParam(name = "sort", defaultValue = "id", required = false) String sort){
        PageImpl<NewsResponse> resp = newsService.getUserNews(user, page - 1, limit, sort);
        return Response.<List<NewsResponse>>builder()
                .data(resp.getContent())
                .pagingResponse(PagingResponse.builder()
                        .currentPage(resp.getNumber() + 1)
                        .totalPage(resp.getTotalPages())
                        .totalData(resp.getTotalElements())
                        .build())
                .build();
    }

    @GetMapping(path = "/news/{id}")
    public Response<NewsResponse> getANews(@PathVariable(name = "id") Long id){
        var aNew = newsService.getNew(id);
        return Response.<NewsResponse>builder()
                .data(aNew)
                .build();
    }

    @GetMapping(path = "/news")
    public Response<List<NewsResponse>> getNews(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                @RequestParam(name = "limit", defaultValue = "10", required = false) int limit,
                                                @RequestParam(name = "sort", defaultValue = "id", required = false) String sort){
        Page<NewsResponse> news = newsService.getNews(page - 1, limit, sort);
        return Response.<List<NewsResponse>>builder()
                .data(news.getContent())
                .pagingResponse(PagingResponse.builder()
                        .currentPage(news.getNumber() + 1)
                        .totalPage(news.getTotalPages())
                        .totalData(news.getTotalElements())
                        .build())
                .build();
    }


}
