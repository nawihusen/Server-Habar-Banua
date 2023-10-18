package com.habarbanua.service.portfolio;

import com.habarbanua.entity.Experience;
import com.habarbanua.entity.Portfolio;
import com.habarbanua.entity.Project;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService{
    @Override
    public void postPortfolio(Portfolio owner) {

    }

    @Override
    public void updatePortfolio(Portfolio owner) {

    }

    @Override
    public Portfolio getPortfolio() {
        return null;
    }

    @Override
    public void addExperience(Experience experience) {

    }

    @Override
    public void updateExperience(Experience experience, Integer id) {

    }

    @Override
    public PageImpl<Experience> getExperiences() {
        return null;
    }

    @Override
    public void addProject() {

    }

    @Override
    public void updateProject() {

    }

    @Override
    public PageImpl<Project> getProject() {
        return null;
    }
}
