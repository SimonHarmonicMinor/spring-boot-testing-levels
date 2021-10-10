package com.kirekov.test_levels.service.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kirekov.test_levels.entity.Rule;
import com.kirekov.test_levels.entity.RuleTypeTestBuilder;
import com.kirekov.test_levels.entity.AppTestBuilder;
import com.kirekov.test_levels.repository.AppRepository;
import com.kirekov.test_levels.repository.RuleRepository;
import com.kirekov.test_levels.repository.RuleTypeRepository;
import com.kirekov.test_levels.service.validation.RuleValidatorService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureTestDatabase
class RuleServiceImplH2Test {
  @Autowired
  private RuleTypeRepository ruleTypeRepository;
  @Autowired
  private AppRepository appRepository;
  @Autowired
  private RuleRepository ruleRepository;
  @MockBean
  private RuleValidatorService ruleValidatorService;
  @Autowired
  private RuleService ruleService;

  @BeforeEach
  void beforeEach() {
    ruleRepository.deleteAll();
    appRepository.deleteAll();
    ruleTypeRepository.deleteAll();
  }

  @Test
  void shouldCreateMultipleRulesSuccessfully() {
    when(ruleValidatorService.isRuleValid(any(), any())).thenReturn(true);
    final var ruleType = ruleTypeRepository.saveAndFlush(RuleTypeTestBuilder.builder().build());
    final var app = appRepository.saveAndFlush(AppTestBuilder.builder().build());
    final var rulesInfo = Stream.of("ip", "host", "user_agent")
        .map(name -> new RuleInfo(name, "key", "value", ruleType.getId(), app.getId()))
        .collect(Collectors.toList());

    final var list = ruleService.createRules(rulesInfo);

    for (Rule rule : list) {
      assertNotNull(rule.getId());
      assertTrue(List.of("ip", "host", "user_agent").contains(rule.getName()));
      assertEquals("key", rule.getKey());
      assertEquals("value", rule.getValue());
    }
  }

  @Test
  @DisplayName("Should rollback if any rule is not valid")
  void shouldRollbackIfAnyRuleIsNotValid() {
    final var ruleType = ruleTypeRepository.saveAndFlush(RuleTypeTestBuilder.builder().build());
    final var app = appRepository.saveAndFlush(AppTestBuilder.builder().build());
    final var rulesInfo = Stream.of("ip", "host", "user_agent")
        .map(name -> new RuleInfo(name, "key", "value", ruleType.getId(), app.getId()))
        .collect(Collectors.toList());
    when(ruleValidatorService.isRuleValid(any(), any())).thenAnswer(invocation -> {
      Rule rule = invocation.getArgument(0);
      return !Objects.equals(rule.getName(), "user_agent");
    });

    assertThrows(IllegalArgumentException.class, () -> ruleService.createRules(rulesInfo));

    assertEquals(0, ruleRepository.count());
  }
}