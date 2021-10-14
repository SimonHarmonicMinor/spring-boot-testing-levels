package com.kirekov.test_levels.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kirekov.test_levels.entity.App;
import com.kirekov.test_levels.entity.AppTestBuilder;
import com.kirekov.test_levels.entity.RuleTestBuilder;
import com.kirekov.test_levels.entity.RuleType;
import com.kirekov.test_levels.entity.RuleTypeTestBuilder;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RuleRepositoryTestFindUniqueNames {
  @Autowired
  private RuleRepository ruleRepository;
  @Autowired
  private RuleTypeRepository ruleTypeRepository;
  @Autowired
  private AppRepository appRepository;
  private App defaultApp;
  private RuleType defaultRuleType;

  @BeforeEach
  void beforeEach() {
    defaultApp = appRepository.saveAndFlush(
        AppTestBuilder.builder().build()
    );
    defaultRuleType = ruleTypeRepository.saveAndFlush(
        RuleTypeTestBuilder.builder().build()
    );
  }

  @Test
  void shouldReturnUniqueNames() {
    createRule("name1");
    createRule("name2");
    createRule("name2");

    final var names = ruleRepository.findUniqueNames();

    assertEquals(Set.of("name1", "name2"), names);
  }

  private void createRule(String name) {
    ruleRepository.saveAndFlush(
        RuleTestBuilder.builder()
            .setName(name)
            .setRuleType(defaultRuleType)
            .setApp(defaultApp)
            .build()
    );
  }
}