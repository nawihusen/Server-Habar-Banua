package com.habarbanua.service.portfolio;

import com.habarbanua.entity.Experience;
import com.habarbanua.entity.Portfolio;
import com.habarbanua.entity.Project;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Properties;

public interface PortfolioService {
    void postPortfolio(Portfolio owner);

    void updatePortfolio(Portfolio owner);

    Portfolio getPortfolio();

    void addExperience(Experience experience);

    void updateExperience(Experience experience, Integer id);

    PageImpl<Experience> getExperiences();

    void addProject();

    void updateProject();

    PageImpl<Project> getProject();
}
