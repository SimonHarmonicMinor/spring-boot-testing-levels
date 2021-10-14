package com.kirekov.test_levels.repository;

import com.kirekov.test_levels.entity.Rule;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RuleRepository extends JpaRepository<Rule, Long> {
  @Query("select distinct r.name from Rule r")
  Set<String> findUniqueNames();
}
