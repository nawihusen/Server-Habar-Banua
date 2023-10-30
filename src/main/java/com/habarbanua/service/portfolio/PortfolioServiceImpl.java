package com.habarbanua.service.portfolio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.habarbanua.entity.Education;
import com.habarbanua.entity.Experience;
import com.habarbanua.entity.Portfolio;
import com.habarbanua.entity.Project;
import com.habarbanua.model.news.NewsResponse;
import com.habarbanua.model.portfolio.EducationModel;
import com.habarbanua.model.portfolio.ExperienceModel;
import com.habarbanua.model.portfolio.PortfolioModel;
import com.habarbanua.model.portfolio.ProjectModel;
import com.habarbanua.repository.mysql.*;
import com.habarbanua.service.ValidationService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService{

    private String owner = "Muhammad Nawi Husen";

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ValidationService validationService;
    @Override
    @Transactional
    public void postPortfolio(PortfolioModel portfolio) {
        Portfolio port = new Portfolio();
        port.setName(portfolio.getName());
        port.setSummary(portfolio.getSummary());
        port.setAddress(portfolio.getAddress());
        port.setPhone(portfolio.getPhone());
        port.setEmail(portfolio.getEmail());
        port.setStatus(portfolio.getStatus());
        port.setLinkedIn(portfolio.getLinkedIn());
        port.setGithub(portfolio.getGithub());
        port.setCv(portfolio.getCv());

        portfolioRepository.save(port);
    }

    @Override
    public void updatePortfolio(PortfolioModel portfolio) {
        var port = portfolioRepository.findByName(owner); // proof that i make this by myself

        if (Objects.nonNull(portfolio.getSummary())){
            port.setSummary(portfolio.getSummary());
        }

        if (Objects.nonNull(portfolio.getAddress())){
            port.setAddress(portfolio.getAddress());
        }

        if (Objects.nonNull(portfolio.getPhone())){
            port.setPhone(portfolio.getPhone());
        }

        if (Objects.nonNull(portfolio.getEmail())){
            port.setEmail(portfolio.getEmail());
        }

        if (Objects.nonNull(portfolio.getStatus())){
            port.setStatus(portfolio.getStatus());
        }

        if (Objects.nonNull(portfolio.getLinkedIn())){
            port.setLinkedIn(portfolio.getLinkedIn());
        }

        if (Objects.nonNull(portfolio.getGithub())){
            port.setGithub(portfolio.getGithub());
        }

        if (Objects.nonNull(portfolio.getCv())){
            port.setCv(portfolio.getCv());
        }

        portfolioRepository.save(port);
    }

    @Override
    public PortfolioModel getPortfolio() {
        var port = portfolioRepository.findByName(owner);
        return toModelPortfolio(port);
    }

    @Override
    public void addExperience(ExperienceModel experience) {
        var port = portfolioRepository.findByName(owner);

        Experience exp = new Experience();
        exp.setOwner(port);
        exp.setCompanyName(experience.getCompanyName());
        exp.setRole(experience.getRole());
        exp.setDescription(experience.getDescription());
        exp.setStartDate(experience.getStartDate());
        exp.setEndDate(experience.getEndDate());

        experienceRepository.save(exp);
    }

    @Override
    public void updateExperience(ExperienceModel experience, Long id) {
        var exp = experienceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "experience not found"));

        if (Objects.nonNull(experience.getCompanyName())){
            exp.setCompanyName(experience.getCompanyName());
        }

        if (Objects.nonNull(experience.getRole())){
            exp.setRole(experience.getRole());
        }

        if (Objects.nonNull(experience.getDescription())){
            exp.setDescription(experience.getDescription());
        }

        if (Objects.nonNull(experience.getCompanyName())){
            exp.setStartDate(experience.getStartDate());
        }

        if (Objects.nonNull(experience.getCompanyName())){
            exp.setEndDate(experience.getEndDate());
        }


        experienceRepository.save(exp);
    }

    @Override
    public void deleteExperience(Long id){
        var exp = experienceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "experience not found"));

        experienceRepository.delete(exp);
    }

    @Override
    public PageImpl<ExperienceModel> getExperiences(int page, int limit, String sort) {
        Pageable pageable = PageRequest.of(page,limit , Sort.by(Sort.Order.asc(sort)));
        var experiences = experienceRepository.findAll(pageable);
        List<ExperienceModel> responses = experiences.getContent().stream()
                .map(exp -> toModelExperience(exp))
                .collect(Collectors.toList());

        return new PageImpl<>(responses, pageable, experiences.getTotalElements());
    }

    @Override
    public ExperienceModel getExperience(Long id){
        var exp = experienceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "experience not found"));

        return toModelExperience(exp);
    }

    @Override
    public void addProject(ProjectModel project){
        Project pro = new Project();
        var own = portfolioRepository.findByName(owner);

        pro.setOwner(own);
        pro.setName(project.getName());
        pro.setCompany(project.getCompany());
        pro.setSummary(project.getSummary());
        pro.setTechStack(project.getTechStack());
        pro.setGithub(project.getGithub());

        projectRepository.save(pro);
    }

    @Override
    public void updateProject(Long id, ProjectModel project){
        var pro = projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "project not found"));

        if (Objects.nonNull(project.getName())){
            pro.setName(project.getName());
        }


        if (Objects.nonNull(project.getCompany())){
            pro.setCompany(project.getCompany());
        }


        if (Objects.nonNull(project.getSummary())){
            pro.setSummary(project.getSummary());
        }


        if (Objects.nonNull(project.getTechStack())){
            pro.setTechStack(project.getTechStack());
        }


        if (Objects.nonNull(project.getGithub())){
            pro.setGithub(project.getGithub());
        }

    }

    @Override
    public void deleteProject(Long id){
        var project = projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "project not found"));

        projectRepository.delete(project);
    }


    @Override
    public PageImpl<ProjectModel> getProjects(int page, int limit, String sort){
        Pageable pageable = PageRequest.of(page,limit , Sort.by(Sort.Order.asc(sort)));
        var projects = projectRepository.findAll(pageable);
        List<ProjectModel> responses = projects.getContent().stream()
                .map(project -> toModelProject(project))
                .collect(Collectors.toList());

        return new PageImpl<>(responses, pageable, projects.getTotalElements());
    }

    @Override
    public  ProjectModel getProject(Long id){
        var project = projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "project not found"));

        return toModelProject(project);
    }

    @Override
    public void addEducation(EducationModel education){
        Education edu = new Education();
        var port = portfolioRepository.findByName(owner);

        edu.setOwner(port);
        edu.setName(education.getName());
        edu.setStart(education.getStart());
        edu.setEnd(education.getEnd());
        edu.setSummary(education.getSummary());
        edu.setScore(education.getScore());

        educationRepository.save(edu);
    }

    @Override
    public void updateEducation(Long id, EducationModel education){
        var edu = educationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "education not found"));

        if (Objects.nonNull(education.getName())){
            edu.setName(education.getName());
        }

        if (Objects.nonNull(education.getStart())){
            edu.setStart(education.getStart());
        }

        if (Objects.nonNull(education.getEnd())){
            edu.setEnd(education.getEnd());
        }

        if (Objects.nonNull(education.getSummary())){
            edu.setSummary(education.getSummary());
        }

        if (Objects.nonNull(education.getScore())){
            edu.setScore(education.getScore());
        }

    }

    @Override
    public void deleteEducation(Long id){
        var education = educationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "education not found"));

        educationRepository.delete(education);
    }

    @Override
    public List<EducationModel> getEducations(){
        var educations = educationRepository.findAll();
        var response = educations.stream()
                .map(education -> toModelEducation(education))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public EducationModel getEducation(Long id){
        var education = educationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "education not found"));

        return toModelEducation(education);
    }






    private PortfolioModel toModelPortfolio(Portfolio portfolio){
        PortfolioModel model = new PortfolioModel();
        model.setName(portfolio.getName());
        model.setSummary(portfolio.getSummary());
        model.setAddress(portfolio.getAddress());
        model.setPhone(portfolio.getPhone());
        model.setEmail(portfolio.getEmail());
        model.setStatus(portfolio.getStatus());
        model.setLinkedIn(portfolio.getLinkedIn());
        model.setGithub(portfolio.getGithub());
        model.setCv(portfolio.getCv());

        return model;
    }

    private ExperienceModel toModelExperience(Experience experience){
        ExperienceModel model = new ExperienceModel();
        model.setId(experience.getId());
        model.setCompanyName(experience.getCompanyName());
        model.setRole(experience.getRole());
        model.setDescription(experience.getDescription());
        model.setStartDate(experience.getStartDate());
        model.setEndDate(experience.getEndDate());

        return model;
    }

    private ProjectModel toModelProject(Project project){
        ProjectModel model = new ProjectModel();
        model.setId(project.getId());
        model.setName(project.getName());
        model.setCompany(project.getCompany());
        model.setSummary(project.getSummary());
        model.setTechStack(project.getTechStack());
        model.setGithub(project.getGithub());

        return model;
    }

    private EducationModel toModelEducation(Education education){
        EducationModel model = new EducationModel();
        model.setId(education.getId());
        model.setName(education.getName());
        model.setStart(education.getStart());
        model.setEnd(education.getEnd());
        model.setSummary(education.getSummary());
        model.setScore(education.getScore());

        return model;
    }

    private String toStringInstant(Instant instant){

        return null;
    }

    private Instant toInstantString(String string){

        return null;
    }

}

