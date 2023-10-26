package com.habarbanua.service.portfolio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.habarbanua.entity.Education;
import com.habarbanua.entity.Experience;
import com.habarbanua.entity.Portfolio;
import com.habarbanua.entity.Project;
import com.habarbanua.model.portfolio.EducationModel;
import com.habarbanua.model.portfolio.ExperienceModel;
import com.habarbanua.model.portfolio.PortfolioModel;
import com.habarbanua.model.portfolio.ProjectModel;
import com.habarbanua.repository.mysql.*;
import com.habarbanua.service.ValidationService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;

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
        exp.setStartDate(toInstantString(experience.getStartDate()));
        exp.setEndDate(toInstantString(experience.getEndDate()));

        experienceRepository.save(exp);
    }

    @Override
    public void updateExperience(ExperienceModel experience, Integer id) {

    }

    @Override
    public PageImpl<ExperienceModel> getExperiences() {
        return null;
    }

    @Override
    public ExperienceModel getExperience(Long id){
        return null;
    }

    @Override
    public void addProject(ProjectModel project){
        Project pro = new Project();


    }

    @Override
    public void updateProject(Long id, ProjectModel project){

    }

    @Override
    public PageImpl<ProjectModel> getProjects(){
        return null;
    }

    @Override
    public  ProjectModel getProject(Long id){
        return null;
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
        model.setStartDate(toStringInstant(experience.getStartDate()));
        model.setEndDate(toStringInstant(experience.getEndDate()));

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

