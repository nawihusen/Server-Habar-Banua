package com.habarbanua.repository.mysql;

import com.habarbanua.entity.New;
import com.habarbanua.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<New, String> {

    Optional<New> findById(Long id);

    Page<New> findAllByUserId(User user, Pageable pageable);
}
