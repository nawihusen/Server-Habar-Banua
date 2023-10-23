package com.habarbanua.controller;

import com.habarbanua.entity.Experience;
import com.habarbanua.entity.Project;
import com.habarbanua.model.Response;
import com.habarbanua.model.portfolio.ExperienceModel;
import com.habarbanua.model.portfolio.PortfolioModel;
import com.habarbanua.model.portfolio.ProjectModel;
import com.habarbanua.service.news.NewsService;
import com.habarbanua.service.portfolio.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(path = "/owner/experience", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> postExperience(ExperienceModel experience){
        // tambah
        return Response.<String>builder().data("Success").build();
    }

    @PatchMapping(path = "/owner/experience/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> patchExperience(@PathVariable("id") Long id, ExperienceModel experience) {
        // tambah
        return Response.<String>builder().data("Success").build();
    }

    @GetMapping(path = "/owner/experience")
    public Response<Experience> getExperience(@RequestParam("id") Long id) {
        // tambah
        return null;
    }

    @GetMapping(path = "/owner/experience")
    public Response<List<Experience>> getExperiences(){
        return null;
    }

    @PostMapping(path = "/owner/project", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> postProject(ExperienceModel experience){
        // tambah
        return Response.<String>builder().data("Success").build();
    }

    @PatchMapping(path = "/owner/project/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> patchProject(@PathVariable("id") Long id, ProjectModel project) {
        // tambah
        return Response.<String>builder().data("Success").build();
    }

    @GetMapping(path = "/owner/project")
    public Response<Project> getProject(@RequestParam("id") Long id) {
        // tambah
        return null;
    }

    @GetMapping(path = "/owner/project")
    public Response<List<Project>> getProjects() {
        // tambah
        return null;
    }

}
