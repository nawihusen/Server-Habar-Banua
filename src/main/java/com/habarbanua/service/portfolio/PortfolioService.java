package com.habarbanua.service.portfolio;

import com.habarbanua.entity.Experience;
import com.habarbanua.entity.Portfolio;
import com.habarbanua.entity.Project;
import com.habarbanua.model.portfolio.EducationModel;
import com.habarbanua.model.portfolio.ExperienceModel;
import com.habarbanua.model.portfolio.PortfolioModel;
import com.habarbanua.model.portfolio.ProjectModel;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Properties;

public interface PortfolioService {
    void postPortfolio(PortfolioModel owner);

    void updatePortfolio(PortfolioModel owner);

    PortfolioModel getPortfolio();

    void addExperience(ExperienceModel experience);

    void updateExperience(ExperienceModel experience, Long id);

    void deleteExperience(Long id);

    PageImpl<ExperienceModel> getExperiences(int page, int limit, String sort);

    ExperienceModel getExperience(Long id);

    void addProject(ProjectModel project);

    void updateProject(Long id, ProjectModel project);

    void deleteProject(Long id);

    PageImpl<ProjectModel> getProjects(int page, int limit, String sort);

    ProjectModel getProject(Long id);

    void addEducation(EducationModel project);

    void updateEducation(Long id, EducationModel project);

    void deleteEducation(Long id);

//    PageImpl<EducationModel> getEducations();
    List<EducationModel> getEducations();

    EducationModel getEducation(Long id);
}
