package com.habarbanua.repository.mysql;

import com.habarbanua.entity.Education;
import com.habarbanua.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, String> {

    Optional<Education> findById(Long id);
}
