package com.kirekov.test_levels.service.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirekov.test_levels.entity.RuleTestBuilder;
import com.kirekov.test_levels.entity.RuleTypeTestBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("RuleValidatorServiceImpl: isRuleValid test cases")
class RuleValidatorServiceImplTest {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final RuleValidatorService service = new RuleValidatorServiceImpl(objectMapper);

  @ParameterizedTest
  @DisplayName("Should return true if keys and values match")
  @CsvSource({
      "key,value,key,value",
      "key,value,key,{\"key\": \"value\"}"
  })
  void shouldReturnTrueIfKeysAndValuesMatch(
      String typeKey, String typeValue,
      String ruleKey, String ruleValue
  ) {
    final var ruleType =
        RuleTypeTestBuilder.builder().setKey(typeKey).setValue(typeValue).build();
    final var rule =
        RuleTestBuilder.builder().setKey(ruleKey).setValue(ruleValue).build();

    final var result = service.isRuleValid(rule, ruleType);

    assertTrue(
        result,
        "Rule should be valid"
    );
  }
}