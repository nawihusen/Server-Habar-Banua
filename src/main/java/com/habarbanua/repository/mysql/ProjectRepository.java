package com.habarbanua.repository.mysql;

import com.habarbanua.entity.New;
import com.habarbanua.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    Optional<Project> findById(Long id);
}
