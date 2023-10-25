package com.habarbanua.service.portfolio;

import com.habarbanua.entity.Experience;
import com.habarbanua.entity.Portfolio;
import com.habarbanua.entity.Project;
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

    void updateExperience(ExperienceModel experience, Integer id);

    PageImpl<ExperienceModel> getExperiences();

    ExperienceModel getExperience(Long id);

    void addProject(ProjectModel project);

    void updateProject(Long id, ProjectModel project);

    PageImpl<ProjectModel> getProjects();

    ProjectModel getProject(Long id);
}
