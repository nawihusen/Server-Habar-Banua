package com.habarbanua.controller;

import com.habarbanua.entity.Education;
import com.habarbanua.entity.Experience;
import com.habarbanua.entity.Project;
import com.habarbanua.entity.User;
import com.habarbanua.model.PagingResponse;
import com.habarbanua.model.Response;
import com.habarbanua.model.news.NewsResponse;
import com.habarbanua.model.portfolio.EducationModel;
import com.habarbanua.model.portfolio.ExperienceModel;
import com.habarbanua.model.portfolio.PortfolioModel;
import com.habarbanua.model.portfolio.ProjectModel;
import com.habarbanua.service.news.NewsService;
import com.habarbanua.service.portfolio.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping(path = "/owner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  Response<String> postPortfolio(PortfolioModel portfolio){
        portfolioService.postPortfolio(portfolio);
        return Response.<String>builder().data("Success").build();
    }

    @PatchMapping(path = "/owner", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  Response<String> patchPortfolio(PortfolioModel portfolio){
        portfolioService.updatePortfolio(portfolio);
        return Response.<String>builder().data("Success").build();
    }

    @GetMapping(path = "/owner", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<PortfolioModel> getPortfolio(){
        var portfolio = portfolioService.getPortfolio();
        return Response.<PortfolioModel>builder().data(portfolio).build();
    }

    @PostMapping(path = "/owner/experience", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> postExperience(ExperienceModel experience){
        portfolioService.addExperience(experience);
        return Response.<String>builder().data("Success").build();
    }

    @PatchMapping(path = "/owner/experience/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> patchExperience(@PathVariable("id") Long id, ExperienceModel experience) {
        portfolioService.updateExperience(experience, id);
        return Response.<String>builder().data("Success").build();
    }

    @DeleteMapping(path = "/owner/experience/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> deleteExperience(@PathVariable("id") Long id) {
        portfolioService.deleteExperience(id);
        return Response.<String>builder().data("Success").build();
    }


    @GetMapping(path = "/owner/experience/{id}")
    public Response<ExperienceModel> getExperience(@PathVariable("id") Long id) {
        var exp = portfolioService.getExperience(id);
        return Response.<ExperienceModel>builder().data(exp).build();
    }

    @GetMapping(path = "/owner/experience")
    public Response<List<ExperienceModel>> getExperiences(
                                                     @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                     @RequestParam(name = "limit", defaultValue = "10", required = false) int limit,
                                                     @RequestParam(name = "sort", defaultValue = "id", required = false) String sort){
        var exps = portfolioService.getExperiences(page - 1, limit, sort);
        return Response.<List<ExperienceModel>>builder()
                .data(exps.getContent())
                .pagingResponse(PagingResponse.builder()
                        .currentPage(exps.getNumber() + 1)
                        .totalPage(exps.getTotalPages())
                        .totalData(exps.getTotalElements())
                        .build())
                .build();
    }

    @PostMapping(path = "/owner/project", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> postProject(ProjectModel project){
        portfolioService.addProject(project);
        return Response.<String>builder().data("Success").build();
    }

    @PatchMapping(path = "/owner/project/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> patchProject(@PathVariable("id") Long id, ProjectModel project) {
        portfolioService.updateProject(id, project);
        return Response.<String>builder().data("Success").build();
    }

    @DeleteMapping(path = "/owner/project/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> deleteProject(@PathVariable("id") Long id) {
        portfolioService.deleteProject(id);
        return Response.<String>builder().data("Success").build();
    }

    @GetMapping(path = "/owner/project/{id}")
    public Response<ProjectModel> getProject(@PathVariable("id") Long id) {
        var project = portfolioService.getProject(id);
        return Response.<ProjectModel>builder().data(project).build();
    }

    @GetMapping(path = "/owner/project")
    public Response<List<ProjectModel>> getProjects(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "10", required = false) int limit,
            @RequestParam(name = "sort", defaultValue = "id", required = false) String sort) {
        var projects = portfolioService.getProjects(page - 1, limit, sort);

        return Response.<List<ProjectModel>>builder()
                .data(projects.getContent())
                .pagingResponse(PagingResponse.builder()
                        .currentPage(projects.getNumber() + 1)
                        .totalPage(projects.getTotalPages())
                        .totalData(projects.getTotalElements())
                        .build())
                .build();
    }

    @PostMapping(path = "/owner/education",consumes = MediaType.APPLICATION_JSON_VALUE )
    public Response<String> postEducation(EducationModel model){
        portfolioService.addEducation(model);
        return Response.<String>builder().data("Success").build();
    }

    @PatchMapping(path = "/owner/education/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> patchEducation(EducationModel model,@PathVariable Long id){
        portfolioService.updateEducation(id, model);
        return Response.<String>builder().data("Success").build();
    }

    @DeleteMapping(path = "/owner/education/{id}")
    public Response<String> deleteEducation(EducationModel model,@PathVariable Long id){
        portfolioService.deleteEducation(id);
        return Response.<String>builder().data("Success").build();
    }

    @GetMapping(path = "/owner/education")
    public Response<List<EducationModel>> getEducations(){
        var educations = portfolioService.getEducations();
        return Response.<List<EducationModel>>builder().data(educations).build();
    }
}
