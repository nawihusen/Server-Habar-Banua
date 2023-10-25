package com.habarbanua.repository.mysql;

import com.habarbanua.entity.Experience;
import com.habarbanua.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, String> {

    Optional<Experience> findById(Long id);
}
