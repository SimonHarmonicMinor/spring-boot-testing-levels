package com.kirekov.test_levels.repository;

import com.kirekov.test_levels.entity.Aggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AggregateRepository extends JpaRepository<Aggregate, Long> {

}
