package com.kirekov.test_levels.service.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kirekov.test_levels.audit.AuditService;
import com.kirekov.test_levels.entity.RuleTestBuilder;
import com.kirekov.test_levels.entity.RuleTypeTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("AuditingRuleValidatorService: isRuleValid test cases")
@ExtendWith(MockitoExtension.class)
class AuditingRuleValidatorServiceTest {
  @Mock
  private RuleValidatorService origin;
  @Mock
  private AuditService auditService;
  private RuleValidatorService ruleValidatorService;

  @BeforeEach
  void init() {
    ruleValidatorService = new AuditingRuleValidatorService(origin, auditService);
  }

  @ParameterizedTest
  @DisplayName("Should perform audit the right way")
  @CsvSource({
      "true,0",
      "false,1"
  })
  void shouldPerformAuditTheRightWay(boolean validness, int auditTimesCalled) {
    final var rule = RuleTestBuilder.builder().build();
    final var ruleType = RuleTypeTestBuilder.builder().build();
    when(origin.isRuleValid(rule, ruleType)).thenReturn(validness);

    final var result = ruleValidatorService.isRuleValid(rule, ruleType);

    assertEquals(validness, result, "Unexpected rule validness");
    verify(auditService, times(auditTimesCalled)).auditWrongRule(rule);
  }
}