package com.kirekov.test_levels.service.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirekov.test_levels.entity.RuleTestBuilder;
import com.kirekov.test_levels.entity.RuleTypeTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RuleValidatorServiceImpl: isRuleValid test cases")
class RuleValidatorServiceImplTest {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final RuleValidatorService service = new RuleValidatorServiceImpl(objectMapper);

  @Test
  @DisplayName("Should return true if keys and values match")
  void shouldReturnTrueIfKeysAndValuesMatch() {
    final var rule =
        RuleTestBuilder.builder()
            .setKey("key")
            .setValue("value")
            .build();
    final var ruleType =
        RuleTypeTestBuilder.builder()
            .setKey("key")
            .setValue("value")
            .build();

    final var result = service.isRuleValid(rule, ruleType);

    assertTrue(
        result,
        "Rule should be valid"
    );
  }
}