package com.kirekov.test_levels.repository;

import com.kirekov.test_levels.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {

}
