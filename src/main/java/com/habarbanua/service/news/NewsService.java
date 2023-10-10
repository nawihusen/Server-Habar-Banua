package com.habarbanua.service.news;

import com.habarbanua.entity.New;
import com.habarbanua.entity.User;
import com.habarbanua.model.news.NewsRequest;
import com.habarbanua.model.news.NewsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NewsService {

    void postNew(User user, NewsRequest request);

    void updateNew(User user, NewsRequest request, Long id);

    void deleteNew(User user, Long id);

    PageImpl<NewsResponse> getUserNews(User user, int page, int limit, String sort);

    NewsResponse getNew(Long id);

    PageImpl<NewsResponse> getNews(int page, int limit, String sort);
}
