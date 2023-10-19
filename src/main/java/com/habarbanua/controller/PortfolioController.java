package com.habarbanua.controller;

import com.habarbanua.model.Response;
import com.habarbanua.model.portfolio.PortfolioModel;
import com.habarbanua.service.news.NewsService;
import com.habarbanua.service.portfolio.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping(path = "/owner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  Response<String> postPortfolio(PortfolioModel portfolio){
//        Tambahkan logic ke service
        return Response.<String>builder().data("Success").build();
    }

    @PatchMapping(path = "/owner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  Response<String> patchPortfolio(PortfolioModel portfolio){
//        Tambahkan logic ke service
        return Response.<String>builder().data("Success").build();
    }

    @GetMapping(path = "/owner", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<PortfolioModel> getPortfolio(){
//        Tambahkan logic ke service
        return null;
    }

//    @PostMapping
//    @PatchMapping
//    @GetMapping
//    @PostMapping
//    @PatchMapping
//    @GetMapping
}
