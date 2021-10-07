package com.kirekov.test_levels.service.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kirekov.test_levels.entity.AppTestBuilder;
import com.kirekov.test_levels.entity.Rule;
import com.kirekov.test_levels.entity.RuleTypeTestBuilder;
import com.kirekov.test_levels.repository.AppRepository;
import com.kirekov.test_levels.repository.RuleRepository;
import com.kirekov.test_levels.repository.RuleTypeRepository;
import com.kirekov.test_levels.service.validation.RuleValidatorService;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RuleServiceImplMocksTest {

  @Mock
  private RuleValidatorService ruleValidatorService;
  @Mock
  private AppRepository appRepository;
  @Mock
  private RuleTypeRepository ruleTypeRepository;
  @Mock
  private RuleRepository ruleRepository;
  private RuleService ruleService;

  @BeforeEach
  void init() {
    ruleService = new RuleServiceImpl(
        ruleValidatorService,
        appRepository,
        ruleTypeRepository,
        ruleRepository
    );
  }

  @Test
  @DisplayName("Should create rule successfully")
  void shouldCreateRuleSuccessfully() {
    final var ruleInfo = new RuleInfo("name", "key", "value", 1L, 2L);
    final var ruleType = RuleTypeTestBuilder.builder().setId(1L).build();
    when(ruleTypeRepository.findById(1L)).thenReturn(Optional.of(ruleType));
    final var app = AppTestBuilder.builder().setId(1L).build();
    when(appRepository.findById(2L)).thenReturn(Optional.of(app));
    when(ruleValidatorService.isRuleValid(any(), any())).thenReturn(true);
    when(ruleRepository.saveAndFlush(any())).thenAnswer(invocation -> {
      final Rule currRule = invocation.getArgument(0);
      return currRule.setId(1L);
    });

    final var result = ruleService.createRule(ruleInfo);

    assertNotNull(result.getId());
    assertEquals("name", ruleInfo.getName());
    assertEquals("key", ruleInfo.getKey());
    assertEquals("value", ruleInfo.getValue());
  }

  @Test
  @DisplayName("Should create multiple rules successfully")
  void shouldCreateMultipleRulesSuccessfully() {
    final var rulesInfo = Stream.of("ip", "host", "user_agent")
        .map(name -> new RuleInfo(name, "key", "value", 1L, 2L))
        .collect(Collectors.toList());
    final var ruleType = RuleTypeTestBuilder.builder().setId(1L).build();
    when(ruleTypeRepository.findById(1L)).thenReturn(Optional.of(ruleType));
    final var app = AppTestBuilder.builder().setId(1L).build();
    when(appRepository.findById(2L)).thenReturn(Optional.of(app));
    when(ruleValidatorService.isRuleValid(any(), any())).thenReturn(true);
    final var idGenerator = new AtomicLong();
    when(ruleRepository.saveAndFlush(any())).thenAnswer(invocation -> {
      final Rule currRule = invocation.getArgument(0);
      return currRule.setId(idGenerator.incrementAndGet());
    });

    final var list = ruleService.createRules(rulesInfo);

    for (Rule rule : list) {
      assertNotNull(rule.getId());
      assertTrue(List.of("ip", "host", "user_agent").contains(rule.getName()));
      assertEquals("key", rule.getKey());
      assertEquals("value", rule.getValue());
    }
  }
}