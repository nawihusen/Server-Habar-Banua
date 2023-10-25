package com.habarbanua.repository.mysql;

import com.habarbanua.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, String> {
}
