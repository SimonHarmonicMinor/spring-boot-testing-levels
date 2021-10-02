package com.kirekov.test_levels.repository;

import com.kirekov.test_levels.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, Long> {

}
