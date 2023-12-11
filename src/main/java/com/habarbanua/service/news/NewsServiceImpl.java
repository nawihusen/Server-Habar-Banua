package com.habarbanua.service.news;

import com.habarbanua.entity.New;
import com.habarbanua.entity.User;
import com.habarbanua.model.news.NewsRequest;
import com.habarbanua.model.news.NewsResponse;
import com.habarbanua.repository.mysql.NewsRepository;
import com.habarbanua.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    @Transactional
    public void postNew(User user, NewsRequest request) {
        validationService.validate(request);

        New news = new New();
        news.setUserId(user);
        news.setContent(request.getContent());
        news.setTitle(request.getTitle());
        news.setCreatedAt(LocalDateTime.now());
        news.setUpdatedAt(LocalDateTime.now());

        newsRepository.save(news);
    }

    @Override
    @Transactional
    public void updateNew(User user, NewsRequest request, Long id) {
        New news = newsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak di temukan"));

        validationService.isSameUser(user.getId(), news.getUserId().getId());

        if (Objects.nonNull(request)){
            news.setUpdatedAt(LocalDateTime.now());
        }

        if (Objects.nonNull(request.getContent())){
            news.setContent(request.getContent());
        }

        if (Objects.nonNull(request.getTitle())){
            news.setTitle(request.getTitle());
        }

        newsRepository.save(news);
    }

    @Transactional
    @Override
    public void deleteNew(User user, Long id) {
        New news = newsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak di temukan"));

        validationService.isSameUser(user.getId(), news.getUserId().getId());

        newsRepository.delete(news);
    }

    @Override
    public NewsResponse getNew(Long id) {
        var aNew =  newsRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "tidak di temukan"));

        return toNewResponse(aNew);
    }

    @Override
    public PageImpl<NewsResponse> getNews(int page, int limit, String sort) {
        Pageable pageable = PageRequest.of(page,limit , Sort.by(Sort.Order.asc(sort)));
        var news = newsRepository.findAll(pageable);
        List<NewsResponse> newsResponses = news.getContent().stream()
                .map(aNew -> toNewResponse(aNew))
                .collect(Collectors.toList());

        return new PageImpl<>(newsResponses, pageable, news.getTotalElements());
    }

    @Override
    public PageImpl<NewsResponse> getUserNews(User user, int page, int limit, String sort){
        Pageable pageable = PageRequest.of(page,limit , Sort.by(Sort.Order.asc(sort)));
        var news = newsRepository.findAllByUserId(user, pageable);
        List<NewsResponse> newsResponses = news.getContent().stream()
                .map(aNew -> toNewResponse(aNew))
                .collect(Collectors.toList());

        return new PageImpl<NewsResponse>(newsResponses, pageable, news.getTotalElements());
    }

    private NewsResponse toNewResponse(New aNew){
        NewsResponse newResponse = new NewsResponse();
        newResponse.setId(aNew.getId());
        newResponse.setUserId(aNew.getUserId().getId());
        newResponse.setTitle(aNew.getTitle());
        newResponse.setContent(aNew.getContent());
        newResponse.setCreatedAt(aNew.getCreatedAt().toString());
        newResponse.setUpdatedAt(aNew.getUpdatedAt().toString());
        newResponse.setDeletedAt(aNew.getDeletedAt().toString());
        return newResponse;
    }
}
